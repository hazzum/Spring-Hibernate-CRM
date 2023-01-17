package com.hazzum.CRMDemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazzum.CRMDemo.DAO.CustomerDAO;
import com.hazzum.CRMDemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> index() {
		// TODO Auto-generated method stub
		return customerDAO.index();
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		return customerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public Customer createCustomer(Customer theCustomer) {
		// TODO Auto-generated method stub
		return customerDAO.createCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer theCustomer) {
		// TODO Auto-generated method stub
		return customerDAO.updateCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return customerDAO.deleteCustomer(id);
	}

}
