package dev.hilmi.productservice.dtos;

public record ProductInput(String code,
                           String name,
                           Double price,
                           Long categoryId) {
}
