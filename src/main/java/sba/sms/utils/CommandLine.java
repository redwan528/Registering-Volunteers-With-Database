package sba.sms.utils;

import java.util.ArrayList;
import java.util.List;

import sba.sms.models.Activity;
import sba.sms.models.Volunteer;
import sba.sms.services.ActivityService;
import sba.sms.services.VolunteerService;


public class CommandLine {
    private CommandLine() {
        // Utility classes should not have public constructors
    }

    public static void addData() {

//        VolunteerService volunteerService = new VolunteerService();
//        ActivityService activityService = new ActivityService();
//
//  //      You can edit or change names as you desire
//       String PASSWORD = "1";
//
//       volunteerService.updateVolunteerTable(null);
//    		   
//    		    
//       List <Volunteer> volunteers = new ArrayList<>();
//       Volunteer v = new Volunteer();
//       
//    	   volunteerService.createVolunteer(new Volunteer("James@gmail.com", "James A", PASSWORD));
//           volunteerService.createVolunteer(new Volunteer("Matthew@gmail.com", "Matthew B", PASSWORD));
//           volunteerService.createVolunteer(new Volunteer("Phillp@gmail.com", "Phillp C", PASSWORD));
//          volunteerService.createVolunteer(new Volunteer("John@gmail.com", "John D", PASSWORD));
//          volunteerService.createVolunteer(new Volunteer("Peter@gmail.com", "Peter E", PASSWORD));
//          volunteerService.createVolunteer(new Volunteer("Peterr@gmail.com", "Peterr E", PASSWORD));
//
//          
//          activityService.createActivity(new Activity("Basketball"));
//          activityService.createActivity(new Activity("Football"));
//           activityService.createActivity(new Activity("Hockey"));
//          activityService.createActivity(new Activity("Tennies"));
//            activityService.createActivity(new Activity("Baseball"));
//            activityService.createActivity(new Activity("Boxing"));

       
       
//        volunteerService.createVolunteer(new Volunteer("James@gmail.com", "James A", PASSWORD));
//        volunteerService.createVolunteer(new Volunteer("Matthew@gmail.com", "Matthew B", PASSWORD));
//        volunteerService.createVolunteer(new Volunteer("Phillp@gmail.com", "Phillp C", PASSWORD));
//       volunteerService.createVolunteer(new Volunteer("John@gmail.com", "John D", PASSWORD));
//       volunteerService.createVolunteer(new Volunteer("Peter@gmail.com", "Peter E", PASSWORD));

    
       
       
//       activityService.createActivity(new Activity("Basketball"));
//      activityService.createActivity(new Activity("Football"));
//       activityService.createActivity(new Activity("Hockey"));
//      activityService.createActivity(new Activity("Tennies"));
//        activityService.createActivity(new Activity("Baseball"));


    	
    	VolunteerService volunteerService = new VolunteerService();
        ActivityService activityService = new ActivityService();

        String PASSWORD = "1";

        

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("James@gmail.com", "James A", PASSWORD));
        volunteers.add(new Volunteer("Matthew@gmail.com", "Matthew B", PASSWORD));
        volunteers.add(new Volunteer("Phillp@gmail.com", "Phillp C", PASSWORD));
        volunteers.add(new Volunteer("John@gmail.com", "John D", PASSWORD));
        volunteers.add(new Volunteer("Peter@gmail.com", "Peter E", PASSWORD));
        volunteers.add(new Volunteer("Peterr@gmail.com", "Peterr E", PASSWORD));
//        volunteers.add(new Volunteer("godlove@gmail.com", "godlove E", PASSWORD));
        
        for (Volunteer volunteer : volunteers) {
            volunteerService.createVolunteer(volunteer);
        }
        volunteerService.updateVolunteerTable(volunteers);
        

        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("Basketball"));
        activities.add(new Activity("Football"));
        activities.add(new Activity("Hockey"));
//        activities.add(new Activity("Tennis"));
        activities.add(new Activity("Baseball"));
        activities.add(new Activity("Boxing"));
        activities.add(new Activity("Soccer"));


        for (Activity activity : activities) {
            activityService.createActivity(activity);
        }
        
        activityService.updateActivityTable(activities);

    

    }
    
    
}
