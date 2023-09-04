package com.codewithdurgesg.blog.services;

import java.util.List;

import com.codewithdurgesg.blog.payloads.UserDTO;

public interface UserService {
	UserDTO registerNewUser(UserDTO userDto);
	
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer id);
	UserDTO getUserById(Integer id);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer userId);

}
