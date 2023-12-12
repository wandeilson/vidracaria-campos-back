package com.vidracariaCampos.enums;

public enum CustomerType {
    FISICA("FÍSICA"),
    JURIDICA("JURÍDICA");

    private String description;
    CustomerType(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
