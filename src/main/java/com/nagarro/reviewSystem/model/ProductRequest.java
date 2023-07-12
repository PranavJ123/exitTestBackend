package com.nagarro.reviewSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Product product;
    private List<Image> image;

    @Override
    public String toString() {
        return "Product{" +
                "productInfo=" + product +
                ", images=" + image +
                '}';
    }
}
