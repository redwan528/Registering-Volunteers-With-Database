//package sba.sms.services;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import sba.sms.dao.VolunteerI;
//import sba.sms.models.Activity;
//import sba.sms.models.Volunteer;
//
//public class VolunteerService implements VolunteerI {
//
//	@Override
//	public void createVolunteer(Volunteer volunteer) {
//		// TODO Auto-generated method stub
//		SessionFactory sf = null; // connects to database using hibernate
//		Session s = null;
//		Transaction transaction = null;
//
//		try {
//
//			sf = new Configuration().configure().buildSessionFactory();
//			s = sf.openSession();
//			transaction = s.beginTransaction();
//			s.persist(volunteer);
//			transaction.commit();
//
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback(); //
//			}
//			e.printStackTrace();
//		}
//
//		finally {
//			// Close session and session factory
//			if (s != null) {
//				s.close();
//			}
//			if (sf != null) {
//				sf.close();
//			}
//		}
//
//	}
//
//	@Override
//	public List<Volunteer> getAllVolunteers() {
//		SessionFactory sf = null; // connects to database using hibernate
//		Session s = null;
//
//		List<Volunteer> volunteersList = new ArrayList<>();
//
//		try {
//			sf = new Configuration().configure().buildSessionFactory();
//			s = sf.openSession();
//			volunteersList = s.createQuery("FROM VOLUNTEERS", Volunteer.class).list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (s != null)
//				s.close();
//			if (sf != null)
//				sf.close();
//		}
//
//		return volunteersList;
//	}
//
//	@Override
//	public Volunteer getVolunteerByEmail(String email) {
//		SessionFactory sf = null; // connects to database using hibernate
//		Session s = null;
//
//		Volunteer emailOfVolunteer = new Volunteer();
//
//		try {
//			sf = new Configuration().configure().buildSessionFactory();
//			s = sf.openSession();
//			emailOfVolunteer = s.createQuery("FROM VOLUNTEERS WHERE EMAIL =:email", Volunteer.class)
//					.setParameter("email", email).uniqueResult();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (s != null)
//				s.close();
//			if (sf != null)
//				sf.close();
//		}
//		return emailOfVolunteer;
//	}
//
//	@Override
//	public boolean validateVolunteer(String email, String password) {
//		SessionFactory sf = null; // connects to database using hibernate
//		Session s = null;
//		Transaction t = null;
//		Volunteer volunteer = new Volunteer();
//
//		try {
//			sf = new Configuration().configure().buildSessionFactory();
//			s = sf.openSession();
//			t = s.beginTransaction();
//
//			volunteer = s.createQuery("FROM VOLUNTEERS WHERE EMAIL =: email AND PASSWORD=:password", Volunteer.class)
//					.setParameter("email", email).setParameter("password", password).uniqueResult();
//
//			if (volunteer != null)
//				
//			return true;
//			t.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//		finally {
//			if (s != null)
//				s.close();
//			if (sf != null)
//				sf.close();
//		}
//
//		return false;
//	}
//
//	@Override
//	public void registerVolunteerToActivity(String email, int ActivityId) {
//		SessionFactory sessionFactory = null;
//		Session session = null;
//		Transaction transaction = null;
//
//		try {
//			// create session factory
//			sessionFactory = new Configuration().configure().buildSessionFactory();
//
//			// open a new session
//			session = sessionFactory.openSession();
//
//			// begin transaction
//			transaction = session.beginTransaction();
//
//			// Retrieve the Volunteer by email
//			Volunteer volunteer = session.createQuery("FROM Volunteer WHERE email = :email", Volunteer.class)
//					.setParameter("email", email).uniqueResult();
//
//			// Retrieve the Activity by ID
//			Activity activity = session.get(Activity.class, ActivityId);
//
//			if (volunteer != null && activity != null) {
//				// Register the Activity to the Volunteer (preventing duplication)
//				if (!volunteer.getActivities().contains(activity)) {
//					volunteer.getActivities().add(activity);
//				}
//			}
//
//			// Commit the transaction
//			transaction.commit();
//
//		} catch (Exception e) {
//			// Handle exceptions
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		} finally {
//			// Close session and session factory
//			if (session != null) {
//				session.close();
//			}
//			if (sessionFactory != null) {
//				sessionFactory.close();
//			}
//		}
//
//	}
//
//	@Override
//	public List<Activity> getVolunteerActivities(String email) {
//		// TODO Auto-generated method stub
//		SessionFactory sessionFactory = null;
//		Session session = null;
//		Transaction transaction = null;
//		List<Activity> activities = null;
//
//		try {
//			// create the sessionfactory
//			sessionFactory = new Configuration().configure().buildSessionFactory();
//
//			// Open a new session
//			session = sessionFactory.openSession();
//
//			// begin transaction
//			transaction = session.beginTransaction();
//
//			// Retrieve the Volunteer activities using a native query
//			String nativeQuery = "SELECT a.* FROM Activity a INNER JOIN Volunteer_Activity va "
//					+ "ON a.activity_id = va.activity_id INNER JOIN Volunteer v yON va.volunteer_id = v.volunteer_id WHERE v.email = :email";
//			/*
//			 * SELECT a.* FROM activity a JOIN volunteer_activities va ON a.id = va.activities_id "
//					+ "JOIN volunteer v ON va.volunteer_email = v.email WHERE v.email = :email
//			 */
//			activities = session.createNativeQuery(nativeQuery, Activity.class).setParameter("email", email)
//					.getResultList();
//
//			// Commit the transaction
//			transaction.commit();
//		} catch (Exception e) {
//			// Handle exceptions
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		} finally {
//			// Close session and session factory
//			if (session != null) {
//				session.close();
//			}
//			if (sessionFactory != null) {
//				sessionFactory.close();
//			}
//		}
//
//		return activities;
//	}
//
//}
package sba.sms.services;
import jakarta.persistence.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import sba.sms.dao.VolunteerI;
import sba.sms.models.Activity;
import sba.sms.models.Volunteer;
import java.util.ArrayList;
import java.util.List;
public class VolunteerService implements VolunteerI {
    @Override
    public void createVolunteer(Volunteer v) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction t= session.beginTransaction();
        try (sessionFactory; session) {
            session.persist(v);
            // Commit the transaction
            t.commit();
        } catch (Exception e) {
            // Handle exceptions
            if (t != null && t.isActive()) {
                t.rollback();
            }
            // handle the exception accordingly
            e.printStackTrace();
        }
    }
    @Override
    public List<Volunteer> getAllVolunteers() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t= session.beginTransaction();
        List<Volunteer> volunteers = new ArrayList<>();
        try {
            volunteers = session.createQuery("FROM Volunteer", Volunteer.class).list();
            t.commit();
        } catch (Exception e) {
            if (t != null && t.isActive())
                t.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
        return volunteers;
    }
    @Override
    public Volunteer getVolunteerByEmail(String email) {
        SessionFactory factory= new Configuration().configure().buildSessionFactory();
        Session session= factory.openSession();
        Transaction t= session.beginTransaction();
        Volunteer volunteerbyEmail = new Volunteer();
        try{
            volunteerbyEmail = session.createQuery("From Volunteer where email = :email", Volunteer.class)
                    .setParameter("email", email).uniqueResult();
            t.commit();
        }catch (Exception e){
            if (t != null && t.isActive())
                t.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
        return volunteerbyEmail;
    }
    @Override
    public boolean validateVolunteer(String email, String password) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction t= session.beginTransaction();
         try {
             Volunteer volunteer = new Volunteer();
             volunteer =session.createQuery("From Volunteer where email = :email and password = :password", Volunteer.class)
                     .setParameter("email", email).setParameter("password", password).uniqueResult();
             if(volunteer != null)
                 return true;
                t.commit();
         } catch (Exception e) {
             if (t != null && t.isActive())
                 t.rollback();
             e.printStackTrace();
         } finally {
             session.close();
             sessionFactory.close();
         }
        return false;
    }
    @Override
    public void registerVolunteerToActivity(String email, int ActivityId) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Retrieve the Volunteer by email
            Volunteer volunteer = session.createQuery("FROM Volunteer WHERE email = :email", Volunteer.class)
                    .setParameter("email", email).uniqueResult();
            // Retrieve the Activity by ID
            Activity activity = session.get(Activity.class, ActivityId);
            if (volunteer != null && activity != null) {
                // Register the Activity to the Volunteer (preventing duplication)
                if (!volunteer.getActivities().contains(activity)) {
                    volunteer.getActivities().add(activity);
                }
            }
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Handle exceptions
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    @Override
    public List<Activity> getVolunteerActivities(String email) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Activity> activitiesList = new ArrayList<>();
        try {
            // Perform the database query to retrieve the Volunteer activities using a native query
            String nativeQuery = "SELECT a.* FROM Activity a " +
					"INNER JOIN volunteer_activities va ON a.id = va.activities_id " +
                    "INNER JOIN Volunteer v ON va.Fk_volunteer_email = v.email " +
                    "WHERE v.email = :email";
          activitiesList = session.createNativeQuery(nativeQuery, Activity.class)
                    .setParameter("email", email)
                    .list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return activitiesList;
    }
    
    //helper method
public boolean isVolunteerTableNotEmpty() {
    	
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
		try  {
            Long rowCount = (Long) session.createNativeQuery("SELECT COUNT(*) FROM Volunteer")
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

//helper method
public void updateVolunteerTable(List<Volunteer> newVolunteerList) {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    try {
        // Retrieve existing data from the table
        List<Volunteer> existingVolunteerList = session.createQuery("FROM Volunteer", Volunteer.class).list();

        // Iterate over the new data to be added
        for (Volunteer newVolunteer : newVolunteerList) {
            // Check if the new data already exists in the existing data list
            boolean exists = existingVolunteerList.stream().anyMatch(existingData -> existingData.equals(newVolunteer));

            // If the new data does not exist, add it to the table
            if (!exists) {
                session.persist(newVolunteer);
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
