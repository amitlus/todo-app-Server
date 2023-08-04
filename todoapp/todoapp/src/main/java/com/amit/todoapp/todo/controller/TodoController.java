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
        //return todoService.findByUsername(username);
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public TodoDto retrieveTodo(@PathVariable String username,
                                @PathVariable int id) {
        //return todoService.findById(id);
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
                                           @PathVariable int id) {
        //todoService.deleteById(id);
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public TodoDto updateTodo(@PathVariable String username,
                              @PathVariable int id, @RequestBody TodoDto todo) {
        //todoService.updateTodo(todo);
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
