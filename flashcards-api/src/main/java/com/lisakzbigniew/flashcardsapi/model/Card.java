package com.lisakzbigniew.flashcardsapi.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 * Entity representing a flash card with
 * phrases A and B beeing translations of one another
 */
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = false)
    private Phrase firstPhrase;

    @OneToOne(optional = false)
    private Phrase secondPhrase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Phrase getFirstPhrase() {
        return firstPhrase;
    }

    public void setFirstPhrase(Phrase a) {
        this.firstPhrase = a;
    }

    public Phrase getSecondPhrase() {
        return secondPhrase;
    }

    public void setSecondPhrase(Phrase b) {
        this.secondPhrase = b;
    }

    public boolean inLang(Language lang){
        return firstPhrase.inLang(lang) || secondPhrase.inLang(lang);
    }

    @Override
    public String toString() {
        return "{\n" +
                "firstWord = " + getFirstPhrase().toString() + ", \n" +
                "secondWord = " + getSecondPhrase().toString() + " \n" +
                "} \n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Card card)) {
            return false;
        }
        return Objects.equals(id, card.id)
                || Objects.equals(firstPhrase, card.firstPhrase) && Objects.equals(secondPhrase, card.secondPhrase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstPhrase, secondPhrase);
    }

}
