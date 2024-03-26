package com.example.demo.Model;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="id")
public class User {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
      
	
	private int id;
	@Column(unique=true)
	private String email;
	private String firstName;
    private String lastName;
//    @OneToMany
//    @JsonIgnore
//    private List<Post> posts;
    
   



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	
   
















public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}











public User(int id, String email, String firstName, String lastName) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}







@Override
public String toString() {
	return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
}



public User() {
		
	}

}
