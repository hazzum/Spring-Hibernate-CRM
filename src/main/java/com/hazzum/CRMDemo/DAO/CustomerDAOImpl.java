package com.hazzum.CRMDemo.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hazzum.CRMDemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> index() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Customer", Customer.class).getResultList();
	}
	
	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Customer.class, id);
	}
	
	@Override
	public Customer createCustomer(Customer theCustomer) {
		Session session = sessionFactory.getCurrentSession();
		Customer tempCustomer = new Customer(theCustomer.getFirstName(), theCustomer.getLastName(), theCustomer.getEmail());
		session.save(tempCustomer);
		return tempCustomer;
	}
	
	@Override
	public Customer updateCustomer(Customer theCustomer) {
		Session session = sessionFactory.getCurrentSession();
		Customer myCustomer = session.get(Customer.class, theCustomer.getId());
		myCustomer.setFirstName(theCustomer.getFirstName());
		myCustomer.setLastName(theCustomer.getLastName());
		myCustomer.setEmail(theCustomer.getEmail());
		return myCustomer;
	}
	
	@Override
	public Customer deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer myCustomer = session.get(Customer.class, id);
		session.delete(myCustomer);
		return myCustomer;
	}

}
