package dev.hilmi.productservice.dtos;

import dev.hilmi.productservice.entities.Category;
import dev.hilmi.productservice.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryOutput mapToOutputModel(Category category);

    default ProductOutput productToProductOutput(Product product) {
        return Mappers.getMapper(ProductMapper.class).mapToOutputModel(product);
    }
}
