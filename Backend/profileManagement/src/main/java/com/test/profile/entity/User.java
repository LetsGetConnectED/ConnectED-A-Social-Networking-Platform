package com.test.profile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_user")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long u_id;
private String name;
public Long getU_id() {
	return u_id;
}
public void setU_id(Long u_id) {
	this.u_id = u_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}
public String getWork_exp() {
	return work_exp;
}
public void setWork_exp(String work_exp) {
	this.work_exp = work_exp;
}
public Long getMob() {
	return mob;
}
public void setMob(Long mob) {
	this.mob = mob;
}
private String gender;
private String bio;
private String address;
private String skill;
private String work_exp;
private Long mob;

}
