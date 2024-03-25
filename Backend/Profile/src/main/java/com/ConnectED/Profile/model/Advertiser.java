package com.ConnectED.Profile.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Table(name = "profilemangement_Advt")
	public class Advertiser {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	    private long id;
		@Column(unique = true)
	    private String email;
	    private String userName;
	    private String firstName;
	    private String lastName;
	    private String gender;
	    private String bio;
	    private String companyName;
	    private String mob;
	    private String city;
	    private String state;
	    private String country;

	}
