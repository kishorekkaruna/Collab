package com.niit.collaborationbackend.controller;

import java.util.List;

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

import com.niit.collaborationbackend.dao.EventDAO;
import com.niit.collaborationbackend.model.Event;

@RestController
public class EventController {

	@Autowired
	EventDAO eventDAO;

	@GetMapping("/Event")
	public List<Event> getevnt() {
		List<Event> eventList = eventDAO.list();

		return eventList;
	}

	@GetMapping("/event/{eventId}")
	public ResponseEntity<Event> getEventId(@PathVariable("eventId") int id) {
		Event event = eventDAO.getEventId(id);

		return new ResponseEntity<Event>(event, HttpStatus.OK);

	}

	@PostMapping("/event")
	public ResponseEntity<Event> save(@RequestBody Event event) {
		eventDAO.save(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@PutMapping("/event")
	public ResponseEntity<Event> update(@RequestBody Event event) {
		eventDAO.saveOrUpdate(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@DeleteMapping("/events/{eventId}")
	public ResponseEntity<Event> deleteEvent(@PathVariable("eventId") int id) {
		Event event = eventDAO.getEventId(id);
		if (event == null) {
			return new ResponseEntity("No Event found for ID " + id, HttpStatus.NOT_FOUND);
		}
		eventDAO.delete(id);
		return new ResponseEntity("deleted for ID " + id, HttpStatus.OK);

	}
}
