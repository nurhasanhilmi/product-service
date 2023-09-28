package dev.hilmi.productservice.services;

import dev.hilmi.productservice.common.Validation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ProductCreateCommand(@Pattern(regexp = "(^$|^[0-9]{8,}$)", message = "must be blank or contain a minimum of 8 numeric characters") String code,
                                   @NotEmpty String name,
                                   @NotNull @Positive Double price,
                                   @NotNull Long categoryId) {

    public ProductCreateCommand(String code, String name, Double price, Long categoryId) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        Validation.validate(this);
    }
}
