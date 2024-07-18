package com.lisakzbigniew.flashcards.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcards.model.CardCollection;

public interface CollectionRepository extends CrudRepository<CardCollection, Long> {
    List<CardCollection> findByOwner(String owner);
}
