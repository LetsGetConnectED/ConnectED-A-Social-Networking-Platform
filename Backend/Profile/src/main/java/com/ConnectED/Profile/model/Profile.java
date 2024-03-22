package com.ConnectED.Profile.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profilemangement")
public class Profile {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    //@Lob
	    @JsonSerialize(using = BlobSerializer.class)
	    private Blob image;

	    private Date date = new Date();
	    
	    @Column(unique = true)
	    private String email;
	    private String userName;
	    private String firstName;
	    private String lastName;
	    private String gender;
	    private String bio;
	    private String edu;
	    private Long mob;
	    private String skill;
	    private String occupation;
	    private String work_exp;
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
	    
