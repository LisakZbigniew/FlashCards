package com.lisakzbigniew.flashcardsapi.model;

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

    public static Language fromGoogleCode(String code){
        switch(code){
            case "en":
                return ENGLISH;
            case "es":
                return SPANISH;
            default:
                return ENGLISH;
        }
    }
}
