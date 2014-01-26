ReadMyPaper
=====================================

##Requirements

- Play 2.2.1
- Java version 1.7.0_51, Java(TM) SE Runtime Environment (build 1.7.0_51-b13)


##Pages

- `/search` will take you to the search page where you can search for articles. Articles are fetched using REST API by Pubmed Central Europe, obtained as XML and converted to Java objects using [JAXP](http://en.wikipedia.org/wiki/Java_API_for_XML_Processing). To try out, navigate to this: `http://localhost:9000/search?q=omar+wagih`

- `/results` displays records fetched and is redirected from search. Top part `Showing 1 to 10 of 25 entries` is a placeholder. I still need to add pagination here.

- `/rate` shows the rating page for one paper. Right now this is static, but should become dynamic once the DB is set up (i.e. you should be able to rate a paper by going to `/rate/{pubmedID}`)

- `/view` lets you view ratings on a paper. Right now this is static, but should become dynamic once the DB is set up (i.e. you should be able to view a paper by going to `/view/{pubmedID}`)

- `/login` login for users. Page is static still.

- `/signup` sign up for users. Page is static still.

- `/account` TODO. This will be a settings page showing user info and the papers he/she have rated. sign up for users. 

