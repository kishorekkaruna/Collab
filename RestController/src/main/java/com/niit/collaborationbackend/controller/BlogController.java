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

import com.niit.collaborationbackend.dao.BlogDAO;
import com.niit.collaborationbackend.model.Blog;
import com.niit.collaborationbackend.model.User;

@RestController
public class BlogController {

	@Autowired
	private BlogDAO blogDAO;

	@GetMapping("/blogs")
	public List<Blog> getBlogs() {
		List<Blog> blogList = blogDAO.list();

		return blogList;
	}

	@GetMapping("/blogId/{blogId}")
	public ResponseEntity<Blog> getByBlogId(@PathVariable("blogId") int id) {
		Blog blog = blogDAO.getByBlogId(id);
		if (blog == null) {
			return new ResponseEntity("No Blog found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@GetMapping("/blogName/{blog_name}")
	public ResponseEntity<Blog> getblogByBlogName(@PathVariable("blog_name") String blog_name) {
		Blog blog = blogDAO.getByBlogName(blog_name);
		if (blog == null) {
			return new ResponseEntity("No Blog found for Blog Name " + blog_name, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);

	}
	
	@GetMapping("/acceptedblog")
	public ResponseEntity<List<Blog>> AcceptedBlogList() {
		List<Blog> listblog = blogDAO.getAcceptedBlog();
		return new ResponseEntity<List<Blog>>(listblog, HttpStatus.OK);
	}
	
	@GetMapping("/notAcceptedblog")
	public ResponseEntity<List<Blog>> notAcceptedBlogList() {
		List<Blog> listblog = blogDAO.getNotAcceptedBlog();
		return new ResponseEntity<List<Blog>>(listblog, HttpStatus.OK);
	}

	@PostMapping("/blog")
	public ResponseEntity<Blog> save(@RequestBody Blog blog,HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		System.out.println(user.getDob());
		blog.setUser_name(user.getUser_name());
		blog.setEmail_Id(user.getEmail_id());
		blog.setUser_id(user.getUserId());
		blog.setStatus("NA");
	
		blogDAO.save(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@PutMapping("/blog/{blogId}")
	public ResponseEntity<Blog> update(@PathVariable("blogId") int id,@RequestBody Blog blog) {
		blogDAO.saveOrUpdate(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@PutMapping("acceptBlog")
	public ResponseEntity<Blog> accept(@RequestBody Blog blog) {
		blog.setStatus("A");
		blogDAO.saveOrUpdate(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	

	@DeleteMapping("/blog/{blogId}")
	public ResponseEntity<Blog> deleteUser(@PathVariable("blogId") int id) {
		Blog blog = blogDAO.getByBlogId(id);
		if (blog == null) {
			return new ResponseEntity("No Blog found for ID " + id, HttpStatus.NOT_FOUND);
		}
		blogDAO.delete(id);
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}
}
