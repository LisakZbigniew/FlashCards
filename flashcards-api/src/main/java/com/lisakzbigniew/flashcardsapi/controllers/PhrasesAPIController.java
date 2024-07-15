package com.lisakzbigniew.flashcardsapi.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lisakzbigniew.flashcardsapi.model.Phrase;
import com.lisakzbigniew.flashcardsapi.service.FlashCardService;

@RestController
@RequestMapping("/api/phrases")
public class PhrasesAPIController {

    @Autowired
    private FlashCardService service;

    @GetMapping("/list")
    @ResponseBody
    public Phrase[] listPhrases() {
        ArrayList<Phrase> list = new ArrayList<Phrase>();
        service.listPhrases().iterator().forEachRemaining(list::add);
        ;
        return list.toArray(new Phrase[list.size()]);
    }

    @RequestMapping(value = "/add", method = { RequestMethod.PUT, RequestMethod.POST })
    public ResponseEntity<Phrase> addPhrase(@RequestBody Phrase newPhrase) {
        return new ResponseEntity<>(service.savePhrase(newPhrase), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id:[\\d]+}")
    @ResponseBody
    public ResponseEntity<Phrase> findPhrase(@PathVariable Long id) {
        Optional<Phrase> found = service.findPhraseById(id);
        if (!found.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Phrase>(found.get(), HttpStatus.OK);
    }

}
