package com.lisakzbigniew.flashcards.service;

import java.util.List;
import java.util.Optional;

import com.lisakzbigniew.flashcards.model.Card;
import com.lisakzbigniew.flashcards.model.Language;
import com.lisakzbigniew.flashcards.model.Phrase;

public interface FlashCardService {

    public List<Card> listCards();

    public List<Phrase> listPhrasesInLanguage(Language lang);

    public Card addCard(Card card);

    public boolean removeCard(Card card);

    public Optional<Card> getRandomCard();

}
