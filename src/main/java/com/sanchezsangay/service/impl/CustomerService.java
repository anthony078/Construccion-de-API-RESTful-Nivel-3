package com.sanchezsangay.service.impl;

import org.springframework.stereotype.Service;

import com.sanchezsangay.model.Customer;
import com.sanchezsangay.repo.ICustomerRepo;
import com.sanchezsangay.repo.IGenericRepo;
import com.sanchezsangay.service.ICustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService extends GenericService<Customer, Integer> implements ICustomerService {
	  private final ICustomerRepo repo;

	    @Override
	    protected IGenericRepo <Customer, Integer> getRepo() {
	        return repo;
	    }
		
}
