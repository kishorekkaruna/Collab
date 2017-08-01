package com.niit.collaborationbackend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.dao.BlogCommentDAO;
import com.niit.collaborationbackend.model.BlogComment;
import com.niit.collaborationbackend.model.User;

@RestController
public class BlogCommentController {

	@Autowired
	BlogCommentDAO blogCommentDAO;

	@GetMapping("/blogcomments")
	public List<BlogComment> getBlogComments() {
		List<BlogComment> blogCommentList = blogCommentDAO.list();

		return blogCommentList;
	}

	@GetMapping("/blogcomment/{blogId}")
	public List<BlogComment> getByBlogCommentId(@PathVariable("blogId") int blogId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("blogId", blogId);
		System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		System.out.println(blogId);
	
		System.out.println(blogId);
		List<BlogComment> blogComment = blogCommentDAO.getById(blogId);

		return blogComment;
	}
	
	@PostMapping("/blogcomment")
	public ResponseEntity<BlogComment> save(@RequestBody BlogComment blogComment, HttpSession session) {
		
		User user = (User) session.getAttribute("loggedInUser");
		
		System.out.println(user.getDob());
		System.out.println("------------------");
		blogComment.setEmail_Id(user.getEmail_id());
		blogComment.setUser_name(user.getUser_name());
		blogComment.setUser_id(user.getUserId());
		
		int blogId =   (Integer) session.getAttribute("blogId");
		System.out.println("printed blog id");
		System.out.println(blogId);
		blogComment.setBlogId(blogId);
		
		blogCommentDAO.save(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	
	@PutMapping("/blogcomment")
	public ResponseEntity<BlogComment> update(@RequestBody BlogComment blogComment) {
		blogCommentDAO.saveOrUpdate(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	
	@DeleteMapping("/blogcomment/{blogId}")
	public ResponseEntity<BlogComment> deleteBlogComment(@PathVariable("blogId") int id) {
		BlogComment blogComment = blogCommentDAO.getByBId(id);
		if (blogComment == null) {
			return new ResponseEntity("No Blog found for ID " + id, HttpStatus.NOT_FOUND);
		}
		blogCommentDAO.delete(id);
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}

}
