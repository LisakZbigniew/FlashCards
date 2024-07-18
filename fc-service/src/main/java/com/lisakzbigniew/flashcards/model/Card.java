package com.lisakzbigniew.flashcards.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Entity representing a flash card with
 * phrases A and B beeing translations of one another
 */
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private Phrase firstPhrase;

    @ManyToOne(optional = false)
    private Phrase secondPhrase;

    public Card() {
    }

    public Card(Phrase first, Phrase second) {
        firstPhrase = first;
        secondPhrase = second;
    }

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

    public boolean inLanguage(Language lang) {
        return firstPhrase.inLanguage(lang) || secondPhrase.inLanguage(lang);
    }

    public void fixPhraseOrder() {
        if (secondPhrase != null &&
                (firstPhrase == null || secondPhrase.getLanguage().compareTo(firstPhrase.getLanguage()) < 0)) {
            Phrase tmp = firstPhrase;
            firstPhrase = secondPhrase;
            secondPhrase = tmp;
        }
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
                || (firstPhrase.equals(card.firstPhrase) && secondPhrase.equals(card.secondPhrase))
                || (secondPhrase.equals(card.firstPhrase) && firstPhrase.equals(card.secondPhrase));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstPhrase, secondPhrase);
    }

}
