package com.hazzum.CRMDemo.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hazzum.CRMDemo.entity.Customer;

@Repository
public interface CustomerDAO {
	
	List<Customer> index();
	
	Customer getCustomer(int id);

	Customer createCustomer(Customer theCustomer);
	
	Customer updateCustomer(Customer theCustomer);

	Customer deleteCustomer(int id);
}
