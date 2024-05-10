package com.example.newpersonal.models.enums;

public enum TaskStatus {
    START ("Начинается"),
    IN_WORK ("В процессе"),
    CHECK_AFTER ("Проверка"),
    READY ("Готова");

    private final String russianTranslation;

    TaskStatus(String russianTranslation) {
        this.russianTranslation = russianTranslation;
    }

    public String getRussianTranslation() {
        return russianTranslation;
    }
}