package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.backendProject.dao.CustomerOrderDAO;
import com.niit.backendProject.dao.UserDAO;
import com.niit.backendProject.model.Customer;
@Controller
public class CheckoutController {
	private final Logger logger = LoggerFactory.getLogger(CheckoutController.class);

	@Autowired
	private Customer customer;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CustomerOrderDAO customerOrderDAO;

	@Autowired
	private HttpSession session;

	@RequestMapping("/order")
	public String createOrder() {
		// CustomerOrder customerOrder = new CustomerOrder();
		System.out.println("Starting createOrder method in CheckoutControlle");
		logger.info("Starting createOrder method in CheckoutController");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		String loggedInUsername = username;

		Customer customer = userDAO.getUserByUserName(loggedInUsername);
		// customerOrder.setCustomer(customer);
		// customerOrder.setBillingAddress(customer.getBillingAddress());
		// customerOrder.setShippingAddress(customer.getShippingAddress());
		
		System.out.println(customer.getId());
		logger.info("Starting checkout Spring Web Flow...");
		return "redirect:/checkout?userId=" + customer.getId();
	}

}


