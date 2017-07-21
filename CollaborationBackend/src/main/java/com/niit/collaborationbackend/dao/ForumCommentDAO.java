package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.ForumComment;

public interface ForumCommentDAO {

	public List<ForumComment> list();

	public void save(ForumComment forumComment);

	public void saveOrUpdate(ForumComment forumComment);

	public void delete(ForumComment forumComment);

	public List<ForumComment> getForumComments(int forumId);

	public ForumComment getComment(int Id);

}
