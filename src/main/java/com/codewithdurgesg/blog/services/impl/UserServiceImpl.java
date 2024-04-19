package com.codewithdurgesg.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.codewithdurgesg.blog.config.AppConstant;
import com.codewithdurgesg.blog.entities.Role;
import com.codewithdurgesg.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesg.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithdurgesg.blog.payloads.UserDTO;
import com.codewithdurgesg.blog.repositories.UserRepo;
import com.codewithdurgesg.blog.services.UserService;
import com.codewithdurgesg.blog.entities.User;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDTO registerNewUser(UserDTO userDto) {
		User user=this.modelMapper.map(userDto,User.class);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role role=this.roleRepo.findById(AppConstant.NORMAL_USER).get();
		user.getRole().add(role);
		User newUser=this.userRepo.save(user);

		return this.modelMapper.map(newUser,UserDTO.class);
	}

	@Override
	public UserDTO createUser(UserDTO user) {
		User user1=this.dtoToUser(user);
//		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser=userRepo.save(user1);

		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userId) {
		User u2=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," Id ",userId));
		u2.setName(user.getName());
		u2.setEmail(user.getEmail());
		u2.setPassword(user.getPassword());
		u2.setAbout(user.getAbout());
		
		User updatedUser=this.userRepo.save(u2);
		UserDTO ud1=this.userToDto(updatedUser);
		return ud1;
	}

	@Override
	public UserDTO getUserById(Integer id) {
		User u3=this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User"," Id ",id));
		
		UserDTO ud2=this.userToDto(u3);
		return ud2;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> u=userRepo.findAll();
		List<UserDTO> u1=u.stream().map(e ->userToDto(e)).collect(Collectors.toList());
		
		return u1;
	}

	@Override
	public void deleteUser(Integer userId) {
		User u=this.userRepo.findById(userId).orElseThrow();
		userRepo.delete(u);
		
	}
	
	private User dtoToUser(UserDTO userDto) {
		
//		User user=new User();
//		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		User user=this.modelMapper.map(userDto, User.class);
		
		return user;
	}
	
	private UserDTO userToDto(User user) {
		
//		UserDTO userDto=new UserDTO();
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		UserDTO userDto=this.modelMapper.map(user, UserDTO.class);
		
		return userDto;
	}

}
