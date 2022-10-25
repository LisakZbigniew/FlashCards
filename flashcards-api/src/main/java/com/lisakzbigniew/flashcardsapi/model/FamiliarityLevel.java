package com.lisakzbigniew.flashcardsapi.model;


/**A measure of how well a given flashcard is mastered*/
public enum FamiliarityLevel {
    NEW,LEARNING,KNOWN,MASTERED;

    public FamiliarityLevel next(){
        switch(this){
            case NEW:
                return LEARNING;
            case LEARNING:
                return KNOWN;
            case KNOWN:
                return MASTERED;
            case MASTERED:
                return MASTERED;
            default:
                return NEW;
        }
    }
}
