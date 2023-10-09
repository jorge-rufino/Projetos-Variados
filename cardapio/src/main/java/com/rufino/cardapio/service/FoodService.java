package com.rufino.cardapio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rufino.cardapio.dto.FoodRequestDto;
import com.rufino.cardapio.entitie.Food;
import com.rufino.cardapio.repository.FoodRepository;

@Service
public class FoodService {
	
	@Autowired
	private FoodRepository foodRepository;
	
	public List<Food> getAll(){
		return foodRepository.findAll();
	}
	
	public Food save(FoodRequestDto data) {
//		Tranforma o objeto "FoodRequestDto" em um "Food" graças ao construtor
		Food food = new Food(data);
		
		return foodRepository.save(food);
	}
}
