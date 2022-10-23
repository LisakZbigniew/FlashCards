package com.lisakzbigniew.flashcardsapi.service.flashcardsRepository;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcardsapi.model.Card;

public interface FlashcardRepository extends CrudRepository<Card,Long> {

}
