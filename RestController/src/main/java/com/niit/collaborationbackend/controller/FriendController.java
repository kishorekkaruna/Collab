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

import com.niit.collaborationbackend.dao.FriendDAO;
import com.niit.collaborationbackend.model.Friend;
import com.niit.collaborationbackend.model.User;

@RestController
public class FriendController {

	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	private Friend friend;

	@GetMapping("/friends")
	public List<Friend> list() {
		List<Friend> freindList = friendDAO.list();
		return freindList;

	}

	@GetMapping("/friend/{friendId}")
	public Friend getByFriendId(@PathVariable("friendId") int id) {
		Friend friendList = friendDAO.getByFriendId(id);

		return friendList;
	}

	@GetMapping("/friendss/{userId}")
	public List<Friend> getByUser(@PathVariable("userId") int name) {
		System.out.println(name+"has been printed");
		return friendDAO.listUnion(name);
	}
	
	@GetMapping("/friends/{name}")  
	public List<Friend> geByID(@PathVariable("name") String name) {
		return friendDAO.getByFriendName(name);
		
	}
	
	@GetMapping("/friendsAccepted/{id}")  
	public List<Friend> geByFriendAccepted(@PathVariable("id") String id) {
		return friendDAO.getByFriendAccepted(id);
	}

	@PostMapping("/friends")
	public ResponseEntity<Friend> save(@RequestBody User friendUser, HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");   
		System.out.println(user.getEmail_id());
		friend.setUserId(user.getUserId());
		friend.setUserName(user.getUser_name());
		friend.setStatus("P");
		friend.setFriendId(friendUser.getUserId());
		System.out.println(friendUser.getFirst_name());
		friend.setFriendName(friendUser.getUser_name());
		//friendUser.setIsOnline("TRUE");
		friendDAO.save(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@PutMapping("/friendAccept")
	public ResponseEntity<Friend> update(@RequestBody Friend friend) {
		friend.setStatus("A");
		friendDAO.saveOrUpdate(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@DeleteMapping("/friends/{friendId}")
	public ResponseEntity<Friend> deleteFriend(@PathVariable("friendId") int id) {
		Friend friend = friendDAO.getByFriendId(id);
		if (friend == null) {
			return new ResponseEntity("No Friend found for ID " + id, HttpStatus.NOT_FOUND);
		}
		friendDAO.delete(id);
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}

}
