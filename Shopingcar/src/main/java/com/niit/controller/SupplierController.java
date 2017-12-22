package com.niit.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.backendProject.dao.SupDAO;

import com.niit.backendProject.model.Supplier;


@Controller
public class SupplierController {
	
	
	@Autowired
	SupDAO supDAO;
	@Autowired
	Supplier supplier;
	
	@Autowired
	HttpSession httpSession;
	@RequestMapping("/supplier")
	public String category(Model model)
	{ 
		model.addAttribute("supplier",new Supplier());
		
		
		//If comment this then our database values will not be shown on page
		model.addAttribute("listSup",supDAO.getAllSupplier());
		
		return "supplier";
	}
	@RequestMapping(value="/addSupplier",method= RequestMethod.POST)
	public String addSupplier(@Valid @ModelAttribute("supplier")Supplier s,BindingResult result,Model model){
		
		
		if(result.hasErrors()){
			
			model.addAttribute("msg","Please fill details properly");
			return "supplier";
			
		}
		else{
		if(supDAO.getSupplierId(s.getSupId())==null)
			
		{

			if(supDAO.addSuppiler(s))
			{
				model.addAttribute("msg","Add Supplier");
			}
			else
			{
				model.addAttribute("msg","not successsfully register");
			}
		}
		else
		{
			supDAO.updateSup(s);
		}
		}
		
		return "redirect:/supplier";
	}
@RequestMapping("/updateSupplier/{supId}")
public String editSupplier(@PathVariable() String supId,Model model){
	model.addAttribute("supplier",supDAO.getSupplierId(supId));
	model.addAttribute("listSup",supDAO.getAllSupplier());
	return "supplier";
}
	
@RequestMapping("/deleteSupplier/{supId}")
public String deleteSupplier(@PathVariable("supId") String supId,Model model)
	{
		supDAO.deleteSup(supId);
		
	return "redirect:/supplier";
	}	
	
}

