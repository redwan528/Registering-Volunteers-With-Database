# AD - SBA - Core Java/Hibernate/JUnit - Practice  Version

## Volunteer Management System
### Business Requirement:
Your task is to create a basic Volunteer Management System
where volunteers can register for activities, and view the
activities assigned to them.
### Work-Flow:
Only volunteers with the right credentials can log in.
Otherwise, a message is displayed stating: “Wrong Credentials”.
Valid volunteers are able to see the activities they are registered for.
Valid volunteers are able to register for any Activity in the system as
long as they are not already registered.

Note: Do not change code in main method located in App.java

### Maven Project Dependencies:

- [Amazon Corretto 11 JDK](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) 
- [Hibernate](https://mvnrepository.com/artifact/org.hibernate/hibernate-core) 
- [MySQL](https://mvnrepository.com/artifact/mysql/mysql-connector-java)

- [Project lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
- [Junit jupiter api](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)
- [Junit jupiter engine](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine)
- [Junit jupiter param](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params)
- [Junit platform suite](https://mvnrepository.com/artifact/org.junit.platform/junit-platform-suite-engine)
- [Junit platform runner](https://mvnrepository.com/artifact/org.junit.platform/junit-platform-runner)


#### Requirement 1 - Models:
Models requires:
- no args constructor 
- all args constructor
- required args constructor
- setters and getter
- toString (exclude collections to avoid infinite loops)
- override equals and hashcode methods (don't use lombok here)
- helper methods
##### Volunteer (`@Table(name = "volunteer")`):
| Field    | Datatype        | Description                 | Database attributes `@Column()`                                                                                                                                                                                    | 
|----------|-----------------|-----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
 | email    | String          | Volunteer’s unique identifier | Primary key, 50 character limit, name `email`                                                                                                                                                                      |
 | name     | String          | Volunteer’s name              | 50 character limit, not null, name `name`                                                                                                                                                                          |
 | password | String          | Volunteer’s password          | 50 character limit not null, name `password`                                                                                                                                                                       |
 | activities  | List\<Activity> | Volunteer activities list        | Join table strategy name `Volunteer_activities` , name of volunteer primary key column `volunteer_email` and inverse primary key (activities) column `activities_id` , fetch type `eager`, cascade all except remove |

##### Activity (`@Table(name = "activity")`):

| Field      | Datatype          | Description              | Database attributes `@Column()`                                   | 
|------------|-------------------|--------------------------|-------------------------------------------------------------------|
| id         | int               | Activity unique identifier | Primary key                                                       |
| name       | String            | Activity name              | 50 character limit, not null                        |
| volunteers   | List\<volunteers> | volunteers  list     | fetch type `eager`, cascade all except remove, mappedBy `activities` | 

---
#### Requirement 2 - Data Access Object  (dao) interfaces:

##### VolunteerI:
| Abstract method             | Return type    | Parameters                    | Description                                                                                                     | 
|-----------------------------|----------------|-------------------------------|-----------------------------------------------------------------------------------------------------------------|
| createVolunteer             | void           | Volunteer                       | persist Volunteer to database, also handle commit,rollback, and exceptions                                        |
| getAllVolunteers            | List\<Volunteer> | None                          | return all volunteers from database, also handle commit,rollback, and exceptions                                  |
| getVolunteerByEmail         | Volunteer        | String email                  | return Volunteer if exists, also handle commit,rollback, and exceptions                                           |
| validateVolunteer           | boolean        | String email, String password | match email and password to database to gain access to activities, also handle commit,rollback, and exceptions     |
| registerVolunteerToActivity | void           | String email, int ActivityId    | register a Activity to a Volunteer (collection to prevent duplication), also handle commit,rollback, and exceptions |
| getVolunteerActivities      | List\<Activity>  | String email                  | get all the Volunteer activities list (use native query), also handle commit,rollback, and exceptions                | 

##### ActivityI:
| Abstract method  | Return type     | Parameters                    | Description                                                                                                     | 
|------------------|-----------------|-------------------------------|-----------------------------------------------------------------------------------------------------------------|
| createActivity   | void            | Activity                        | persist Activity to database, also handle commit,rollback, and exceptions                                         |
| getAllActivities | List\<Activity> | None                          | return all activities from database, also handle commit,rollback, and exceptions                                   |
| getActivityById  | Activity        | int ActivityId                  | return Activity if exists, also handle commit,rollback, and exceptions                                            |
---
#### Requirement 3 - Service layer:
implement interfaces:
- VolunteerService
- ActivityService
---
#### Requirement 4 - Utility classes:
- hibernate configuration session factory helper
- data initializer helper (dummy data dump)
---
#### Requirement 5 - JUnit:
- Write at least one junit test

