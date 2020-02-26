package Extra_Scolaire;

import java.util.*;

import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Extra_Scolaire.*;
//import javafx.event.Event;


public class Eventt {

	private int idEvent;
	private String nomEvent;
	private String description;
	private String Datet; 
	private Timestamp timestamp;
	
	
	public String getIdEvent() {
        return Integer.toString(idEvent);
    }
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public String getNomEvent() {
		return nomEvent;
	}
	public void setNomEvent(String nomEvent) {
		this.nomEvent = nomEvent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDate() {
		return timestamp;
	}
	
	public String getDatet() {
        return timestamp.toString();
    }
	public void setDate(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + idEvent;
		result = prime * result + ((nomEvent == null) ? 0 : nomEvent.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eventt other = (Eventt) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idEvent != other.idEvent)
			return false;
		if (nomEvent == null) {
			if (other.nomEvent != null)
				return false;
		} else if (!nomEvent.equals(other.nomEvent))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Event [idEvent=" + idEvent + ", nomEvent=" + nomEvent + ", description=" + description + ", timestamp="
				+ timestamp + ", getIdEvent()=" + getIdEvent() + ", getNomEvent()=" + getNomEvent()
				+ ", getDescription()=" + getDescription() + ", getTimestamp()=" + getDate() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
	
	
	public Eventt( String nomEvent, String description, String date)  {
        this.nomEvent = nomEvent;
        this.description = description;
        try{
            Date dateTime= new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse(date);
            long dateL = dateTime.getTime();
            timestamp = new Timestamp(dateL);
           } catch(ParseException e)
        {
             e.printStackTrace();
        }
    }
	
	 public Eventt(int idEvent, String nomEvent, Timestamp date ) {
	        this.idEvent = idEvent;
	        this.nomEvent = nomEvent;
	        this.timestamp = date;
	        
	    }
	 
	 public Eventt(int idEvent, String nomEvent, String description, Timestamp date ) {
	        this.idEvent = idEvent;
	        this.nomEvent = nomEvent;
	        this.description= description;
	        this.timestamp = date;
	        
	    }
	
	 
	public Eventt(String nomEvent, String description, Timestamp Date) {
		this.nomEvent = nomEvent;
		this.description = description;
		this.timestamp = Date;
	}
	public Eventt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Eventt(int id, String nomEvent, String description, String dateTime) {
		this.idEvent=id;
		this.nomEvent = nomEvent;
		this.description = description;
		this.Datet = dateTime;
	}
	
	
	
	
	
	
	
	
}