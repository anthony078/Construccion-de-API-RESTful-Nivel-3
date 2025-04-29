package com.sanchezsangay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel extends RepresentationModel<ProductModel> {
    private Integer idProduct;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
