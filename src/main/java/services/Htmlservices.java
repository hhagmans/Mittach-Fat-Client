package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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
	
	public void deleteEvent(long ID){
	         URL url = null;
	         HttpURLConnection httpCon = null;
	         try{
				url = new URL("http://127.0.0.1:9000/event/" + ID);
				httpCon = (HttpURLConnection) url.openConnection();
	         httpCon.setDoOutput(true);
	         httpCon.setRequestProperty(
	             "Content-Type", "application/x-www-form-urlencoded" );
				httpCon.setRequestMethod("DELETE");
				httpCon.connect();
				httpCon.getContent();
	         } catch (Exception e){
	        	 e.printStackTrace();
	         }
	         finally{
	        	 if (httpCon != null) {
	        		 httpCon.disconnect();
	        	 }
	         }
	}
	
	public void createEvent(Event event) {
		String body = new Gson().toJson(event);
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try{
			URL url = new URL( "http://127.0.0.1:9000/event" );
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod( "POST" );
			connection.setDoInput( true );
			connection.setDoOutput( true );
			connection.setUseCaches( false );
			connection.setRequestProperty( "Content-Type",
					"application/json" );
			connection.setRequestProperty( "Content-Length", String.valueOf(body.length()) );

			writer = new OutputStreamWriter( connection.getOutputStream() );
			writer.write( body );
			writer.flush();
			
			connection.getContent();

		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if (writer != null)
					writer.close();
				if (reader!= null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}
}
