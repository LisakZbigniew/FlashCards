package com.lisakzbigniew.flashcardsapi.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** A Phrase in a given language */
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

    public boolean inLang(Language lang){
        return this.lang.equals(lang);
    }

    @Override
    public String toString() {
        return "{\n" +
                "content = " + getContent().toString() + ", \n" +
                "lang = " + getLang().toString() + " \n" +
                "} \n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Phrase phrase)) {
            return false;
        }
        return Objects.equals(id, phrase.id) ||
                Objects.equals(content, phrase.content) &&
                        Objects.equals(lang, phrase.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, lang);
    }

}
