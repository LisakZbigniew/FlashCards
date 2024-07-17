package com.lisakzbigniew.flashcardsapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcardsapi.model.Card;
import com.lisakzbigniew.flashcardsapi.model.Phrase;

public interface CardRepository extends CrudRepository<Card, Long> {
    Optional<Card> findByFirstPhraseAndSecondPhrase(Phrase firstPhrase,Phrase secondPhrase);
}
