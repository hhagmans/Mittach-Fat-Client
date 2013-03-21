package models;

import java.util.Date;
import java.util.List;

public class EventInfo {
	private String title;
    private Date date;
    
    private boolean vegetarian_opt;
    private int slots;
    
    private String details;
    
    private List<Booking> bookings;

     public EventInfo(Event event) {
    	 this.bookings = event.getBookings();
         this.title = event.getTitle();
         this.slots = event.getSlots();
         this.details = event.getDetails();
         this.date = event.getDate();
         this.vegetarian_opt = event.isVegetarian_opt();
     }

     public String toString() {
         return title;
     }
}
