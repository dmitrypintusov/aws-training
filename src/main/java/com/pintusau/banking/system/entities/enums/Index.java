package com.pintusau.banking.system.entities.enums;

public enum Index {

    LOCAL_ID_EMAIL ("id-email-index"),
    GLOBAL_FIRST_NAME_LAST_NAME ("firstName-lastName-index");

    private String indexName;

    Index(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexName() {
        return indexName;
    }
}