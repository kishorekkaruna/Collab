package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.BlogComment;

public interface BlogCommentDAO {
	
	public List<BlogComment> list();
	public List<BlogComment> getById(int blogId);
	public BlogComment getByBId(int id);
	public void save(BlogComment blogComment);
	public void saveOrUpdate(BlogComment blogComment);
	public void delete(int id);

}
