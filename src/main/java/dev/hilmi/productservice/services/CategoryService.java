package dev.hilmi.productservice.services;

import dev.hilmi.productservice.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();
    Category create(CategoryCreateCommand command);
}
