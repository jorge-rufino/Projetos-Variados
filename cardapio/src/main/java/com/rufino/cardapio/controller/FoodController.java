package com.rufino.cardapio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rufino.cardapio.dto.FoodResponseDto;
import com.rufino.cardapio.service.FoodService;

@RestController
@RequestMapping("food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@GetMapping
	public List<FoodResponseDto> getAll() {
		
		List<FoodResponseDto> foodList = foodService.getAll().stream()
				.map(FoodResponseDto::new)
				.toList();
		
		return foodList;
	}
}
