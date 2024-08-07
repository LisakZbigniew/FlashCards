package com.lisakzbigniew.flashcards.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lisakzbigniew.flashcards.model.Language;
import com.lisakzbigniew.flashcards.model.Phrase;
import com.lisakzbigniew.flashcards.service.FlashCardService;

@RestController
@RequestMapping("/api/phrases")
public class PhrasesAPIController {

    @Autowired
    private FlashCardService service;

    @GetMapping("/list/{lang}")
    @ResponseBody
    public Phrase[] listPhrases(String lang) {
        Language language = Language.fromString(lang);
        List<Phrase> listInLanguage = service.listPhrasesInLanguage(language);
        return listInLanguage.toArray(new Phrase[listInLanguage.size()]);
    }

}
