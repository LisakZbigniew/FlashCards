package com.lisakzbigniew.flashcardsapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcardsapi.model.CardCollection;

public interface CollectionRepository extends CrudRepository<CardCollection,Long>{
    
}
