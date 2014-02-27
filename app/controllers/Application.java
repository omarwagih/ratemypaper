package controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import models.PMCResults;
import models.Paper;
import models.User;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.Session;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;
import views.html.about;
import views.html.contact;
import views.html.index;
import views.html.login;
import views.html.profile;
import views.html.rate;
import views.html.restricted;
import views.html.results;
import views.html.search;
import views.html.signup;
import views.html.top;
import views.html.view;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider;
import com.feth.play.module.pa.user.AuthUser;

public class Application extends Controller {
	public static final String FLASH_MESSAGE_KEY = "message";
	public static final String FLASH_ERROR_KEY = "error";
	public static final String USER_ROLE = "user";

	public static Result index() {
		return ok(index.render());
	}

	public static Result results() {
		return ok(results.render(new ArrayList<Paper>(), ""));
	}

	public static Result about() {
		return ok(about.render());
	}

	public static Result contact() {
		return ok(contact.render());
	}

	public static Result top() {
		return ok(top.render());
	}

	public static Result rate() {
		return ok(rate.render());
	}

	public static Result view() {
		return ok(view.render());
	}

	public static List<Paper> fetch(String query) throws Exception {
		// query = URLEncoder.encode(query, "UTF-8");

		Logger.info("Encoded query:" + query);
		String q = ("http://www.ebi.ac.uk/europepmc/webservices/rest/search/query=" + query)
				.replaceAll("\\+", "%20");
		URL url = new URL(q);

		Logger.info("URL to fetch:" + url.toString());
		JAXBContext jc = JAXBContext.newInstance(PMCResults.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		PMCResults res = (PMCResults) unmarshaller.unmarshal(url);

		return res.getListofPapers();
	}

	public static Result search() {
		String q = request().getQueryString("q");
		Logger.info(q);

		if (q == null || q.isEmpty()) {
			return ok(search.render());
		}

		List<Paper> p = new ArrayList<Paper>();
		try {
			p = fetch(q);
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
		Logger.info(p.size() + " records fetched!");
		return ok(results.render(p, q));
	}

	public static Result signup() {
		return ok(signup.render(MyUsernamePasswordAuthProvider.SIGNUP_FORM));
	}

	public static Result doSignup() {
		for (Entry<String, String[]> a : request().queryString().entrySet()) {
			System.out.println(a.getKey());
		}

		com.feth.play.module.pa.controllers.Authenticate.noCache(response());
		final Form<MySignup> filledForm = MyUsernamePasswordAuthProvider.SIGNUP_FORM
				.bindFromRequest();
		if (filledForm.hasErrors()) {
			// User did not fill everything properly
			return badRequest(signup.render(filledForm));
		} else {
			// Everything was filled
			// do something with your part of the form before handling the user
			// signup
			return UsernamePasswordAuthProvider.handleSignup(ctx());
		}
	}

	public static User getLocalUser(final Session session) {
		final AuthUser currentAuthUser = PlayAuthenticate.getUser(session);
		final User localUser = User.findByAuthUserIdentity(currentAuthUser);
		return localUser;
	}

	@Restrict(@Group(Application.USER_ROLE))
	public static Result restricted() {
		final User localUser = getLocalUser(session());
		return ok(restricted.render(localUser));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public static Result profile() {
		final User localUser = getLocalUser(session());
		return ok(profile.render(localUser));
	}

	public static Result login() {
		return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
	}

	public static Result doLogin() {
		com.feth.play.module.pa.controllers.Authenticate.noCache(response());
		final Form<MyLogin> filledForm = MyUsernamePasswordAuthProvider.LOGIN_FORM
				.bindFromRequest();
		if (filledForm.hasErrors()) {
			// User did not fill everything properly
			return badRequest(login.render(filledForm));
		} else {
			// Everything was filled
			return UsernamePasswordAuthProvider.handleLogin(ctx());
		}
	}

	public static String formatTimestamp(final long t) {
		return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
	}
}
