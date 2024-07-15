package com.lisakzbigniew.flashcardsapi.service;

import java.util.Optional;

import com.lisakzbigniew.flashcardsapi.model.Card;
import com.lisakzbigniew.flashcardsapi.model.CardCollection;
import com.lisakzbigniew.flashcardsapi.model.Language;
import com.lisakzbigniew.flashcardsapi.model.Phrase;
public interface FlashCardService {
    

    //Card operations
    public Card saveCard(Card card);

    public Iterable<Card> listCards();

    public Card updateCard(Card updatedCard);

    public void removeCard(Card card);
    
    public boolean cardExists(Card card);

    public Optional<Card> findCardById(Long id);

    public Iterable<Card> listCardsByLanguage(Language lang);

    //Phrase Operations

    public Phrase savePhrase(Phrase phrase);

    public Iterable<Phrase> listPhrases();

    public Phrase updatePhrase(Phrase phrase);

    public void removePhrase(Phrase phrase);

    public boolean phraseExists(Phrase phrase);

    public Optional<Phrase> findPhraseById(Long id);

    public Iterable<Phrase> findPhrasesByLanguage(Language lang);

    //Collection Operations

    public CardCollection saveCardCollection(CardCollection cardCollection);

    public Iterable<CardCollection> listCardCollections();

    public CardCollection updateCardCollection(CardCollection updatedCardCollection);

    public void removeCardCollection(CardCollection cardCollection);
    
    public boolean cardCollectionExists(CardCollection cardCollection);

    public Optional<CardCollection> findCardCollectionById(Long id);

    public Iterable<CardCollection> findCardCollectionsByLanguage(Language lang);

    public Iterable<CardCollection> findCardCollectionsByOwner(String owner);

    public Iterable<CardCollection> findCardCollectionsByCard(Card card);

}
