package services;

import java.util.List;

import models.Event;

public interface Services {

	public List<Event> listEvents();
	public void deleteEvent(long ID);
	public void createEvent(Event event);
}
