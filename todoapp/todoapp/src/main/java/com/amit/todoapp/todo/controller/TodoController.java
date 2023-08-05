package com.amit.todoapp.todo.controller;

import com.amit.todoapp.todo.TodoDto;
import com.amit.todoapp.todo.repository.TodoRepository;
import com.amit.todoapp.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private TodoService todoService;

    private TodoRepository todoRepository;

    public TodoController(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/users/{username}/todos")
    public List<TodoDto> retrieveTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public TodoDto retrieveTodo(@PathVariable String username,
                                @PathVariable int id) {
        return todoRepository.findById(id).get();
    }

    @GetMapping("/users/{username}/todos/urgent")
    public List<TodoDto> retrieveUrgentTodos(@PathVariable String username) {
        List<TodoDto> todosList = todoRepository.findByUsername(username);
        return todoService.findUrgentTodos(todosList);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
                                           @PathVariable int id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public TodoDto updateTodo(@PathVariable String username,
                              @PathVariable int id, @RequestBody TodoDto todo) {
        todoRepository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public TodoDto createTodo(@PathVariable String username,
                              @RequestBody TodoDto todo) {
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
    }

}
