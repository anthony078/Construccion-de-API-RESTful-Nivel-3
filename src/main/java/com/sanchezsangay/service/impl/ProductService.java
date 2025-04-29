package com.sanchezsangay.service.impl;

import org.springframework.stereotype.Service;

import com.sanchezsangay.model.Product;
import com.sanchezsangay.repo.IGenericRepo;
import com.sanchezsangay.repo.IProductRepo;
import com.sanchezsangay.service.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService extends GenericService<Product, Integer> implements IProductService {
	  private final IProductRepo repo;

	    @Override
	    protected IGenericRepo<Product, Integer> getRepo() {
	        return repo;
	    }
}
