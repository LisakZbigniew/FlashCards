package com.lisakzbigniew.flashcardsapi.model;

import java.util.Optional;

/**
 * A language for a phrase, one supported by google translationAPI
 * List: https://cloud.google.com/translate/docs/languages
 */
public enum Language {
    ENGLISH("en"),
    SPANISH("es");

    private final String googleLangCode;

    Language(String code){
        this.googleLangCode = code; 
    }

    public String googleCode(){
        return this.googleLangCode;
    }

    public static Language fromGoogleCode(String code) throws IllegalArgumentException{
        switch(code){
            case "en":
                return ENGLISH;
            case "es":
                return SPANISH;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Optional<Language> parseString(String lang){
        try{
            return Optional.of(Language.valueOf(lang.toUpperCase()));
        }catch(IllegalArgumentException e){
            try{
                return Optional.of(Language.fromGoogleCode(lang.toLowerCase()));
            }catch(IllegalArgumentException ex){
                return Optional.empty();
            }
        }
    }
}
