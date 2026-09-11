package com.practice.intern.Book.Management.API.demo.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookSearchRequestDto {
    private String search;

    @Min(value = 0, message = "Price should be greater than zero")
    private BigDecimal minPrice;

    @Min(value = 0, message = "Price should be greater than zero")
    private BigDecimal maxPrice;

    @Min(value = 0, message = "Page index cannot be negative")
    private int page = 0;

    @Min(value = 1,message = "Page size must be at least 1")
    private int size = 10;

    private String sortBy = "price";
    private String direction = "asc";

}