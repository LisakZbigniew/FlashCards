package com.lisakzbigniew.flashcards.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.collections4.IterableUtils;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisakzbigniew.flashcards.model.Card;
import com.lisakzbigniew.flashcards.model.Language;
import com.lisakzbigniew.flashcards.model.Phrase;
import com.lisakzbigniew.flashcards.repository.CardRepository;
import com.lisakzbigniew.flashcards.repository.PhraseRepository;
import com.lisakzbigniew.flashcards.service.FlashCardService;

import jakarta.annotation.Nonnull;

@Service
public class DatabaseFlashCardServiceImpl implements FlashCardService {

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private PhraseRepository phraseRepo;

    private Logger logger = LoggerFactory.getLogger(DatabaseFlashCardServiceImpl.class.getName());

    private Random randomGenerator = new Random(System.currentTimeMillis());

    public DatabaseFlashCardServiceImpl(CardRepository cardRepo, PhraseRepository phraseRepo) {
        this.cardRepo = cardRepo;
        this.phraseRepo = phraseRepo;
    }

    @Override
    public Card addCard(@Nonnull Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Attempted to save null card");
        }
        if (card.getFirstPhrase() == null || card.getSecondPhrase() == null) {
            throw new IllegalArgumentException("Attempted to save card with null phrase");
        }

        card.setFirstPhrase(savePhrase(card.getFirstPhrase()));
        card.setSecondPhrase(savePhrase(card.getSecondPhrase()));
        card.fixPhraseOrder();

        Card newCard = cardRepo.findByFirstPhraseAndSecondPhrase(
                card.getFirstPhrase(),
                card.getSecondPhrase())
                .orElseGet(() -> cardRepo.save(card));

        if (logger.isDebugEnabled()) {
            logger.debug("Saved card %s", newCard.toString());
        }

        return newCard;
    }

    private Phrase savePhrase(Phrase toSave) {
        return phraseRepo.findByContentAndLanguage(toSave.getContent(), toSave.getLanguage())
                .orElseGet(() -> phraseRepo.save(toSave));
    }

    @Override
    public List<Card> listCards() {
        return IterableUtils.toList(cardRepo.findAll());
    }

    @Override
    public boolean removeCard(@Nonnull Card card) {
        try {
            cardRepo.delete(card);
        } catch (OptimisticEntityLockException e) {
            logger.debug("Couldn't remove card entity: %s", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Phrase> listPhrasesInLanguage(Language lang) {
        return IterableUtils.toList(phraseRepo.findByLanguage(lang));
    }

    @Override
    public Optional<Card> getRandomCard() {
        return Optional.ofNullable(
                randomFromList(IterableUtils.toList(cardRepo.findAll())));
    }

    private <E> E randomFromList(List<E> list) {
        if (list.size() == 0)
            return null;
        return list.get(randomGenerator.nextInt(list.size()));
    }

}
