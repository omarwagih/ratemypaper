package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

@Entity
public class Rating extends Model {
	@NotNull
	private User user;
	private String pmid;
	private boolean isAuthor;
	private Date dateCreated;
	
	private int votesUp;
	private int votesDown;
	
	private String comment;
	
	private double rateClarity;
	private double rateScience;
	private double rateUseful;
	private double rateInterest;
	private double rateOverall;
	
	private int flag;

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getPmid() {
		return pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	public boolean isAuthor() {
		return isAuthor;
	}

	public void setAuthor(boolean isAuthor) {
		this.isAuthor = isAuthor;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getVotesUp() {
		return votesUp;
	}

	public void setVotesUp(int votesUp) {
		this.votesUp = votesUp;
	}

	public int getVotesDown() {
		return votesDown;
	}

	public void setVotesDown(int votesDown) {
		this.votesDown = votesDown;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getRateClarity() {
		return rateClarity;
	}

	public void setRateClarity(double rateClarity) {
		this.rateClarity = rateClarity;
	}

	public double getRateScience() {
		return rateScience;
	}

	public void setRateScience(double rateScience) {
		this.rateScience = rateScience;
	}

	public double getRateUseful() {
		return rateUseful;
	}

	public void setRateUseful(double rateUseful) {
		this.rateUseful = rateUseful;
	}

	public double getRateInterest() {
		return rateInterest;
	}

	public void setRateInterest(double rateInterest) {
		this.rateInterest = rateInterest;
	}

	public double getRateOverall() {
		return rateOverall;
	}

	public void setRateOverall(double rateOverall) {
		this.rateOverall = rateOverall;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
