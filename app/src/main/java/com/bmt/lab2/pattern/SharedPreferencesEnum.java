package com.bmt.lab2.pattern;

import lombok.Getter;

public enum SharedPreferencesEnum {
    KEY("notes"),SHAREDPREFERENCESNAME("shared preferences");
    @Getter
    private String value;
    private SharedPreferencesEnum(String value){
        this.value = value;
    }
}
