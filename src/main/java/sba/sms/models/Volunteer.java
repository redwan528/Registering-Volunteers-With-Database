package sba.sms.models;

import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "volunteer")
public class Volunteer {
	@Id // gives email the primary key
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@Column(name = "password", nullable = false, length = 50)
	private String password;
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH, }, fetch = FetchType.EAGER)
	@JoinTable(name = "volunteer_activities", 
	joinColumns = @JoinColumn(name = "Fk_volunteer_email"),
	inverseJoinColumns = @JoinColumn(name = "activities_id"),
	inverseForeignKey = @ForeignKey(name = "Fk_activities_id"))

	private List<Activity> activities;

	public Volunteer() {
	}; // •no args constructor

	public Volunteer(String email, String name, String password, List<Activity> activities) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.activities = activities;
	}
	// - all args constructor

	public Volunteer(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activities, email, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Volunteer other = (Volunteer) obj;
		return Objects.equals(activities, other.activities) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Volunteer [email=" + email + ", name=" + name + ", password=" + password + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

}