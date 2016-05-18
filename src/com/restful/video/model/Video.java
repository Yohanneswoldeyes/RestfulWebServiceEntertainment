package com.restful.video.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.persistence.*;

/* I used 
JAX-RS stands for JAVA API for RESTful Web Services
Hibernate Object Relational Mapping (ORM) 
*/
//POJO class for Video model. I use RESTful (annotations to map a resource as a web service resource)
// and Hibernate annotation 

@Entity
@Table(name = "video")
@XmlRootElement(name = "video")
public class Video {

	@Id @GeneratedValue
	@Column(name = "videoInfoID")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "url")
	private String url;

	@Column(name = "image")
	private String image;

	@Column(name = "artistFirstName")
	private String artistFirstName;

	@Column(name = "atristLastName")
	private String atristLastName;

	@Column(name = "count")
	private int count;

	@Column(name = "desciption")
	private String desciption ;

	@Column(name = "videoType")
	private String videoType;
	
	public Video(){

	}
	
	public Video(int id, String title, String url, String image,
			String artistFirstName, String atristLastName, int count,
			String desciption, String videoType) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.image = image;
		this.artistFirstName = artistFirstName;
		this.atristLastName = atristLastName;
		this.count = count;
		this.desciption = desciption;
		this.videoType = videoType;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@XmlElement
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@XmlElement
	public String getArtistFirstName() {
		return artistFirstName;
	}
	public void setArtistFirstName(String artistFirstName) {
		this.artistFirstName = artistFirstName;
	}
	@XmlElement
	public String getAtristLastName() {
		return atristLastName;
	}
	public void setAtristLastName(String atristLastName) {
		this.atristLastName = atristLastName;
	}
	@XmlElement
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@XmlElement
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	@XmlElement
	public String getVideoType() {
		return videoType;
	}
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

}
