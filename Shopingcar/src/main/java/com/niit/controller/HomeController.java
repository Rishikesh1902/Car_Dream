package com.niit.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.backendProject.dao.CartDAO;
import com.niit.backendProject.dao.CategoryDAO;
import com.niit.backendProject.dao.ProdDAO;
import com.niit.backendProject.dao.SupDAO;
import com.niit.backendProject.dao.UserDAO;
import com.niit.backendProject.model.BillingAddress;
import com.niit.backendProject.model.Cart;
import com.niit.backendProject.model.Category;
import com.niit.backendProject.model.Customer;
import com.niit.backendProject.model.Product;
import com.niit.backendProject.model.ShippingAddress;
import com.niit.backendProject.model.User;

@Controller

public class HomeController {
	@Autowired
	UserDAO userDAO;
	@Autowired 
	SupDAO supDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	HttpSession httpSession;
	@Autowired
	ProdDAO prodDAO;
	@Autowired
	CartDAO cartDAO;
	
	
	

	@RequestMapping(value={"/","Home"})
	public String goHome(){
		httpSession.setAttribute("listSup",supDAO.getAllSupplier());
		httpSession.setAttribute("listCategory",categoryDAO.getAllCategory());
		httpSession.setAttribute("listproduct",prodDAO.getAllProduct() );
		
		return "Home";
		
	}
	@RequestMapping("/Login") 
	public String Log(){
		return "Login";
	}
	
	

/*@RequestMapping("/logg")
public String lon(@RequestParam("name")String x,@RequestParam("password")String y){
	if(x.equals("Aashish") && y.equals("716"))
	{
		return "logg";	
	}
	else
	{
		return "Home";
	}
	
}*/
@RequestMapping("/Signup")
public String signup(Model model)
{ 
	//model.addAttribute("user",new User());
	model.addAttribute("customer",new Customer());
	
	//model.addAttribute("userList",userDAO.getUser());
	
	return "Signup";
	
}
@RequestMapping("/addUser")
public String addUser(@ Valid @ModelAttribute("customer") Customer u,BindingResult result,Model model)
{ 
	
	if(result.hasErrors()){
		
		model.addAttribute("msg","Please fill details properly");
		return "Signup";
		
	}
	else
	{
	if(userDAO.save(u))
	{
		ShippingAddress shippingAddress = new ShippingAddress();
	BillingAddress billingAddress = new BillingAddress();
	Customer customer = new Customer();

	customer.setBillingAddress(billingAddress);
	customer.setShippingAddress(shippingAddress);
	model.addAttribute("customer", customer);
	}
	}
	return "Signup";
}

	/*RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {
		logger.info("Executing Registration page...");

				return "register";
	}

	// @PostMapping("/register")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
		logger.info("Starting saveUser method in CustomerController");
		if (result.hasErrors()) {
			logger.error("Binding Result has error");
			model.addAttribute("error", "Binding Result has error");
			return "error";
		}

		List<Customer> usersList = customerService.getAllCustomers();

		try {
			logger.info("Saving user...");
			for (int i = 0; i < usersList.size(); i++) {
				if (customer.getEmail().equalsIgnoreCase(usersList.get(i).getEmail())) {
					model.addAttribute("emailError", "This email is already exists");
					logger.error("Email is already exist");
					return "register";
				}

				if (customer.getUsername().equalsIgnoreCase(usersList.get(i).getUsername())) {
					model.addAttribute("usernameError", "This username is already exists");
					logger.error("Username is already exists");
					return "register";
				}

				if (customer.getMobileno().equalsIgnoreCase(usersList.get(i).getMobileno())) {
					model.addAttribute("mobileError", "Mobile number is already exists");
					logger.error("Mobile number is already exists");
					return "register";
				}
			}

			boolean flag = userDAO.save(customer);

			if (flag == true) {

				model.addAttribute("success", "Registered successfully");
				logger.info("User registered successfully");
				return "login";
			} else {
				model.addAttribute("error", "Registration Failed, Please try again !");
				logger.error("Registration failed");
				return "register";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception occured " + e);
			model.addAttribute("catchError", "Server is not responding please try again letter.");
			return "error";
		}

	}


}*/

			
		
	@RequestMapping("/LogOut")
	public ModelAndView logout(){
		ModelAndView mv=new ModelAndView("Login");
		mv.addObject("msg","Sucessfully Logout");
		return mv;
	}
	
		
	@RequestMapping(value="/logg",method=RequestMethod.POST)
	public String validate(HttpServletRequest httpServletRequest,Principal p){
	 httpSession=	httpServletRequest.getSession(true);
	httpSession.setAttribute("loggedInUser", true);
	httpSession.setAttribute("loggedInUsername",userDAO.getUserByUserName(p.getName()));
	httpSession.setAttribute("loggedInName",p.getName());
	Cart cart=new Cart();
	httpSession.setAttribute("numberProducts", cartDAO.getNumberOfProducts(p.getName()));
	httpSession.setAttribute("cartList", cartDAO.getCartList(p.getName()));
	
	 
	 
	 return "Home";
		
		
		
	}
		

}

