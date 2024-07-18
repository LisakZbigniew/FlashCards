package com.lisakzbigniew.flashcards.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcards.model.Language;
import com.lisakzbigniew.flashcards.model.Phrase;

public interface PhraseRepository extends CrudRepository<Phrase, Long> {
    List<Phrase> findByLanguage(Language language);
    Optional<Phrase> findByContentAndLanguage(String content, Language language);
}
