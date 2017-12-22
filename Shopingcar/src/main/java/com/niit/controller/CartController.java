package com.niit.controller;



import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.backendProject.dao.CartDAO;
import com.niit.backendProject.dao.ProdDAO;
import com.niit.backendProject.dao.UserDAO;
import com.niit.backendProject.model.Cart;
import com.niit.backendProject.model.Customer;
import com.niit.backendProject.model.Product;

import sun.awt.AWTAccessor.SystemColorAccessor;


@RequestMapping("/myCart")
@Controller
public class CartController {

	private final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired(required=true)
	private Cart cart;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private Product product;

	@Autowired
	private ProdDAO prodDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private HttpSession session;

	@RequestMapping("/all")
	public String getCart() {
		logger.info("Starting getCart method in CartController");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		String loggedInUsername = username;
		session.setAttribute("numberProducts", cartDAO	.getNumberOfProducts(loggedInUsername));
		session.setAttribute("cartList", cartDAO.getCartList(loggedInUsername));
		session.setAttribute("totalAmount", cartDAO.getTotalAmount(loggedInUsername));
		return "Cart";
	}

	@RequestMapping(value = "/addToCart/{id}")

	public String addToCart(@PathVariable("id") int id, RedirectAttributes redirect, Model model,Principal p) {
		
		System.out.println("addtocart");
		logger.info("Starting addtocart method in CartController");
		try {
			Cart cart = new Cart();
			Product product = prodDAO.getProductId(id);
			System.out.println(product.getName());
			cart.setProductName(product.getName());
			cart.setPrice(product.getPrice());
			cart.setDateAdded(new Date());

			/*if (loggedInUsername == null) {

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String username = auth.getName();
				loggedInUsername = username;

			}*/
			
		String username=p.getName();
		System.out.println("username: "+username);
			cart.setUsername(username);
			cart.setStatus("NEW");
			Customer customer =userDAO.getUserByCustomerName(username);
			System.out.println(customer.getId());
			cart.setCustomer_id(customer.getId());
			Cart existCart = cartDAO.getCartByUsername(username, cart.getProductName());
			System.out.println(product.getName());
			if (existCart != null) {
				int currentQuantity = cartDAO.getQuantity(username, cart.getProductName());
				cart.setId(existCart.getId());
				cart.setQuantity(currentQuantity + 1);
				boolean flag = cartDAO.update(cart);

				if (flag) {

					redirect.addFlashAttribute("success", product.getName() + " " + "Successfully added to cart!");
					session.setAttribute("numberProducts", cartDAO.getNumberOfProducts(username));
					return "redirect:/myCart/all";

				} else {
					redirect.addFlashAttribute("error", "Failed to add product to cart!");
					return "redirect:/CatProduct";
				}
			} else {
				System.out.println("first time product is going to add");
				cart.setQuantity(1);
				boolean flag = cartDAO.save(cart);

				if (flag) {

					redirect.addFlashAttribute("success", product.getName() + " " + "Successfully added to cart!");
					session.setAttribute("numberProducts", cartDAO.getNumberOfProducts(username));
					return "redirect:/myCart/all";

				} else {
					redirect.addFlashAttribute("error", "Failed to add product to cart!");
					return "redirect:/CatProduct";
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception occured " + e);
			model.addAttribute("catchError", "Server is not responding please try again letter.");
			return "Cart";
		}
	}

	@RequestMapping("/deleteItem/{id}")
	public String deleteCartItem(@PathVariable("id") int id, Model model, RedirectAttributes redirect) {
		logger.info("Starting deleteCartItem method in CartController");
		try {
			Cart cart = cartDAO.getCartById(id);

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();

			int checkQ = cartDAO.getQuantity(username, cart.getProductName());

			if (checkQ > 1) {
				cart.setQuantity(checkQ - 1);
				cartDAO.update(cart);
				redirect.addFlashAttribute("success", "Cart updated successfully.");
				return "redirect:/myCart/all";
			} else {
				// cart.setStatus("OLD");
				cartDAO.delete(id);
				redirect.addFlashAttribute("success", "Item removed successfully.");
				return "redirect:/myCart/all";
			}
		} catch (Exception e) {
			logger.error("Exception occured " + e);
			model.addAttribute("catchError", "Server is not responding please try again letter.");
			return "error";
		}
	}

	@RequestMapping("/clearCart")
	public String clearCart(RedirectAttributes redirect, Model model) {
		logger.info("Starting clearCart method in CartController");
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			int flag = cartDAO.clearCart(username);

			if (flag >= 1) {
				redirect.addFlashAttribute("success", "All Items removed successfully.");
				return "redirect:/myCart/all";
			} else {
				redirect.addFlashAttribute("error", "Failed to clear cart!");
				return "redirect:/myCart/all";
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception occured " + e);
			model.addAttribute("catchError", "Server is not responding please try again letter.");
			return "error";
		}
	}

}
