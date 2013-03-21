package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import models.Event;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Htmlservices implements services.Services{

	public String getResourceFromURL(String urlToRead) {
	      URL url;
	      HttpURLConnection conn = null;
	      BufferedReader rd = null;
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
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      finally {
	    	  try {
	    		  if (rd != null)
	    			  rd.close();
	    		  if (conn != null)
	    		  conn.disconnect();
	    	  } 
	    	  catch (IOException e) {
	    		  e.printStackTrace();
	    	  }
	      }
	      return result;
	   }
	
	public List<Event> listEvents(){
		 String jsonEvents = getResourceFromURL("http://127.0.0.1:9000/event/all");
		 List<Event> events = new Gson().fromJson(jsonEvents, new TypeToken<Collection<Event>>(){}.getType());
		 return events;
	}
}
