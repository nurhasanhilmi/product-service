package dev.hilmi.productservice.services;

import dev.hilmi.productservice.entities.Product;
import dev.hilmi.productservice.repositories.CategoryRepository;
import dev.hilmi.productservice.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " does not exist"));
    }

    @Override
    public Product create(ProductCreateCommand command) {
        var code = codeCheckOrGenerate(command.code());

        var category = categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + command.categoryId() + " does not exist"));

        var product = new Product(
                code,
                command.name(),
                command.price(),
                category
        );

        return productRepository.save(product);
    }

    @Override
    public Product update(ProductUpdateCommand command) {
        var product = productRepository.findById(command.id())
                .orElseThrow(() -> new EntityNotFoundException("Product with id: " + command.id() + " does not exist"));

        var code = command.code();
        if (code == null)
            code = product.getCode();
        else if (!code.equals(product.getCode()))
            code = codeCheckOrGenerate(command.code());

        var category = categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + command.categoryId() + " does not exist"));

        product.setCode(code);
        product.setName(command.name());
        product.setPrice(command.price());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private String codeCheckOrGenerate(String code) {
        if (code == null || code.isBlank())
            return RandomStringUtils.randomNumeric(8);

        while (productRepository.findByCode(code).isPresent())
            code = incrementLastDigit(code);

        return code;
    }

    private String incrementLastDigit(String code) {
        var length = code.length();
        var lastDigit = Integer.parseInt(code.substring(length - 1));
        return code.substring(0, length - 1) +
                ++lastDigit;
    }
}
