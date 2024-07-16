package com.lisakzbigniew.flashcardsapi.service;

import java.util.List;
import java.util.Optional;

import com.lisakzbigniew.flashcardsapi.model.Card;
import com.lisakzbigniew.flashcardsapi.model.Language;
import com.lisakzbigniew.flashcardsapi.model.Phrase;

public interface FlashCardService {

    public List<Card> listCards();

    public List<Phrase> listPhrasesInLanguage(Language lang);

    public Card addCard(Card card);

    public boolean removeCard(Card card);

    public Optional<Card> getRandomCard();

}
