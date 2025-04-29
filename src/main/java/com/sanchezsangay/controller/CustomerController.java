package com.sanchezsangay.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sanchezsangay.model.Customer;
import com.sanchezsangay.service.ICustomerService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
	  private final ICustomerService service;

	    @GetMapping
	    public ResponseEntity<List<Customer>> findAll() throws Exception{
	        List<Customer> list = service.findAll();
	        return ResponseEntity.ok(list);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Customer> findById(@PathVariable("id") Integer id) throws Exception{
	    	Customer obj =  service.findById(id);
	        return ResponseEntity.ok(obj);
	    }

	    @PostMapping
	    public ResponseEntity<Customer> save(@RequestBody Customer customer) throws Exception{
	    	Customer obj =  service.save(customer);
	        URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(obj.getIdCustomer()).toUri();
	        return ResponseEntity.created(location).build();
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Customer> update(@PathVariable("id") Integer id, @RequestBody Customer customer) throws Exception{
	    	Customer obj =  service.update(customer, id);
	        return ResponseEntity.ok(obj);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
	            throws Exception{
	        service.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	
}
