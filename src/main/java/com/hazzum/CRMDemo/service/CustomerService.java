package com.hazzum.CRMDemo.service;

import java.util.List;

import com.hazzum.CRMDemo.entity.Customer;

public interface CustomerService {

	List<Customer> index();

	Customer getCustomer(int id);

	Customer createCustomer(Customer theCustomer);

	Customer deleteCustomer(int id);
	
	Customer updateCustomer(Customer theCustomer);
}
