package com.magalister.apigateway.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.magalister.apigateway.model.Category;

/**
 * Category client interface.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2019-08-27
 *
 */
@FeignClient("category-service")
public interface CategoryClient {

	@GetMapping("/categories")
	@CrossOrigin
	Resources<Category> readCategories();
}
