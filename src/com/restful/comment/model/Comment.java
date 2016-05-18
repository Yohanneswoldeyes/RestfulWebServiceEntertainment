package com.restful.comment.model;




import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.persistence.*;
/* I used 
   JAX-RS stands for JAVA API for RESTful Web Services
   Hibernate Object Relational Mapping (ORM) 
 */

//POJO class for comment model. I use RESTful (annotations to map a resource as a web service resource)
//and Hibernate annotation 

@Entity
@Table(name = "comment")
@XmlRootElement(name = "comment")
public class Comment {

	@Id @GeneratedValue
	@Column(name = "id")
	private int id;

	private String entertainmentTitle;

	@Column(name = "comment")
	private String comment;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "url")
	private String url;

	@Column(name = "video_type")
	private String videoType;

	@Column(name = "videoInfo_url")
	private String videoInfoUrl;

	@Column(name = "date")
	private Date date;




	public Comment(){

	}
	public Comment(int id, String entertainmentTitle, String comment,
			String userName, String email, String url, String videoType, String videoInfoUrl, Date date) {
		super();
		this.id = id;
		this.entertainmentTitle = entertainmentTitle;
		this.comment = comment;
		this.userName = userName;
		this.email = email;
		this.url = url;
		this.videoType = videoType;
		this.videoInfoUrl = videoInfoUrl;
		this.date = date;
	}




	@XmlElement
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@XmlElement
	public String getVideoInfoUrl() {
		return videoInfoUrl;
	}



	public void setVideoInfoUrl(String videoInfoUrl) {
		this.videoInfoUrl = videoInfoUrl;
	}



	@XmlElement
	public String getVideoType() {
		return videoType;
	}



	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getEntertainmentTitle() {
		return entertainmentTitle;
	}
	public void setEntertainmentTitle(String entertainmentTitle) {
		this.entertainmentTitle = entertainmentTitle;
	}
	@XmlElement
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@XmlElement
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


}
