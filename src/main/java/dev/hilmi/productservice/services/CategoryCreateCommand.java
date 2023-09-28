package dev.hilmi.productservice.services;

import dev.hilmi.productservice.common.Validation;
import jakarta.validation.constraints.NotEmpty;

public record CategoryCreateCommand(@NotEmpty String name) {

    public CategoryCreateCommand(String name) {
        this.name = name;
        Validation.validate(this);
    }
}
