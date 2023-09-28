package dev.hilmi.productservice.dtos;

public record ProductOutput(Long id,
                     String code,
                     String name,
                     Double price,
                     Long categoryId,
                     String categoryDesc) {
}
