package com.lisakzbigniew.flashcards.model;

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
    private Language language;

    public Phrase() {
    }

    public Phrase(String content, Language lang) {
        this.content = content;
        this.language = lang;
    }

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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language lang) {
        this.language = lang;
    }

    public boolean inLanguage(Language lang) {
        return this.language.equals(lang);
    }

    @Override
    public String toString() {
        return "{\n" +
                "content = " + getContent().toString() + ", \n" +
                "language = " + getLanguage().toString() + " \n" +
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
                content.equals(phrase.content) &&
                        Objects.equals(language, phrase.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, language);
    }

}
