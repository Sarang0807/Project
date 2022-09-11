package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Food;

public interface FoodRepository extends JpaRepository<Food,Integer> {

}
