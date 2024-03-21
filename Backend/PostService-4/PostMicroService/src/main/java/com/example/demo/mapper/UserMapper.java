package com.example.demo.mapper;

import com.example.demo.Model.User;
import com.example.demo.dto.UserDTO;

public class UserMapper {
	 public static UserDTO toDTO(User user) {
	        UserDTO dto = new UserDTO();
	        dto.setId(user.getId());
	        dto.setFirstName(user.getFirstName());
	        dto.setLastName(user.getLastName());
	        return dto;
	    }
}
