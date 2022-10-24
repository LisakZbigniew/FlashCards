package com.lisakzbigniew.flashcardsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisakzbigniew.flashcardsapi.model.Card;
import com.lisakzbigniew.flashcardsapi.model.FamiliarityLevel;
import com.lisakzbigniew.flashcardsapi.service.repository.FlashcardRepository;
import com.lisakzbigniew.flashcardsapi.service.repository.PhraseRepository;

@Service
public class FlashCardSevice {
    @Autowired
    private FlashcardRepository flashCardRepo;
    @Autowired
    private PhraseRepository phraseRepo;
    
    
    public Card create(Card newCard){

        if(newCard.getA().getId() != null){
            newCard.setA(phraseRepo.save(newCard.getA()));
        }
        if(newCard.getB().getId() != null){
            newCard.setB(phraseRepo.save(newCard.getB()));
        }

        newCard.setStreak(0);
        newCard.setLevel(FamiliarityLevel.NEW);
        
        return flashCardRepo.save(newCard);

    }

    public Iterable<Card> findAll(){
        return flashCardRepo.findAll();
    }

}
