package com.sanchezsangay.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import com.sanchezsangay.model.Product;
import com.sanchezsangay.model.ProductModel;
import com.sanchezsangay.service.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    
    private final IProductService service;

   
    private ProductModel convertToModel(Product product) {
        ProductModel productModel = new ProductModel(
            product.getIdProduct(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock()
        );
        
        // Agregar enlaces HATEOAS
        Link selfLink = WebMvcLinkBuilder.linkTo(ProductController.class).slash(product.getIdProduct()).withSelfRel();
        Link allProductsLink = WebMvcLinkBuilder.linkTo(ProductController.class).withRel("all-products");
        productModel.add(selfLink);
        productModel.add(allProductsLink);
        
        return productModel;
    }

   
    private List<ProductModel> convertToModelList(List<Product> products) {
        return products.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> findAll() throws Exception {
        List<Product> list = service.findAll();
        List<ProductModel> productModels = convertToModelList(list);
        return ResponseEntity.ok(productModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable("id") Integer id) throws Exception {
        Product obj = service.findById(id);
        ProductModel productModel = convertToModel(obj);
        return ResponseEntity.ok(productModel);
    }

    @PostMapping
    public ResponseEntity<ProductModel> save(@RequestBody Product product) throws Exception {
        Product obj = service.save(product);
        ProductModel productModel = convertToModel(obj);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdProduct())
                .toUri();
        
        return ResponseEntity.created(location).body(productModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> update(@PathVariable("id") Integer id, @RequestBody Product product) throws Exception {
        Product obj = service.update(product, id);
        ProductModel productModel = convertToModel(obj);
        return ResponseEntity.ok(productModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
