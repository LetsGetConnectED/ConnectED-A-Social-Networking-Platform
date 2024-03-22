package com.connected.connection.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Password is required")
    private String password;

    public User() {
        super();
    }

    public User(Long id, String username, String name, String password) {
        super();
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



/*
 * package com.connected.connection.model;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * import jakarta.validation.constraints.NotBlank;
 * 
 * 
 * @Entity public class User { public User() { super(); // TODO Auto-generated
 * constructor stub }
 * 
 * 
 * @Override public String toString() { return "User [id=" + id + ", username="
 * + username + "]"; }
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 * 
 * @NotBlank(message = "Username is required") private String username;
 * 
 * public User(Long id, String username) { super(); this.id = id; this.username
 * = username; }
 * 
 * 
 * public Long getId() { return id; } public void setId(Long id) { this.id = id;
 * } public String getUsername() { return username; } public void
 * setUsername(String username) { this.username = username; }
 * 
 * }
 */