package com.rufino.cardapio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rufino.cardapio.entitie.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{

}
