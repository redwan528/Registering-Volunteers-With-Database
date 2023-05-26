package sba.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import sba.sms.dao.ActivityI;
import sba.sms.models.Activity;

public class ActivityService implements ActivityI {

	@Override
	public void createActivity(Activity activity) {
		// TODO Auto-generated method stub
		SessionFactory sf = null; // connects to database using hibernate
		Session s = null;
		Transaction transaction = null;

		try {
			sf = new Configuration().configure().buildSessionFactory();
			s = sf.openSession();
			transaction = s.beginTransaction();
			s.persist(activity);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback(); //

			e.printStackTrace();
		} finally {
			// Close session and session factory
			if (s != null)
				s.close();

			if (sf != null)
				sf.close();

		}

	}

	@Override
	public List<Activity> getAllActivities() {
		SessionFactory sf = null; // connects to database using hibernate
		Session s = null;

		List<Activity> activitiesList = new ArrayList<>();

		try {
			sf = new Configuration().configure().buildSessionFactory();
			s = sf.openSession();

			activitiesList = s.createQuery("FROM Activity", Activity.class).list();

			// Check for duplicates and add only unique activities to the list
//	        for (Activity activity : activitiesList) {
//	            if (!activitiesList.contains(activity)) {
//	                activitiesList.add(activity);
//	            }
//	        }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null)
				s.close();
			if (sf != null)
				sf.close();
		}

		return activitiesList;
	}

	@Override
	public Activity getActivityById(int AcitivityId) {
		SessionFactory sf = null;
		Session s = null;
		Transaction t = null;
		Activity a = null;
		try {
			sf = new Configuration().configure().buildSessionFactory();

			// open new session
			s = sf.openSession();

			// begin transaction
			t = s.beginTransaction();

			// retrieve acitivity by its ID
			a = s.get(Activity.class, AcitivityId);
			// Commit the transaction
			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			// Close session and session factory
			if (s != null) {
				s.close();
			}
			if (sf != null) {
				sf.close();
			}
		}

		return a;
	}

	public boolean isActivityTableNotEmpty() {

		  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        Transaction transaction = session.beginTransaction();
	        
			try  {
	            Long rowCount = (Long) session.createNativeQuery("SELECT COUNT(*) FROM Activity")
	                    .uniqueResult();
	            
	            transaction.commit();
	            return rowCount != null && rowCount > 0;
	        } catch (Exception e) {
	            // Handle exceptions
	        	if (transaction != null && transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            return false;
	        }
			
			finally {
	            session.close();
	            sessionFactory.close();
	        }
	}
	
	public void updateActivityTable(List<Activity> newActivityList) {
	    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	    Session session = sessionFactory.openSession();
	    Transaction transaction = session.beginTransaction();

	    try {
	        // Retrieve existing data from the table
	        List<Activity> existingActivityList = session.createQuery("FROM Activity", Activity.class).list();

	        // Iterate over the new data to be added
	        for (Activity newActivity : newActivityList) {
	            // Check if the new data already exists in the existing data list
	            boolean exists = existingActivityList.stream().anyMatch(existingData -> existingData.equals(newActivity));

	            // If the new data does not exist, add it to the table
	            if (!exists) {
	                session.persist(newActivity);
	            }
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        // Handle exceptions
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	        sessionFactory.close();
	    }
	}


}
