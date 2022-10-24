package com.lisakzbigniew.flashcardsapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;



/**
 * Entity representing a flash card with 
 * phrases A and B beeing translations of one another
 */
@Entity
public class Card {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @OneToOne(optional = false) 
    private Phrase a;
    @OneToOne(optional = false) 
    private Phrase b;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FamiliarityLevel level;
    @Column(nullable = false)
    private int streak;
    @ManyToMany private List<Tag> tags;
    private String hint;
    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Phrase getA() {
        return a;
    }
    public void setA(Phrase a) {
        this.a = a;
    }
    public Phrase getB() {
        return b;
    }
    public void setB(Phrase b) {
        this.b = b;
    }
    public FamiliarityLevel getLevel() {
        return level;
    }
    public void setLevel(FamiliarityLevel level) {
        this.level = level;
    }
    public int getStreak() {
        return streak;
    }
    public void setStreak(int streak) {
        this.streak = streak;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public String getHint() {
        return hint;
    }
    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public String toString() {
        return "{" +
            " a='" + getA() + "'" +
            ", b='" + getB() + "'" +
            "}";
    }
   
}
