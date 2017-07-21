package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.Blog;

public interface BlogDAO {

	public List<Blog> list();

	public Blog getByBlogId(int blogId);
	
	public Blog getByBlogName(String blog_name);

	public List<Blog> getAcceptedBlog();

	public List<Blog> getNotAcceptedBlog();

	public Blog getByuId(int user_id);

	public Blog getbyemailId(String email_Id);

	public void save(Blog blog);

	public Blog delete(int blogId);

	public void saveOrUpdate(Blog blog);
}
