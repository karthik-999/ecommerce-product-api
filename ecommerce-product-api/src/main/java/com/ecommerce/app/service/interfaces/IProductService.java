package com.ecommerce.app.service.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.ecommerce.app.entities.Product;
import com.ecommerce.app.requests.createProductDTO;

public interface IProductService {

	List<Product> findAll(Pageable pageable);

	Product getProduct(Long productId);

	List<Product> getProductsByCategoryId(Long categoryId);

	Product saveProduct(Product product);

	Product saveProduct(@Valid createProductDTO productDTO);

	List<Product> getProductsByOtherParams(String otherParams);

}
