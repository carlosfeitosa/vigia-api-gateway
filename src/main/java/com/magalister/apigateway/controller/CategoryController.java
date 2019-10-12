package com.magalister.apigateway.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magalister.apigateway.model.Category;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Category rest controller.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2019-08-27
 *
 */
@RestController
public class CategoryController {

	private final CategoryClient client;

	/**
	 * Constructor.
	 * 
	 * @param client category client
	 */
	public CategoryController(CategoryClient client) {
		this.client = client;
	}

	/**
	 * Fallback method.
	 * 
	 * @return empty ArrayList
	 */
	@SuppressWarnings("unused")
	private Collection<Category> fallback() {
		return new ArrayList<>();
	}

	/**
	 * Recover categories from category client.
	 * 
	 * @return category collection
	 */
	@GetMapping("/categorias")
	@CrossOrigin
	@HystrixCommand(fallbackMethod = "fallback")
	public Collection<Category> categorias() {

		return client.readCategories().getContent().stream().filter(this::isOk).collect(Collectors.toList());
	}

	/**
	 * Identify if a category is OK (filter).
	 * 
	 * @param category
	 * @return
	 */
	private boolean isOk(Category category) {
		return (!category.getName().isEmpty());
	}
}
