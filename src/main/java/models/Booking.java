package models;

import javax.persistence.*;

 

public class Booking{
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="event_id")
	private Event event;
	
	@ManyToOne
	private User user;
	private boolean vegetarian;
	
	
	public Booking(Event event, User user, boolean vegetarian){
		this.event = event;
		this.user = user;
		this.vegetarian = vegetarian;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	@Override
	public String toString() {
		return "Booking[" + this.user.toString() + ", " + this.event.getTitle() + "]";
	}

}

