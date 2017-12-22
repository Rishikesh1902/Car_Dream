  

package com.niit.controller;
import javax.servlet.http.HttpServletRequest; 

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.niit.backendProject.dao.CategoryDAO;
import com.niit.backendProject.dao.ProdDAO;
import com.niit.backendProject.dao.SupDAO;
import com.niit.backendProject.model.Product;

import jdk.nashorn.internal.ir.RuntimeNode.Request;


@Controller
public class ProductController {
	@Autowired
	ProdDAO prodDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	SupDAO supDAO;
	@Autowired
	HttpSession httpSession;
	@RequestMapping("/ProductDetail")
	public String product(Model model)
	{ 
		model.addAttribute("product",new Product());
		httpSession.setAttribute("listSup",supDAO.getAllSupplier());
		httpSession.setAttribute("listCategory",categoryDAO.getAllCategory());
		model.addAttribute("listProduct",prodDAO.getAllProduct());
		
		return "ProductDetail";
	}
	@RequestMapping("/addProduct")
	public ModelAndView addProduct(@Valid @ModelAttribute("product")Product p,BindingResult result,Model model){
		ModelAndView m=new ModelAndView("forward:/ProductDetail");
		if(result.hasErrors()){
			m.addObject("msg", "please fil the details correctly");
	
			return m;
	/*	System.out.println(p.getProductId());
		System.out.println(p.getName());
		System.out.println(p.getDescp());*/
		
		}
		else{
		if(p.getProductId()==0)
		{
			
		
		if(prodDAO.addProduct(p))
		{
			System.out.println("p.getid is zero");
			
			MultipartFile file=p.getImage();
			
			ServletContext con=httpSession.getServletContext();
			String filelocation=con.getRealPath("/resources/image/");
			System.out.println(filelocation);
			String filename=filelocation+"\\"+p.getProductId()+".jpg";
			System.out.println(filename);
			
			try{
				byte b[]=file.getBytes();
				FileOutputStream fos=new FileOutputStream(filename);
				fos.write(b);
				fos.close();
		    	}
			catch(IOException ex){}
			catch(Exception e){}
				}	
		}
		
		else{
			System.out.println("product is updqating");
			if(prodDAO.updateProd(p))
			{
			model.addAttribute("msg"," Catgory updated Sucessfully");
			}
			else
			{
				model.addAttribute("msg","not updated");
			}			
			
		}
		}
		return m;

	}
	
	@RequestMapping("/updateProduct/{name}")
	//public String editCategory(@PathVariable("name") String name,Model model){
	//public String editCategory(@PathVariable() String name,Model model){ it gives error
public String editProduct(@PathVariable() String name,Model model){
		model.addAttribute("product",prodDAO.getProductName(name));
		model.addAttribute("listProduct",prodDAO.getAllProduct());
		return "ProductDetail";
	}
	
	
	@RequestMapping("/deleteProduct/{name}")
	public String deleteProduct(@PathVariable("name") String name,Model model)
	{
			prodDAO.deleteProduct(name);
			
		return "redirect:/ProductDetail";
		}
	
	@RequestMapping("/productbyCategory/{catid}")
	public String getProdByCat(@PathVariable("catid") String id,Model model ){
		System.out.print("reteriving data by category");
		model.addAttribute("listCatPro",prodDAO.getProductByCategory(id));
		return "CatProduct";
	}
	
/*
@RequestMapping("/productbyCategory/{catid}")
	public String getProdByCat(@PathVariable("catid") String id,Model model ){
		System.out.print("reteriving data by category");
	List<Product>listCatProduct=(List<Product>)prodDAO.getAllProduct();
	for(Product p:listCatProduct){
		System.out.println(p.getName());
		
	}
	Gson gson=new Gson();
	String json=gson.toJson(listCatProduct);
	System.out.println(json);
	//model.addAttribute("listCatPro",json);
		return "CatProduct";
	}
	*/
	
	
	
	@RequestMapping("/MoreDetails/{id}")
	public ModelAndView moreDetail(@PathVariable("") int  id){
	ModelAndView mv=new ModelAndView("/MoreDetails");
	mv.addObject("product",prodDAO.getProductId(id));
	mv.addObject("categoryList", categoryDAO.getAllCategory());
	mv.addObject("listSup", supDAO.getAllSupplier());
			return mv;
		
	}
}
