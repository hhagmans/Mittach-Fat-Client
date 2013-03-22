package models;

import java.util.Date;
import java.util.List;

public class EventInfo {
	private long id;
	private String title;
    private Date date;
    
    private boolean vegetarian_opt;
    private int slots;
    
    private String details;
    
    private List<Booking> bookings;

     public EventInfo(Event event) {
    	 this.id = event.getID();
    	 this.bookings = event.getBookings();
         this.title = event.getTitle();
         this.slots = event.getSlots();
         this.details = event.getDetails();
         this.date = event.getDate();
         this.vegetarian_opt = event.isVegetarian_opt();
     }

     public long getID() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getDate() {
		return date;
	}

	public boolean isVegetarian_opt() {
		return vegetarian_opt;
	}

	public int getSlots() {
		return slots;
	}

	public String getDetails() {
		return details;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public String toString() {
         return title;
     }
}
