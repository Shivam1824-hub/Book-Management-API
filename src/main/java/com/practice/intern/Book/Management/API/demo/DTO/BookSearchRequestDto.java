package com.practice.intern.Book.Management.API.demo.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookSearchRequestDto {
    private String search;

    @DecimalMin(value = "0.0", inclusive = true,message = "Price should be greater than zero")
    private BigDecimal minPrice;

    @DecimalMin(value = "0.0", inclusive = true,message = "Price should be greater than zero")
    private BigDecimal maxPrice;

    @Min(value = 0, message = "Page index cannot be negative")
    private int page = 0;

    @Min(value = 5,message = "Page size must be at least 5")
    private int size = 5;

    @Pattern(
            regexp = "title|author|price",
            message = "sortBy must be one of: title, author, price"
    )
    private String sortBy = "price";

    @Pattern(
            regexp = "(?i)asc|desc",
            message = "direction must be either 'asc' or 'desc'"
    )
    private String direction = "asc";
}