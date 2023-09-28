package dev.hilmi.productservice.controllers;

import dev.hilmi.productservice.dtos.GeneralOutput;
import dev.hilmi.productservice.dtos.ProductInput;
import dev.hilmi.productservice.dtos.ProductMapper;
import dev.hilmi.productservice.dtos.ProductOutput;
import dev.hilmi.productservice.services.ProductCreateCommand;
import dev.hilmi.productservice.services.ProductService;
import dev.hilmi.productservice.services.ProductUpdateCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Products", description = "Operations about product")
class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/products")
    @Operation(summary = "List of products")
    List<ProductOutput> all() {
        return productService.getAll().stream()
                .map(productMapper::mapToOutputModel)
                .toList();
    }

    @PostMapping("/products")
    @Operation(summary = "Add new product")
    GeneralOutput createProduct(@RequestBody ProductInput body) {
        var command = new ProductCreateCommand(
                body.code(),
                body.name(),
                body.price(),
                body.categoryId());

        productService.create(command);
        return new GeneralOutput("Created");
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Get a product by id")
    ProductOutput one(@PathVariable Long id) {
        return productMapper.mapToOutputModel(productService.getById(id));
    }

    @PutMapping("/products/{id}")
    @Operation(summary = "Update a product")
    GeneralOutput update(@PathVariable Long id,
                         @RequestBody ProductInput body) {
        var command = new ProductUpdateCommand(
                id,
                body.code(),
                body.name(),
                body.price(),
                body.categoryId());

        productService.update(command);
        return new GeneralOutput("Updated");
    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "Delete a product")
    GeneralOutput deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return new GeneralOutput("Deleted");
    }
}
