package dev.hilmi.productservice.dtos;

import java.util.List;

public record CategoryOutput(Long id,
                      String name,
                      List<ProductOutput> products) {
}
