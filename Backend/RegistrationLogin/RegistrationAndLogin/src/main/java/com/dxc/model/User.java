
package com.dxc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="USER")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid; 
    
    private String username;
    private String useremail;
    private String userpassword;
    
    @Enumerated(EnumType.STRING)
    private Role role;
<<<<<<< HEAD
    
    
=======
    private String token;
>>>>>>> 9803b956d5e3410726c9811648a72b8ca5376f2e
    public User() {
        super();
    }
    
<<<<<<< HEAD
    public User(String username, String useremail, String userpassword, Role role) {
=======
    public User(int userid, String username, String useremail, String userpassword, Role role,String token) {
>>>>>>> 9803b956d5e3410726c9811648a72b8ca5376f2e
        super();
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.role = role;
        this.token =token;
        
    }
    
    public int getUserid() {
        return userid;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    
    @Override
    public String getUsername() {
        return useremail;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public String getPassword() {
        return userpassword;
    }

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
}
