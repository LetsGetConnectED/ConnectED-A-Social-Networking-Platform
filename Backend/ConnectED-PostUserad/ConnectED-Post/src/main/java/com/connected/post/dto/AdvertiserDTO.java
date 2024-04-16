package com.connected.post.dto;
public class AdvertiserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public AdvertiserDTO(Long id, String email, String firstName, String lastName) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "AdvertiserDTO [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
	public AdvertiserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
