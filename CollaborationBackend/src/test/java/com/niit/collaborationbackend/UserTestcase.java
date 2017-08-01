package com.niit.collaborationbackend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.dao.BlogCommentDAO;
import com.niit.collaborationbackend.dao.BlogDAO;
import com.niit.collaborationbackend.dao.EventDAO;
import com.niit.collaborationbackend.dao.ForumCommentDAO;
import com.niit.collaborationbackend.dao.ForumDAO;
import com.niit.collaborationbackend.dao.FriendDAO;
import com.niit.collaborationbackend.dao.JobDAO;
import com.niit.collaborationbackend.dao.UserDAO;
import com.niit.collaborationbackend.model.Blog;
import com.niit.collaborationbackend.model.BlogComment;
import com.niit.collaborationbackend.model.Event;
import com.niit.collaborationbackend.model.Forum;
import com.niit.collaborationbackend.model.ForumComment;
import com.niit.collaborationbackend.model.Friend;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.User;

public class UserTestcase {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaborationbackend");
		context.refresh();

		UserDAO userDAO = (UserDAO) context.getBean("UserDAO");
		BlogDAO blogDAO = (BlogDAO) context.getBean("BlogDAO");
		BlogCommentDAO blogCommentDAO = (BlogCommentDAO) context.getBean("BlogCommentDAO");
		ForumDAO forumDAO = (ForumDAO) context.getBean("forumDAO");
		ForumCommentDAO forumCommentDAO = (ForumCommentDAO) context.getBean("forumCommentDAO");
		JobDAO jobDAO=(JobDAO) context.getBean("jobDAO");
		FriendDAO friendDAO=(FriendDAO) context.getBean("friendDAO");	
		EventDAO eventDAO=(EventDAO) context.getBean("eventDAO");

		User user = (User) context.getBean("user");
		Blog blog = (Blog) context.getBean("blog");
		BlogComment blogComment = (BlogComment) context.getBean("blogComment");
		Forum forum = (Forum) context.getBean("forum");
		ForumComment forumComment = (ForumComment) context.getBean("forumComment");
		Job job = (Job) context.getBean("job");
		Friend friend = (Friend) context.getBean("friend");
		Event event = (Event) context.getBean("event");

		/* User Table */
		user.setFirst_name("Kishore");
		user.setLast_name("Karuna");
		user.setPassword("kishore");
		user.setEmail_id("kishorekaruna@gmail.com");
		user.setDob("25-3-1994");
		user.setRole("User");
		user.setStatus("V");
		user.setIs_online("is_online");

		userDAO.create(user);

		/* BlogDATA */
		blog.setBlog_name("");
		blog.setUser_id(1);
		blog.setEmail_Id("");
		blog.setUser_name("");
		blog.setStatus("N");
		blog.setLikes(2);

		blogDAO.save(blog);

		/* BlogComment */
		blogComment.setBlog_name("AngularJS");
		blogComment.setMessage("message");
		blogComment.setUser_id(1);
		blogComment.setUser_name("Kishore");
		blogComment.setBlogId(2);

		blogCommentDAO.save(blogComment);
		
		/* Forum DATA */
		forum.setDescription("bye");
		forum.setTitle("hi");

		forumDAO.save(forum);
		
		/* ForumComment */
		forumComment.setEmail_id("Krishna@gmail.com");
		forumComment.setForumid(10);
		forumComment.setUser_name("Krishna");
		
		forumCommentDAO.save(forumComment);
		
		/*Job*/
		job.setJobProfile("BE");
		job.setJobDescription("developer");
		
		jobDAO.save(job);
		
		/*Friend*/
		friend.setFriendName("Rap");
		friend.setStatus("Y");
		
		friendDAO.save(friend);
		
//		Event
		event.setDescription("Hi this event is going to held on saturday");
		
		eventDAO.save(event);
		
	}

}
