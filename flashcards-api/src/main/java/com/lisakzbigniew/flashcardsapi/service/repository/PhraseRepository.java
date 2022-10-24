package com.lisakzbigniew.flashcardsapi.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcardsapi.model.Phrase;

public interface PhraseRepository extends CrudRepository<Phrase,Long>{
    
}