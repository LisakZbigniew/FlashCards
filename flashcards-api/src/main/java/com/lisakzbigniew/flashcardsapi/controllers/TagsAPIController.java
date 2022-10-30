package com.lisakzbigniew.flashcardsapi.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lisakzbigniew.flashcardsapi.model.Tag;
import com.lisakzbigniew.flashcardsapi.service.FlashCardService;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/tags")
public class TagsAPIController {
    
    @Autowired
    private FlashCardService service;

    @GetMapping("/list")
    @ResponseBody
    public Tag[] listTags(){
        ArrayList<Tag> list = new ArrayList<Tag>();
        service.listTags().iterator().forEachRemaining(list::add);;
        return list.toArray(new Tag[list.size()]);
    }

    @RequestMapping(value="/add", method={RequestMethod.PUT,RequestMethod.POST})
    public ResponseEntity<Tag> addTag(@RequestBody Tag newTag) {
        return new ResponseEntity<>(service.saveTag(newTag), HttpStatus.CREATED);
    }
    
    @GetMapping(value="/{id}")
    @ResponseBody
    public ResponseEntity<Tag> findTag(@PathVariable Long id){
        Optional<Tag> found = service.findTagById(id);
        if(!found.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<Tag>(found.get(), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    @ResponseBody
    public ResponseEntity<Tag> removeTag(@PathVariable Long id){
        Optional<Tag> found = service.findTagById(id);
        if(!found.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            Tag toDelete = found.get();
            service.removeTag(toDelete);
            toDelete.setId(null);
            return new ResponseEntity<Tag>(toDelete, HttpStatus.OK);
        }

    }


}
