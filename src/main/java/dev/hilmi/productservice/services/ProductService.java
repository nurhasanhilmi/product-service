package dev.hilmi.productservice.services;

import dev.hilmi.productservice.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product getById(Long id);
    Product create(ProductCreateCommand command);
    Product update(ProductUpdateCommand command);
    void deleteById(Long id);
}
