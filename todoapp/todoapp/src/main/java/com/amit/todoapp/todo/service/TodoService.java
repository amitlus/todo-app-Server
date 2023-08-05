package com.amit.todoapp.todo.service;

import com.amit.todoapp.todo.TodoDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
public class TodoService {
    public List<TodoDto> findUrgentTodos(List<TodoDto> todoList) {
        LocalDate referenceDate = LocalDate.now();
        return getClosestDates(todoList, referenceDate, 3);
    }

    public static List<TodoDto> getClosestDates(List<TodoDto> todosList, LocalDate referenceDate, int count) {
        // Custom comparator to compare the difference between the dates and the reference date
        Comparator<TodoDto> dateComparator = Comparator.comparing(obj -> Math.abs(ChronoUnit.DAYS.between(obj.getDueDate(), referenceDate)));

        // Sort the list in ascending order of the date difference
        todosList.sort(dateComparator);

        // Take the first 'count' elements from the sorted list
        int listSize = todosList.size();
        int effectiveCount = Math.min(count, listSize);
        return todosList.subList(0, effectiveCount);
    }

}
