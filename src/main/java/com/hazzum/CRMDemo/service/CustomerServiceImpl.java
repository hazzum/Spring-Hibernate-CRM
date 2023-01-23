package com.hazzum.CRMDemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazzum.CRMDemo.DAO.CustomerDAO;
import com.hazzum.CRMDemo.entity.Customer;

@Component
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> index() {
		return customerDAO.index();
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		return customerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public Customer createCustomer(Customer theCustomer) {
		return customerDAO.createCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer deleteCustomer(int id) {
		return customerDAO.deleteCustomer(id);
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer theCustomer) {
		return customerDAO.updateCustomer(theCustomer);
	}

}
