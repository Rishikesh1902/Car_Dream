package com.niit.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.backendProject.dao.CategoryDAO;
import com.niit.backendProject.model.Category;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping("/CategoryDetail")
	public String category(Model model)
	{ 
		model.addAttribute("category",new Category());
	model.addAttribute("listCategory",categoryDAO.getAllCategory());
		return "CategoryDetail";
	}
	@RequestMapping("/addCategory")
	public String addCategory(@Valid @ModelAttribute("category")Category c,BindingResult result,Model model){
		if (result.hasErrors()){
			
			model.addAttribute("msg", "Please Fill the Details Correctly");
		//ModelAndView mv=new ModelAndView("CategoryDetail");
			return "CategoryDetail";
		}
	/*	
	 * System.out.println(c.getCatId());
		
		System.out.println(c.getCname());
		System.out.println(c.getCdescp());
		*/
		else{
		if(c.getCatId()==null || c.getCatId().isEmpty())
		{
			if(categoryDAO.addCategory(c))
			{
			model.addAttribute("msg"," Catgory added Sucessfully");
			}
			else
			{
				model.addAttribute("msg","not successsfully register");
			}
			
		}
		else
		{
	    System.out.println("category is updating");
	    if(categoryDAO.update(c))
		{
		model.addAttribute("msg"," Catgory updated Sucessfully");
		}
		else
		{
			model.addAttribute("msg","not updated");
		}			
	}
		
		
		}
		return "redirect:/CategoryDetail";
		
		
		
	
		
	}
	@RequestMapping("/updateCategory/{catId}")
	//public String editCategory(@PathVariable("catId") String catId,Model model){
	//public String editCategory(@PathVariable() String id,Model model){ it gives error
		public String updateCategory(@PathVariable("") String catId,Model model){
		model.addAttribute("category",categoryDAO.getCategoryId(catId));
		model.addAttribute("listCategory",categoryDAO.getAllCategory());
		return "CategoryDetail";
	}
	
	
	@RequestMapping("/deleteCategory/{catId}")
	public String deleteCategory(@PathVariable("catId") String catId,Model model)
		{
			categoryDAO.deleteCat(catId);
			
		return "redirect:/CategoryDetail";
		}
	
	
}
