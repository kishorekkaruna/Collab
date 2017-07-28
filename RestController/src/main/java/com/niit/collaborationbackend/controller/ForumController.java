package com.niit.collaborationbackend.controller;

import java.util.List;

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

import com.niit.collaborationbackend.dao.ForumDAO;
import com.niit.collaborationbackend.model.Forum;
import com.niit.collaborationbackend.model.User;

@RestController
public class ForumController {
	
	@Autowired ForumDAO forumDAO;
	
	@GetMapping("/forums")
	public List<Forum> list(){
		List<Forum> forumList=forumDAO.list();
		return forumList;
		
	}
	
	@GetMapping("/forum/{forum_id}")
	public ResponseEntity<Forum> getByForumId(@PathVariable("forum_id")int id ){
		Forum forum = forumDAO.get(id);
		if (forum == null) {
			return new ResponseEntity("No Forum found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	@GetMapping("/AcceptedForum")
	public ResponseEntity<List<Forum>> AcceptedForumList() {
		List<Forum> listForum = forumDAO.getAcceptedList();
		return new ResponseEntity<List<Forum>>(listForum, HttpStatus.OK);
	}
	
	@GetMapping("/notAcceptedForum")
	public ResponseEntity<List<Forum>> notAcceptedForumList() {
		List<Forum> listForum = forumDAO.getNotAcceptedList();
		return new ResponseEntity<List<Forum>>(listForum, HttpStatus.OK);
	}
	
	@PostMapping("/forum")
	public ResponseEntity<Forum> save(@RequestBody Forum forum,HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		System.out.println(user.getDob());
		forum.setUser_name(user.getUser_name());
		forum.setUser_id(user.getUserId());
		forum.setStatus("NA");
		
		forumDAO.save(forum);
		return new ResponseEntity(forum, HttpStatus.OK);
	}

	@PutMapping("/forums")
	public ResponseEntity<Forum> update(@RequestBody Forum forum) {
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity(forum, HttpStatus.OK);
	}
	
	@PutMapping("/forumAccept")
	public ResponseEntity<Forum> updat(@RequestBody Forum forum) {
		forum.setStatus("A");
		
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity(forum, HttpStatus.OK);
	}

	@DeleteMapping("/forum/{forum_id}")
	public ResponseEntity<Forum> deleteforum(@PathVariable("forum_id") int id) {
		Forum forum = forumDAO.get(id);
		if (forum == null) {
			return new ResponseEntity("No Forum found for ID " + id, HttpStatus.NOT_FOUND);
		}
		forumDAO.delete(id);
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}

}
