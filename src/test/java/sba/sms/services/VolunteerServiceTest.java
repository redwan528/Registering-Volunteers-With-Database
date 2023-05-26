package sba.sms.services;

import lombok.AccessLevel;

import lombok.experimental.FieldDefaults;
import sba.sms.models.Activity;
import sba.sms.models.Volunteer;
import sba.sms.utils.CommandLine;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@FieldDefaults(level = AccessLevel.PRIVATE)
class VolunteerServiceTest {

	static VolunteerService volunteerService;
	static ActivityService activityService;

	@BeforeAll
	static void beforeAll() {
		volunteerService = new VolunteerService();
		activityService = new ActivityService();
		//if	(volunteerService.isVolunteerTableNotEmpty()&& activityService.isActivityTableNotEmpty()) 
			CommandLine.addData();
	}

	/// Create one test
	@Test
	void testGetAllVolunteers() {
		List<Volunteer> volunteers = volunteerService.getAllVolunteers();
		// Check if the retrieved volunteers list is not null and has the expected size
		Assertions.assertNotNull(volunteers);
		Assertions.assertEquals(6, volunteers.size());
	}

	@Test
	void testGetActivityById() {

		Activity retrievedActivityId = activityService.getActivityById(100);
		System.out.print("retrieveActivityById " + retrievedActivityId.getName());

		Assertions.assertNotNull(retrievedActivityId);

		// Assert that the retrieved activity has the expected properties
		Assertions.assertEquals(100, retrievedActivityId.getId());

	}

}