package models;


import java.util.*;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "responseWrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class PMCResults {

	 public PMCResults(){}
	 
	 @XmlElementWrapper(name="resultList")
	 @XmlElement(name="result")
	 private List<Paper> listOfPapers;
	 
	 public List<Paper> getListofPapers() {
		 return listOfPapers;
	 }

}
