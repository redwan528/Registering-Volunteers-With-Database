package sba.sms;

import lombok.extern.java.Log;
import sba.sms.models.Activity;
import sba.sms.services.ActivityService;
import sba.sms.services.VolunteerService;
import sba.sms.utils.CommandLine;
import java.util.Scanner;
import java.util.List;


@Log
public class App {
    static final VolunteerService VOLUNTEER_SERVICE = new VolunteerService();
    static final ActivityService ACTIVITY_SERVICE = new ActivityService();

    
    public static void main(String[] args) {
        VolunteerService s = new VolunteerService();
        ActivityService a = new ActivityService();

        // Use to add data
      if((!s.isVolunteerTableNotEmpty()&& !a.isActivityTableNotEmpty()))  CommandLine.addData();
    // Use to run app when you have finished requirements 1-4
      runner();


    }
    // Use to run app when you have finished requirements 1-4


    private static void printVolunteerActivity(String email) {
        System.out.printf("%s activities:%n-----------------------------%n", email);
        System.out.printf("%-2s | %-20s | %n", "ID", "Activity");
        List<Activity> userCours = VOLUNTEER_SERVICE.getVolunteerActivities(email);
        if (userCours.isEmpty()) System.out.printf("No activities to view%n");
        for (Activity activity : userCours) {
            System.out.printf("%-2d | %-20s | %n", activity.getId(), activity.getName());
        }
    }

    static void runner() {
        Scanner input = new Scanner(System.in);
        int userInput;
        do {
            System.out.printf("Select # from menu:%n1.Volunteer%n2.Quit%n");
            userInput = input.nextInt();
            if (userInput == 1) {
                System.out.print("Enter Volunteer email: ");
                String email = input.next();
                System.out.printf("Enter %s's password: ", email.substring(0, email.indexOf("@")));
                String password = input.next();
                if (VOLUNTEER_SERVICE.validateVolunteer(email, password)) {
                    printVolunteerActivity(email);
                    System.out.printf("select # from menu: %n1.Register %s to activity: %n2.Logout%n", VOLUNTEER_SERVICE.getVolunteerByEmail(email).getName());
                    userInput = input.nextInt();
                    if (userInput == 2) {
                        System.exit(0);
                    } else {
                        List<Activity> activityList = ACTIVITY_SERVICE.getAllActivities();
                        System.out.printf("All activities:%n-----------------------------%n");
                        System.out.printf("%-2s | %-20s | %n", "ID", "Activity");
                        if (activityList.isEmpty()) System.out.printf("No activities to view%n");
                        for (Activity activity : activityList) {
                            System.out.printf("%-2d | %-20s |%n", activity.getId(), activity.getName());
                        }
                        System.out.print("select activity #: ");
                        int courseId = input.nextInt();
                        if (courseId > 0 && courseId <= activityList.size()) {

                            VOLUNTEER_SERVICE.registerVolunteerToActivity(email, (courseId));
                            System.out.printf("successfully register %s to %s%n", VOLUNTEER_SERVICE.getVolunteerByEmail(email).getName(), ACTIVITY_SERVICE.getActivityById(courseId).getName());
                            printVolunteerActivity(email);
                        } else {
                            System.out.printf("activity id not found!%n");
                        }
                        System.out.printf("session ended!%n");
                    }
                } else {
                    System.out.printf("Incorrect username or password%n");
                }
            }
        } while (userInput != 2);
        input.close();
    }
}
