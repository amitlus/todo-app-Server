package com.amit.todoapp.todo.repository;

import com.amit.todoapp.todo.TodoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoDto, Integer> {
    List<TodoDto> findByUsername(String username);

}
