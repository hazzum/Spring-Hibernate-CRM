package com.hazzum.CRMDemo.DAO;

import java.util.List;
import com.hazzum.CRMDemo.entity.Customer;

public interface CustomerDAO {
	
	List<Customer> index();
	
	Customer getCustomer(int id);

	Customer createCustomer(Customer theCustomer);
	
	Customer updateCustomer(Customer theCustomer);

	Customer deleteCustomer(int id);
}
