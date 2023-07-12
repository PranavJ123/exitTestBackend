package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.model.Message;
import com.nagarro.reviewSystem.model.Product;
import com.nagarro.reviewSystem.model.ProductRequest;

import java.util.List;

public interface ProductService {

    public List<ProductRequest> getProducts();

    public ProductRequest getProductById(long productId);

List<ProductRequest> getProductsByCriteria(String name, String code, String brand);
    public Message addProduct(ProductRequest productRequest);

    public long totalProducts();

}
