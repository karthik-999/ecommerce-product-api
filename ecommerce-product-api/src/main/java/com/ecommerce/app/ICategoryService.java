package com.ecommerce.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.app.entities.Category;

@FeignClient(value = "addcategory-api/category-app/category/", decode404 = true)
public interface ICategoryService {

	@GetMapping("get/{categoryId}")
	public ResponseEntity<Category> getcategory(@PathVariable("categoryId") Long categoryId);
	
	@PostMapping("save")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category);

}
