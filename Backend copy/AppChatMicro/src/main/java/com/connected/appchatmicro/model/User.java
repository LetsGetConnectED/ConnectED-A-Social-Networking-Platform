package com.connected.appchatmicro.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class User {
        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long userId;
        @NotBlank(message = "Username is required")
        private String username;
        @NotBlank(message = "Password is required")
        private String password;
        @Email
        @NotEmpty(message = "Email is required")
        @Column(nullable = false,unique = true)
        private String email;

        @NotBlank(message = "Phone is required")
        private String phone;
        @NotBlank(message = "First Name is required")
        private String first_name;
        @NotBlank(message = "Last Name is required")
        private String last_name;
        @NotBlank(message = "Company Name is required")
        private String company_name;

        @Nullable
        private String profile_picture;

        @Override
		public String toString() {
			return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
					+ ", phone=" + phone + ", first_name=" + first_name + ", last_name=" + last_name + ", company_name="
					+ company_name + ", profile_picture=" + profile_picture + ", created=" + created + ", enabled="
					+ enabled + "]";
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		private Instant created;
        private boolean enabled;
		public User(Long userId, @NotBlank(message = "Username is required") String username,
				@NotBlank(message = "Password is required") String password,
				@Email @NotEmpty(message = "Email is required") String email,
				@NotBlank(message = "Phone is required") String phone,
				@NotBlank(message = "First Name is required") String first_name,
				@NotBlank(message = "Last Name is required") String last_name,
				@NotBlank(message = "Company Name is required") String company_name, String profile_picture,
				Instant created, boolean enabled) {
			super();
			this.userId = userId;
			this.username = username;
			this.password = password;
			this.email = email;
			this.phone = phone;
			this.first_name = first_name;
			this.last_name = last_name;
			this.company_name = company_name;
			this.profile_picture = profile_picture;
			this.created = created;
			this.enabled = enabled;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		public String getCompany_name() {
			return company_name;
		}
		public void setCompany_name(String company_name) {
			this.company_name = company_name;
		}
		public String getProfile_picture() {
			return profile_picture;
		}
		public void setProfile_picture(String profile_picture) {
			this.profile_picture = profile_picture;
		}
		public Instant getCreated() {
			return created;
		}
		public void setCreated(Instant created) {
			this.created = created;
		}
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
}


