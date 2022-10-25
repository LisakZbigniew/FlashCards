package com.lisakzbigniew.flashcardsapi.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/** A collection of flash cards belonging to
 * an owner(might support mulitiple users later on) */
@Entity
public class CardCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany List<Card> cards;
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
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", cards='" + getCards() + "'" +
            ", owner='" + getOwner() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CardCollection)) {
            return false;
        }
        CardCollection cardCollection = (CardCollection) o;
        return Objects.equals(id, cardCollection.id) && Objects.equals(name, cardCollection.name) && Objects.equals(cards, cardCollection.cards) && Objects.equals(owner, cardCollection.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cards, owner);
    }

    
}
