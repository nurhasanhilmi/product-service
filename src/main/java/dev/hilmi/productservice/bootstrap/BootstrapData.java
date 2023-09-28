package dev.hilmi.productservice.bootstrap;


import dev.hilmi.productservice.services.CategoryCreateCommand;
import dev.hilmi.productservice.services.CategoryService;
import dev.hilmi.productservice.services.ProductCreateCommand;
import dev.hilmi.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        if (categoryService.getAll().isEmpty())
            loadData();
    }

    private void loadData() {
        var food = categoryService.create(new CategoryCreateCommand("Food"));
        var beverage = categoryService.create(new CategoryCreateCommand("Beverage"));
        var electronic = categoryService.create(new CategoryCreateCommand("Electronic"));

        productService.create(new ProductCreateCommand(
                "",
                "Burger",
                55000.0,
                food.getId()
        ));

        productService.create(new ProductCreateCommand(
                "",
                "Pizza",
                130000.0,
                food.getId()
        ));

        productService.create(new ProductCreateCommand(
                "",
                "Sprite",
                12000.0,
                beverage.getId()
        ));
    }
}
