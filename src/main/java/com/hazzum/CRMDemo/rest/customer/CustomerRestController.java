package com.hazzum.CRMDemo.rest.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hazzum.CRMDemo.entity.Customer;
import com.hazzum.CRMDemo.rest.exceptionHandler.InternalServerErrorException;
import com.hazzum.CRMDemo.rest.exceptionHandler.NotFoundException;
import com.hazzum.CRMDemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	// add mapping for GET /customer
	@GetMapping("/customers")
	public List<Customer> getCustomer() {
		List<Customer> theCustomers = null;
		try {
			theCustomers = customerService.index();
			return theCustomers;
		} catch (Exception e) {
			throw new InternalServerErrorException("Could not retrieve customers");
		} finally {
			if (theCustomers.isEmpty())
				throw new NotFoundException("No customers found");
		}
	}

	// add mapping for GET /customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer theCustomer = null;
		try {
			theCustomer = customerService.getCustomer(customerId);
			return theCustomer;
		} catch (Exception e) {
			throw new InternalServerErrorException("Could not retrieve customer");
		} finally {
			if (theCustomer == null)
				throw new NotFoundException("Customer not found id: " + customerId);
		}
	}

	// add mapping for POST /customers - add new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		try {
			customerService.createCustomer(theCustomer);
			return theCustomer;
		} catch (Exception e) {
			throw new InternalServerErrorException("Could not create customer");
		}
	}

	// add mapping for PUT /customers - update existing customer
	@PutMapping("/customers/{customerId}")
	public Customer updateCustomer(@RequestBody Customer theCustomer, @PathVariable int customerId) {
		Customer tempCustomer = customerService.getCustomer(customerId);
		// throw exception if null
		if (tempCustomer == null)
			throw new NotFoundException("Customer id not found - " + customerId);
		// update customer
		theCustomer.setId(customerId);
		try {
			customerService.updateCustomer(theCustomer);
			return theCustomer;
		} catch (Exception e) {
			throw new InternalServerErrorException("Could not update customer");
		}
	}

	// add mapping Delete /customers/{customerId} - delete existing customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		Customer tempCustomer = customerService.getCustomer(customerId);
		// throw exception if null
		if (tempCustomer == null)
			throw new NotFoundException("Customer id not found - " + customerId);
		try {
			customerService.deleteCustomer(customerId);
			return "Deleted Customer id - " + customerId;
		} catch (Exception e) {
			throw new InternalServerErrorException("Could not delete customer");
		}
	}

}
