package com.lisakzbigniew.flashcards.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lisakzbigniew.flashcards.model.Card;
import com.lisakzbigniew.flashcards.service.FlashCardService;

@RestController
@RequestMapping(path = "api/flashcards")
public class FlashcardsAPIController {

    @Autowired
    private FlashCardService flashCardService;

    @GetMapping("/")
    public @ResponseBody Iterable<Card> listAll() {
        return flashCardService.listCards();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Card addCard(@RequestBody Card newCard) {
        return flashCardService.addCard(newCard);
    }

}
