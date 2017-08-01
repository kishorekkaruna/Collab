package com.niit.collaborationbackend.dao;

import java.util.List;

import com.niit.collaborationbackend.model.Event;

public interface EventDAO {

	public List<Event> list();

	public void save(Event event);

	public void saveOrUpdate(Event event);

	public Event getEventId(int id);

	public void delete(int id);

}
