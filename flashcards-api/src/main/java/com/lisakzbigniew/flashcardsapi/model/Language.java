package com.lisakzbigniew.flashcardsapi.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A language for a phrase
 */
public enum Language {
    ENGLISH,
    SPANISH;

    private static Logger LOG = LoggerFactory.getLogger(Language.class);

    public static Language fromString(String stringValue) {
        try {
            return valueOf(stringValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            switch (stringValue.toUpperCase()) {
                case "ESP":
                case "ESPANOL":
                    return SPANISH;
                case "ENG":
                case "AMERICAN":
                case "BRITISH":
                    return ENGLISH;
                default:
                    LOG.debug("Unrecognised language %s defaulting to ENGLISH", stringValue);
                    return ENGLISH;
            }
        }
    }
}
