package com.manos.spring5recipeapp.models;

public enum Difficulty {
    EASY("EASY"), MODERATE("MODERATE"),KIND_OF_HARD("KIND_OF_HARD"), HARD("HARD");

    private String difficulty;

    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
