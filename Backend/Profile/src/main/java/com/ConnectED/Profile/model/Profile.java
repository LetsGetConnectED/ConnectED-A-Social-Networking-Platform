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

//@Getter
//@Setter
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

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public Blob getImage() {
			return image;
		}

		public void setImage(Blob image) {
			this.image = image;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
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

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBio() {
			return bio;
		}

		public void setBio(String bio) {
			this.bio = bio;
		}

		public String getEdu() {
			return edu;
		}

		public void setEdu(String edu) {
			this.edu = edu;
		}

		public Long getMob() {
			return mob;
		}

		public void setMob(Long mob) {
			this.mob = mob;
		}

		public String getSkill() {
			return skill;
		}

		public void setSkill(String skill) {
			this.skill = skill;
		}

		public String getOccupation() {
			return occupation;
		}

		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}

		public String getWork_exp() {
			return work_exp;
		}

		public void setWork_exp(String work_exp) {
			this.work_exp = work_exp;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
	



}
	    


