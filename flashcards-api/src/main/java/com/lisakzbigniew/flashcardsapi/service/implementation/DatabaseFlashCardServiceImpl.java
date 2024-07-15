package com.lisakzbigniew.flashcardsapi.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisakzbigniew.flashcardsapi.model.Card;
import com.lisakzbigniew.flashcardsapi.model.CardCollection;
import com.lisakzbigniew.flashcardsapi.model.Language;
import com.lisakzbigniew.flashcardsapi.model.Phrase;
import com.lisakzbigniew.flashcardsapi.repository.CardRepository;
import com.lisakzbigniew.flashcardsapi.repository.CollectionRepository;
import com.lisakzbigniew.flashcardsapi.repository.PhraseRepository;
import com.lisakzbigniew.flashcardsapi.service.FlashCardService;

import jakarta.annotation.Nonnull;

@Service
public class DatabaseFlashCardServiceImpl implements FlashCardService {

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private PhraseRepository phraseRepo;

    @Autowired
    private CollectionRepository cardCollectionRepo;

    private Logger logger = LoggerFactory.getLogger(DatabaseFlashCardServiceImpl.class.getName());

    @Override
    public Card saveCard(@Nonnull Card card) {
        card.setFirstPhrase(savePhrase(card.getFirstPhrase()));
        card.setSecondPhrase(savePhrase(card.getSecondPhrase()));

        return cardRepo.save(card);
    }

    @Override
    public Iterable<Card> listCards() {
        return cardRepo.findAll();
    }

    @Override
    public Card updateCard(@Nonnull Card updatedCard) {
        return saveCard(updatedCard);
    }

    @Override
    public void removeCard(@Nonnull Card card) {
        cardRepo.delete(card);
    }

    @Override
    public boolean cardExists(@Nonnull Card card) {
        return card.getId() != null && cardRepo.existsById(card.getId());
    }

    @Override
    public Optional<Card> findCardById(Long id) {
        if (id == null)
            return Optional.empty();
        return cardRepo.findById(id);
    }

    @Override
    public Iterable<Card> listCardsByLanguage(Language lang) {
        List<Card> filtered = new ArrayList<>();
        listCards().forEach(card -> {
            if (card.inLang(lang))
                filtered.add(card);
        });
        return filtered;
    }

    @Override
    public Phrase savePhrase(@Nonnull Phrase phrase) {
        return phraseRepo.save(phrase);
    }

    @Override
    public Iterable<Phrase> listPhrases() {
        return phraseRepo.findAll();
    }

    @Override
    public Phrase updatePhrase(@Nonnull Phrase phrase) {
        return savePhrase(phrase);
    }

    @Override
    public void removePhrase(@Nonnull Phrase phrase) {
        phraseRepo.delete(phrase);
    }

    @Override
    public boolean phraseExists(Phrase phrase) {
        return phrase.getId() != null && phraseRepo.existsById(phrase.getId());
    }

    @Override
    public Optional<Phrase> findPhraseById(Long id) {
        if (id == null)
            return Optional.empty();
        return phraseRepo.findById(id);
    }

    @Override
    public Iterable<Phrase> findPhrasesByLanguage(Language lang) {
        List<Phrase> filtered = new ArrayList<>();
        listPhrases().forEach(p -> {
            if (p.inLang(lang)) {
                filtered.add(p);
            }
        });
        return filtered;
    }

    @Override
    public CardCollection saveCardCollection(@Nonnull CardCollection cardCollection) {
        List<Card> updatedList = new ArrayList<>();

        for (Card card : cardCollection.getCards()) {
            updatedList.add(saveCard(card));
        }

        cardCollection.setCards(updatedList);

        return cardCollectionRepo.save(cardCollection);
    }

    @Override
    public Iterable<CardCollection> listCardCollections() {
        return cardCollectionRepo.findAll();
    }

    @Override
    public CardCollection updateCardCollection(@Nonnull CardCollection updatedCardCollection) {
        return saveCardCollection(updatedCardCollection);
    }

    @Override
    public void removeCardCollection(@Nonnull CardCollection cardCollection) {
        cardCollectionRepo.delete(cardCollection);
    }

    @Override
    public boolean cardCollectionExists(@Nonnull CardCollection cardCollection) {
        return cardCollection.getId() != null &&
                cardCollectionRepo.existsById(cardCollection.getId());
    }

    @Override
    public Optional<CardCollection> findCardCollectionById(Long id) {
        if (id == null)
            return Optional.empty();
        return cardCollectionRepo.findById(id);
    }

    @Override
    public Iterable<CardCollection> findCardCollectionsByLanguage(Language lang) {
        List<CardCollection> filtered = new ArrayList<>();

        listCardCollections().forEach(collection -> {
            if (collection.getCards().stream().anyMatch(c -> c.inLang(lang))) {
                filtered.add(collection);
            }
        });

        return filtered;
    }

    @Override
    public Iterable<CardCollection> findCardCollectionsByOwner(String owner) {
        return cardCollectionRepo.findByOwner(owner);
    }

    @Override
    public Iterable<CardCollection> findCardCollectionsByCard(Card card) {
        List<CardCollection> filtered = new ArrayList<>();

        listCardCollections().forEach(collection -> {
            if (collection.getCards().stream().anyMatch(c -> c.equals(card))) {
                filtered.add(collection);
            }
        });

        return filtered;
    }

}
