package com.ankita.todoList.DataModel;

import java.time.LocalDate;

public class TodoItem {
    private String details;
    private String shortDescription;
    private LocalDate deadLine;

    public TodoItem(String details, String shortDescription, LocalDate deadLine) {
        this.details = details;
        this.shortDescription = shortDescription;
        this.deadLine = deadLine;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return details;
    }
}
