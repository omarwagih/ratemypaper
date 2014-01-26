package controllers;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


import models.PMCResults;
import models.Paper;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    
    public static Result results() {
        return ok(results.render(new ArrayList(), ""));
    }
    
    public static Result login() {
        return ok(login.render());
    }
    
    public static Result signup() {
        return ok(signup.render());
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
    
    public static List<Paper> fetch(String query) throws Exception{
    	//query = URLEncoder.encode(query, "UTF-8");
    	
    	Logger.info("Encoded query:"+query);
    	String q  = ("http://www.ebi.ac.uk/europepmc/webservices/rest/search/query="+query).replaceAll("\\+","%20");
    	URL url = new URL( q );
    	
    	Logger.info("URL to fetch:"+url.toString());
    	JAXBContext jc = JAXBContext.newInstance(PMCResults.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		PMCResults res = (PMCResults) unmarshaller.unmarshal(url);
		
		return res.getListofPapers();
    }
    
    public static Result search(){
    	String q = request().getQueryString("q");
    	Logger.info(q);
    	
    	if(q == null || q.isEmpty()){
    		return ok(search.render());
    	}
    	
    	List<Paper> p = new ArrayList();
    	try{
    		p = fetch(q);
    	}catch(Exception e){
    		Logger.error(e.getMessage());
    	}
    	Logger.info(p.size() + " records fetched!");
    	return ok(results.render(p, q));
    }
}
