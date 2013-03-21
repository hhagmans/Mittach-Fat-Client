package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.OneToMany;

public class Event{
	 
    private String title;
    private Date date;
    
    private boolean vegetarian_opt;
    private int slots;
    
    private String details;
    
    @OneToMany(mappedBy="event")
    private List<Booking> bookings;
    
     
    public Event(String title, String details, Date date, int slots, boolean vegetarian) { 
        this.bookings = new ArrayList<Booking>();
        this.title = title;
        this.slots = slots;
        this.details = details;
        this.date = date;
        this.vegetarian_opt = vegetarian;
    }
    
    public Event(Event event) { 
        this.bookings = event.bookings;
        this.title = event.title;
        this.slots = event.slots;
        this.details = event.details;
        this.date = event.date;
        this.vegetarian_opt = event.vegetarian_opt;
    }
    
    public List<String> getUsers(){
    	ListIterator<Booking> iter = this.bookings.listIterator();
    	List<String> users = new ArrayList<String>();;
    	while (iter.hasNext()){
    		Booking book = iter.next();
    		users.add(book.getUser().getShortname());
    }
    	return users;
    }
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isVegetarian_opt() {
		return vegetarian_opt;
	}

	public void setVegetarian_opt(boolean vegetarian_opt) {
		this.vegetarian_opt = vegetarian_opt;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<String> getVegetarians(){
    	ListIterator<Booking> iter = this.bookings.listIterator();
    	List<String> users = new ArrayList<String>();
    	while (iter.hasNext()){
    		Booking book = iter.next();
    		if (book.isVegetarian())
    		users.add(book.getUser().getShortname());
    }
    	return users;
    }
 
}

