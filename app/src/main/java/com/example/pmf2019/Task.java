package com.example.pmf2019;

public class Task {
    int id;
    String tittle;
    String description;

    public Task(int id) {
        this.id = id;
    }

    public Task(int id, String tittle) {
        this.id = id;
        this.tittle = tittle;
    }

    public Task(int id, String tittle, String description) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
