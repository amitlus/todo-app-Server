package com.amit.todoapp.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String description;
    private LocalDate dueDate;
    private boolean isDone;

    @Override
    public String toString() {
        return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
                + dueDate + ", done=" + isDone + "]";
    }
}
