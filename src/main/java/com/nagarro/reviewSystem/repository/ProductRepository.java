package com.nagarro.reviewSystem.repository;

import com.nagarro.reviewSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findById(long productId);
    List<Product> findByName(String name);

    List<Product> findByCode(String code);

    List<Product> findByBrand(String brand);

    List<Product> findByNameAndCode(String name, String code);

    List<Product> findByNameAndBrand(String name, String brand);

    List<Product> findByCodeAndBrand(String code, String brand);

    List<Product> findByNameAndCodeAndBrand(String name, String code, String brand);

}
