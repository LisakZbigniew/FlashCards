package com.lisakzbigniew.flashcardsapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcardsapi.model.Language;
import com.lisakzbigniew.flashcardsapi.model.Phrase;

public interface PhraseRepository extends CrudRepository<Phrase, Long> {
    List<Phrase> findByLang(Language lang);
}
