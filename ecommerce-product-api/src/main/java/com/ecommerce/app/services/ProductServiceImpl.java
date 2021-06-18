package com.ecommerce.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.app.ICategoryService;
import com.ecommerce.app.entities.Category;
import com.ecommerce.app.entities.Product;
import com.ecommerce.app.repositories.ProductRepository;
import com.ecommerce.app.requests.createProductDTO;
import com.ecommerce.app.service.interfaces.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ICategoryService categoryService;

	@Override
	public List<Product> findAll(Pageable pageable) {

		return productRepository.findAll(pageable).getContent();

	}

	@Override
	public Product getProduct(Long productId) {
		Optional<Product> Optionalproduct = productRepository.findById(productId);
		if (Optionalproduct.isPresent()) {
			return Optionalproduct.get();
		}
		return new Product();

	}

	@Override
	public List<Product> getProductsByCategoryId(Long categoryId) {
		List<Product> products = productRepository.findByCategoryId(categoryId);
		if (!products.isEmpty()) {
			return products;
		}
		return new ArrayList<Product>();

	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product saveProduct(@Valid createProductDTO productDTO) {
		Product product = new Product();
		if (productDTO == null && productDTO.getCategory() == null && productDTO.getParentCategory() == null) {
			throw new RuntimeException("product is invalid check request Data");
		}

		ResponseEntity<Category> category = categoryService.getcategory(productDTO.getCategory());
		if (category != null) {
			product.setCategory(category.getBody());
		}

		Category parentCategory = categoryService.getcategory(productDTO.getParentCategory().longValue()).getBody();
		if (category != null) {
			product.setParentCategory(parentCategory);
		}
		if (productDTO.getLongDescription() == null) {
			throw new RuntimeException("check Long Description in Request");
		}
		BeanUtils.copyProperties(productDTO, product, "category", "parentCategory");

		return productRepository.save(product);
	}

	@Override
	public List<Product> getProductsByOtherParams(String otherParams) {
		if (otherParams != null) {
			String newone = "%" + otherParams + "%";
			List<Product> products = productRepository.findByProductNameLike(newone);
			return products;
		}

		return new ArrayList<Product>();
	}
}
