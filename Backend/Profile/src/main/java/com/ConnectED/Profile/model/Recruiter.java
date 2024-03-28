package com.ConnectED.Profile.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "profilemangement_Recr")
public class Recruiter {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
		@Lob
		@JsonSerialize(using = BlobSerializer.class)
	    private Blob image;
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
	    
	    @Transient
	    private String imageBase64;

	    public String getImageBase64() {
	        if (image != null) {
	            try {
	                byte[] bytes = image.getBytes(1, (int) image.length());
	                return Base64.getEncoder().encodeToString(bytes);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }

	    public void setImageBase64(String imageBase64) {
	        this.imageBase64 = imageBase64;
	    }
}
