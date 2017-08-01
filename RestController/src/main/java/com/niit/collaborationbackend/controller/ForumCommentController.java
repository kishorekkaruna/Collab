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

import com.niit.collaborationbackend.dao.ForumCommentDAO;
import com.niit.collaborationbackend.model.ForumComment;
import com.niit.collaborationbackend.model.User;

@RestController
public class ForumCommentController {

	@Autowired
	ForumCommentDAO forumCommentDAO;

	@GetMapping("/forumComments")
	public List<ForumComment> list() {
		List<ForumComment> forumCommentList = forumCommentDAO.list();
		return forumCommentList;

	}

	@GetMapping("/forumComment/{forumComment_id}")
	public ResponseEntity getByForumCommentId(@PathVariable("forumComment_id") int id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("forumComment_id", id);
		System.out.println(
				"haaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		List<ForumComment> forumCommentList=forumCommentDAO.getForumComments(id);

		return new ResponseEntity(forumCommentList, HttpStatus.OK);
	}

	@PostMapping("/forumComment")
	public ResponseEntity<ForumComment> save(@RequestBody ForumComment forumComment, HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");

		System.out.println(user.getDob());
		System.out.println("------------------");
		forumComment.setEmail_id(user.getEmail_id());
		forumComment.setUser_name(user.getUser_name());
		forumComment.setUserId(user.getUserId());

		int forumComment_id = (Integer) session.getAttribute("forumComment_id");
		System.out.println("printed blog id");
		System.out.println(forumComment_id);
		forumComment.setForumid(forumComment_id);

		forumCommentDAO.save(forumComment);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}

	@PutMapping("/forumComment")
	public ResponseEntity<ForumComment> saveOrUpdate(@RequestBody ForumComment forumComment) {
		forumCommentDAO.saveOrUpdate(forumComment);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}

	@DeleteMapping("/forumComment/{forumId}")
	public ResponseEntity<ForumComment> deleteforumComment(@PathVariable("forumId") int id) {
		ForumComment forumComment = forumCommentDAO.getComment(id);
		if (forumComment == null) {
			return new ResponseEntity("No ForumComment found for ForumId " + id, HttpStatus.NOT_FOUND);
		}
		forumCommentDAO.delete(forumComment);
		;
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}

}
