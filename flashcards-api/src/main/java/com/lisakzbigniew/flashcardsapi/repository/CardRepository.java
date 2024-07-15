package com.lisakzbigniew.flashcardsapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcardsapi.model.Card;

public interface CardRepository extends CrudRepository<Card, Long> {

}
