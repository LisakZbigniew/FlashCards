package com.lisakzbigniew.flashcardsapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lisakzbigniew.flashcardsapi.model.Card;
import com.lisakzbigniew.flashcardsapi.service.flashcardsRepository.FlashcardRepository;
import com.lisakzbigniew.flashcardsapi.service.phraseRepositiory.PhraseRepository;

@RestController
@RequestMapping(path = "api/flashcard")
public class FlashcardsController {

    @Autowired
    private FlashcardRepository repoCard;
    @Autowired
    private PhraseRepository repoPhrase;

    @GetMapping("/all")
    public @ResponseBody Iterable<Card> listAll(){
        return repoCard.findAll();
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Card addCard(@RequestBody Card newCard){
        newCard.setA(repoPhrase.save(newCard.getA()));
        newCard.setB(repoPhrase.save(newCard.getB()));
        return repoCard.save(newCard);
    }

}
