package com.restful;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.restful.comment.model.Comment;
import com.restful.video.model.Video;


/* I used 
JAX-RS stands for JAVA API for RESTful Web Services
Hibernate Object Relational Mapping (ORM) 
 */

@Path("/comment")
public class EntertainmentComment {
	@Path("/add")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void creatComment(Comment comment) {
		SessionFactory factory;
		ArrayList<Comment> commentList = null;
		try{
			factory = new Configuration().
					configure().
					//addPackage("com.restful.comment.model") //add package if used.
					addAnnotatedClass(com.restful.comment.model.Comment.class).
					buildSessionFactory();
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		Session session = factory.openSession();
		Comment commentSave = null;
		Transaction tx = null;  

		try{
			tx = session.beginTransaction();
			commentSave = new Comment();
			commentSave.setComment(comment.getComment());
			commentSave.setEmail(comment.getEmail());
			commentSave.setEntertainmentTitle(comment.getEntertainmentTitle());
			commentSave.setUrl(comment.getUrl());
			commentSave.setUserName(comment.getUserName());
			commentSave.setVideoInfoUrl(comment.getUrl());		
			commentSave.setVideoType(comment.getEntertainmentTitle());
			session.save(commentSave);
			tx.commit(); 

			//			test commentList
			//			for (Iterator iterator = 
			//					commentList.iterator(); iterator.hasNext();){
			//				Comment employee = (Comment) iterator.next(); 
			//				System.out.print("First Name: " + employee.getComment()); 
			//				System.out.print("  Last Name: " + employee.getEmail()); 
			//				System.out.println("  url: " + employee.getUrl());
			//				System.out.println("  date: " + employee.getDate()); 
			//			}

		}catch (Exception ex) {  
			ex.printStackTrace();  
			tx.rollback();  
		}  
		finally {
			session.close(); 
			factory.close();
		}
	}



	@Path("/search")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ArrayList<Comment> getCommentsByUrl(String url) {

		SessionFactory factory;
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		videoViewedCount(url);
		try{
			factory = new Configuration().
					configure().
					//addPackage("com.restful.comment.model") //add package if used.
					addAnnotatedClass(com.restful.comment.model.Comment.class).
					buildSessionFactory();
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Comment.class);
			criteria.add(Restrictions.eq("url", url));
			if(criteria.list() != null){
				commentList = (ArrayList<Comment>) criteria.list();
			}
			session.getTransaction().commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close();
			factory.close();
		}
		return commentList;
	}


	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ArrayList<Comment> getAllComments(@PathParam("url") String  url) {
		SessionFactory factory;
		ArrayList<Comment> commentList = new ArrayList<Comment>();

		try{
			factory = new Configuration().
					configure().
					//addPackage("com.restful.comment.model") //add package if used.
					addAnnotatedClass(com.restful.comment.model.Comment.class).
					buildSessionFactory();
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Comment.class);
			criteria.add(Restrictions.eq("url", url));
			criteria.addOrder(Order.desc("date"));
			if(criteria.list() != null){
				commentList = (ArrayList<Comment>) criteria.list();
			}
			session.getTransaction().commit();

		}catch (HibernateException e) {
			e.printStackTrace(); 
		}finally {
			session.close(); 
			factory.close();
		}
		return commentList;
	}

	public void videoViewedCount(String  url) {
		System.out.println("url  " + url);
		SessionFactory factory;
		ArrayList<Video> videoList = new ArrayList<Video>();
		try{
			factory = new Configuration().
					configure().
					//addPackage("ccom.restful.video.model") //add package if used.
					addAnnotatedClass(com.restful.video.model.Video.class).
					buildSessionFactory();
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		Session session = factory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Video.class);
			criteria.add(Restrictions.eq("url", url));
			criteria.setMaxResults(1);
			Video videoCount =  (Video)criteria.uniqueResult();
			if(videoCount != null){
				int numberOfViewers = videoCount.getCount() + 1;
				videoCount.setCount(numberOfViewers);
				session.update(videoCount);
				session.getTransaction().commit();
			}

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
			factory.close();
		}

	}





	@Path("/videos/{videoType}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ArrayList<Video> getAllVideos(@PathParam("videoType") String  videoType) {

		SessionFactory factory;
		ArrayList<Video> videoList = new ArrayList<Video>();

		try{
			factory = new Configuration().
					configure().
					//addPackage("ccom.restful.video.model") //add package if used.
					addAnnotatedClass(com.restful.video.model.Video.class).
					buildSessionFactory();
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
		Session session = factory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Video.class);
			criteria.add(Restrictions.eq("videoType", videoType));
			if(criteria.list() != null){
				videoList = (ArrayList<Video>) criteria.list();
			}
			session.getTransaction().commit();

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
			factory.close();
		}
		return videoList;
	}

}
