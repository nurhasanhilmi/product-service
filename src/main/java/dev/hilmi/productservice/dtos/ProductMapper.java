package dev.hilmi.productservice.dtos;

import dev.hilmi.productservice.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryDesc")
    ProductOutput mapToOutputModel(Product product);
}
