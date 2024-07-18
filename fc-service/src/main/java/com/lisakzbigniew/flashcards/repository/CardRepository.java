package com.lisakzbigniew.flashcards.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcards.model.Card;
import com.lisakzbigniew.flashcards.model.Phrase;

public interface CardRepository extends CrudRepository<Card, Long> {
    Optional<Card> findByFirstPhraseAndSecondPhrase(Phrase firstPhrase,Phrase secondPhrase);
}
