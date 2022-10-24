package com.lisakzbigniew.flashcardsapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**A Phrase in a given language*/
@Entity
public class Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language lang;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Language getLang() {
        return lang;
    }
    public void setLang(Language lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "{" +
            " content='" + getContent() + "'" +
            ", lang='" + getLang() + "'" +
            "}";
    }
    
    
}
