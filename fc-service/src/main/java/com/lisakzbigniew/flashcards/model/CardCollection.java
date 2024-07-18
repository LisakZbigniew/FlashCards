package com.lisakzbigniew.flashcards.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * A collection of flash cards belonging to
 * an owner(might support mulitiple users later on)
 */
@Entity
public class CardCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    List<Card> cards;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'root'")
    private String owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "{\n" +
                "id = " + getId().toString() + ", \n" +
                "name = " + getName().toString() + ", \n" +
                "cards = [\n" + getCards().stream().map(Card::toString).collect(Collectors.joining(",\n")) + "\n], \n" +
                "owner = " + getOwner().toString() + " \n" +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CardCollection)) {
            return false;
        }
        CardCollection cardCollection = (CardCollection) o;
        return Objects.equals(id, cardCollection.id) ||
                Objects.equals(name, cardCollection.name) &&
                Objects.equals(cards, cardCollection.cards) &&
                Objects.equals(owner, cardCollection.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cards, owner);
    }

}
