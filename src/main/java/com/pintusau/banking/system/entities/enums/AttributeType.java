package com.pintusau.banking.system.entities.enums;

public enum AttributeType {

    STRING("String"),
    NUMBER("Number");

    private final String type;

    AttributeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}