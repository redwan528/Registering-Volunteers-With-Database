package sba.sms.dao;

import java.util.List;

import sba.sms.models.Activity;
import sba.sms.models.Volunteer;

public interface VolunteerI {

	public void createVolunteer(Volunteer volunteer);
	/*
	 * persist Volunteer to database, also handle commit,rollback, 
	 * and exceptions
	 */
	
	public List<Volunteer> getAllVolunteers();
	
	public Volunteer getVolunteerByEmail(String email);
	
	public boolean validateVolunteer(String email, String password);
	
	public void registerVolunteerToActivity	(String email, int ActivityId);
	
	public List<Activity> getVolunteerActivities(String email);
	
	
	
}
