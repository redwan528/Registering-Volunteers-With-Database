package sba.sms.dao;

import java.util.List;

import sba.sms.models.Activity;

public interface ActivityI {

public void createActivity (Activity activity);

public List<Activity> getAllActivities();

public Activity getActivityById (int AcitivityId);



}
