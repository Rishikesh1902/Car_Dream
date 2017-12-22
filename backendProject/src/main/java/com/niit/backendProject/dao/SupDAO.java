package com.niit.backendProject.dao;

import java.util.List;


import com.niit.backendProject.model.Supplier;

public interface SupDAO {
	public boolean addSuppiler(Supplier s);
	public boolean updateSup(Supplier s);
	List<Supplier>getAllSupplier(); 
		
	public Supplier getSupplierId(String id);
	public boolean deleteSup(String id);

	

}
