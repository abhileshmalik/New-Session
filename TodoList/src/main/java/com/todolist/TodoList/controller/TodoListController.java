package com.todolist.TodoList.controller;

import com.todolist.TodoList.entity.TodoList;
import com.todolist.TodoList.service.TodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoListController {

    @Autowired
    TodolistService todolistService;

    @GetMapping("/todos")
    public List<TodoList> retrievMyTodoList() {
        return todolistService.findAll();
    }

    @PostMapping("/todos")
    public void viewTodoList(@RequestBody TodoList todoList) {
        todolistService.addNewTodo(todoList);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodoTask(@PathVariable Long id) {
        todolistService.deleteTodo(id);
    }

    @PatchMapping("/todos/{id}")
    public void completeTodo(@PathVariable Long id) {
        todolistService.markComplete(id);
    }


}
