package com.ideas;

/**
 * All string constants go here.
 */
public enum Constants {

    SPACE(" "), COMMA(","), HYPHEN("-");

    private String value;

    Constants(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }

}
