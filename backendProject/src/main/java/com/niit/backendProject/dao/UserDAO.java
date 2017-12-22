package com.niit.backendProject.dao;

import java.util.List;

import com.niit.backendProject.model.Customer;
import com.niit.backendProject.model.User;

public interface UserDAO {

	//public boolean addUser(User u);
	//List<User>getUser();
	
	public List<Customer> getAllCustomers();

	public boolean save(Customer customer);

	public boolean update(Customer customer);

	//public boolean delete(int id);

	public Customer getUserById(int id);

	public User getUsersById(int id);

	public Customer getUserByUserName(String username);

	public Customer getUserByCustomerName(String name);

	//public String getUserRole(String username);

	public boolean getStatus(int id);

	public int changeStatus(int id)  ;








}



