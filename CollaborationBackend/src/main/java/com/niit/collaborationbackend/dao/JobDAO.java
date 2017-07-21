package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.Job;

public interface JobDAO {

	public List<Job> list();

	public void save(Job job);

	public void saveOrUpdate(Job job);

	public Job getByJobId(int jobid);

	public Job delete(int jobid);

}
