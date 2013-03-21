package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Event;

import com.google.gson.Gson;

public class Htmlservices implements services.Services{

	public String getHTML(String urlToRead) {
	      URL url;
	      HttpURLConnection conn;
	      BufferedReader rd;
	      String line;
	      String result = "";
	      try {
	         url = new URL(urlToRead);
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;
	         }
	         rd.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return result;
	   }
	
	public List<Event> listEvents(){
		 String page = getHTML("http://127.0.0.1:9000/events/?page=1");
		 int lastpage = page.lastIndexOf("/events/?page=") + 17;
		 String pagecount = page.substring(lastpage,lastpage + 1);
		 int aktpage = 1;
		 int end;
		 int current;
		 int eventID;
		 List<Event> events = new ArrayList<Event>();
		 while ( aktpage <= Integer.parseInt(pagecount)) {
	     page = getHTML("http://127.0.0.1:9000/events/?page=" + aktpage);
		 end = page.lastIndexOf("/event/") + 6;
		 current = page.indexOf("/event/") + 6;
		 eventID = 0;
		 List<Integer> eventIDs = new ArrayList<Integer>();
		 if (end > 0){
			 while (current <= end ){
				 String str = page.substring(current+1, current+3);
				 if ( str.matches("\\d+")){
					 eventID = Integer.parseInt(str);
				 }
				 else if ( str.substring(0,1).matches("\\d")){
					 eventID = Integer.parseInt(String.valueOf(page.charAt(current+1)));
				 }
				 else {
					 break;
				 }
				 eventIDs.add(eventID);
				 current = page.indexOf("/event/", current + 1) + 6;
			 }
		 }
		 Iterator<Integer> iter = eventIDs.iterator();
		 Event ev = null;
		 String jsonEvent = null;
		 while (iter.hasNext()){
			 jsonEvent = getHTML("http://127.0.0.1:9000/event/" + String.valueOf(iter.next()));
			 ev = new Gson().fromJson(jsonEvent, Event.class);
			 events.add(ev);
		 }
		 aktpage++;
		 }
		 return events;
	}
}
