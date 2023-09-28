package dev.hilmi.productservice.services;

import dev.hilmi.productservice.entities.Category;
import dev.hilmi.productservice.exceptionhandlers.UniqueEntityDuplicationException;
import dev.hilmi.productservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(CategoryCreateCommand command) {
        var name = command.name();

        if (categoryRepository.findByName(name).isPresent())
            throw new UniqueEntityDuplicationException(String.format("Category '%s' is already exist", name));

        var newCategory = new Category(command.name());
        return categoryRepository.save(newCategory);
    }
}
