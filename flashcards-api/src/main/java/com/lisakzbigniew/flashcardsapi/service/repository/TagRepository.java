package com.lisakzbigniew.flashcardsapi.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.lisakzbigniew.flashcardsapi.model.Tag;

public interface TagRepository extends CrudRepository<Tag,Long>{
    
}
