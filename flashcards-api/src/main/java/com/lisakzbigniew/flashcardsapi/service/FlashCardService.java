package com.lisakzbigniew.flashcardsapi.service;

import org.springframework.core.metrics.StartupStep.Tags;

import com.lisakzbigniew.flashcardsapi.model.Card;
import com.lisakzbigniew.flashcardsapi.model.CardCollection;
import com.lisakzbigniew.flashcardsapi.model.Language;
import com.lisakzbigniew.flashcardsapi.model.Phrase;
import com.lisakzbigniew.flashcardsapi.model.Tag;

public interface FlashCardService {
    

    //Card operations
    public Card saveCard(Card card);

    public Iterable<Card> listCards();

    public Card updateCard(Card updatedCard);

    public void removeCard(Card card);
    
    public boolean cardExists(Card card);

    public Card findCardById(Long id);

    public Iterable<Card> findCardByLanguage(Language lang);

    public Iterable<Card> findCardByTag(Tag tag);

    public Card addToStreak(Card card);

    public Card resetStreak(Card card);

    //Phrase Operations

    public Phrase savePhrase(Phrase phrase);

    public Iterable<Phrase> listPhrases();

    public Phrase updatePhrase(Phrase phrase);

    public boolean phraseExists(Phrase phrase);

    public Phrase findPhraseById(Long id);

    public Iterable<Phrase> findPhraseByLanguage(Language lang);

    //Tag Operations

    public Tag saveTag(Tag tag);

    public Iterable<Tag> listTags();

    public Tag updateTag(Tag updatedTag);

    public void removeTag(Tag tag);
    
    public boolean tagExists(Tag tag);

    public Tag findTagById(Long id);

    //Collection Operations

    public CardCollection saveCardCollection(CardCollection cardCollection);

    public Iterable<CardCollection> listCardCollections();

    public CardCollection updateCardCollection(CardCollection updatedCardCollection);

    public void removeCardCollection(CardCollection cardCollection);
    
    public boolean cardCollectionExists(CardCollection cardCollection);

    public CardCollection findCardCollectionById(Long id);

    public Iterable<CardCollection> findCardCollectionByLanguage(Language lang);

    public Iterable<CardCollection> findCardCollectionByowner(String owner);

    public Iterable<CardCollection> findCardCollectionByCard(Card card);

    //Misc Operations
    
    

}