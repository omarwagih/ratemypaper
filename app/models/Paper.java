package models;


import javax.xml.bind.annotation.*;

@XmlRootElement(namespace = "PMCResults")
public class Paper {

	public Paper(){}
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="authorString")
	private String authors;

	@XmlElement(name="pubYear")
	private String date;
	
	@XmlElement(name="journalTitle")
	private String journal;
	
	@XmlElement(name="journalVolume")
	private String volume;
	
	@XmlElement(name="pageInfo")
	private String pages;
	
	@XmlElement(name="citedByCount")
	private Integer cites;
	
	@XmlElement(name="pmid")
	private String pmid;


	public String getTitle() {
		return title;
	}

	public String getPmid() {
		return pmid;
	}
	
	public String getAuthors() {
		return authors;
	}

	public String getDate() {
		return date;
	}

	public String getJournal() {
		return journal;
	}

	public String getVolume() {
		return volume;
	}

	public String getPages() {
		return pages;
	}

	public Integer getCites() {
		return cites;
	}

	
}
