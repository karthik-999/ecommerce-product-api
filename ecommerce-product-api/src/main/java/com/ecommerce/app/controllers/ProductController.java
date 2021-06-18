package com.ecommerce.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.entities.Product;
import com.ecommerce.app.requests.createProductDTO;
import com.ecommerce.app.service.interfaces.IProductService;

@RestController
@RequestMapping("/product/")
public class ProductController {

	@Autowired
	IProductService productService;

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<Product>> getProductsByPage(@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		List<Product> products = productService.findAll(pageable);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/get/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
		return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
	}

	@GetMapping("/get/category/{categoryId}")
	public ResponseEntity<List<Product>> getProductByCategoryId(@PathVariable("categoryId") Long categoryId) {
		return new ResponseEntity<>(productService.getProductsByCategoryId(categoryId), HttpStatus.OK);
	}
	
	@GetMapping("/get/otherParams/{otherParams}")
	public ResponseEntity<List<Product>> getProductByOther(@PathVariable("otherParams") String otherParams) {
		return new ResponseEntity<>(productService.getProductsByOtherParams(otherParams), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Product> saveUser(@Valid @RequestBody createProductDTO productDTO) {
		return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.CREATED);
	}
}
