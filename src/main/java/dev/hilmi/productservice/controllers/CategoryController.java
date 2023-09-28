package dev.hilmi.productservice.controllers;

import dev.hilmi.productservice.dtos.CategoryInput;
import dev.hilmi.productservice.dtos.CategoryMapper;
import dev.hilmi.productservice.dtos.CategoryOutput;
import dev.hilmi.productservice.dtos.GeneralOutput;
import dev.hilmi.productservice.services.CategoryCreateCommand;
import dev.hilmi.productservice.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Operations about category")
class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/categories")
    @Operation(summary = "List of categories")
    List<CategoryOutput> all() {
        return categoryService.getAll().stream()
                .map(categoryMapper::mapToOutputModel)
                .toList();
    }

    @PostMapping("/categories")
    @Operation(summary = "Add new category")
    GeneralOutput newCategory(@RequestBody CategoryInput body) {
        var command = new CategoryCreateCommand(body.name());
        categoryService.create(command);
        return new GeneralOutput("Created");
    }
}
