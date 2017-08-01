package com.niit.collaborationbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.dao.EventDAO;
import com.niit.collaborationbackend.model.Event;

@Repository("EventDAO")
@Transactional
public class EventDAOImpl implements EventDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Event> list() {
		return sessionFactory.getCurrentSession().createQuery("from Event").list();
	}

	public void save(Event event) {
		sessionFactory.getCurrentSession().save(event);

	}

	public void saveOrUpdate(Event event) {
		sessionFactory.getCurrentSession().saveOrUpdate(event);

	}

	public Event getEventId(int id) {
		Event eventListByID = (Event) sessionFactory.getCurrentSession().get(Event.class, id);

		return eventListByID;
	}

	public void delete(int id) {
		sessionFactory.getCurrentSession().delete(id);

	}

}
