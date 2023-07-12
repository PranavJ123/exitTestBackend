package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.CONSTANTS;
import com.nagarro.reviewSystem.model.Image;
import com.nagarro.reviewSystem.model.Message;
import com.nagarro.reviewSystem.model.Product;
import com.nagarro.reviewSystem.model.ProductRequest;
import com.nagarro.reviewSystem.repository.ImageRepository;
import com.nagarro.reviewSystem.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CONSTANTS constants;


    @Override
    public List<ProductRequest> getProducts() {

        List<ProductRequest> result = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            List<Image> images = imageRepository.findByProductId(product.getId());
            result.add(new ProductRequest(product, images));
        });

        return result;
    }

    @Override
    public ProductRequest getProductById(long productId) {
        ProductRequest result = new ProductRequest();
        Product info = productRepository.findById(productId).get(0);
        result.setProduct(info);
        List<Image> images = imageRepository.findByProductId(info.getId());
        result.setImage(images);
        return result;
    }

    @Override

    public List<ProductRequest> getProductsByCriteria(String name, String code, String brand) {
        List<Product> products;
        System.out.println(name + " " + code + " " + brand);
        if (name != "" && code != "" && brand != "") {
            products = productRepository.findByNameAndCodeAndBrand(name, code, brand);
        } else if (name != "" && code != "") {
            products = productRepository.findByNameAndCode(name, code);
        } else if (name != "" && brand != "") {
            products = productRepository.findByNameAndBrand(name, brand);
        } else if (code != "" && brand != "") {
            products = productRepository.findByCodeAndBrand(code, brand);
        } else if (name != "") {
            System.out.println(name);
            products = productRepository.findByName(name);
        } else if (code != "") {
            products = productRepository.findByCode(code);
            System.out.println(products);
        } else if (brand != "") {
            products = productRepository.findByBrand(brand);
        } else {
            products = productRepository.findAll();
        }

        List<ProductRequest> result = new ArrayList<>();
        products.forEach(product -> {
            List<Image> images = imageRepository.findByProductId(product.getId());
            result.add(new ProductRequest(product, images));
        });

        return result;
    }

    @Override
    public Message addProduct(ProductRequest product) {
        Product productInfo = productRepository.save(product.getProduct());
        product.getImage().forEach(image -> {
            image.setProductId(productInfo.getId());
            imageRepository.save(image);
        });
        return new Message("Product Added Successfully", constants.SUCCESS);
    }

    @Override
    public long totalProducts() {
        return productRepository.count();
    }
}
