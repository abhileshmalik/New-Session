package com.todolist.TodoList.service;

import com.todolist.TodoList.entity.TodoList;
import com.todolist.TodoList.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodolistService {

    @Autowired
    TodoListRepository todoListRepository;


    public void addNewTodo(TodoList todoList) {
        todoList.setCompleted(false);
        todoListRepository.save(todoList);
    }

    public List<TodoList> findAll() {
        List<TodoList> todoLists = (List<TodoList>) todoListRepository.findAll();
        return todoLists;
    }

    public void deleteTodo(Long id) {
        todoListRepository.deleteById(id);
    }

    public void markComplete(Long id) {
        Optional<TodoList> optionalTodoTask = todoListRepository.findById(id);

        if(optionalTodoTask.isPresent()) {
            TodoList todoList = optionalTodoTask.get();

            if(!todoList.getCompleted()) {
                todoList.setCompleted(true);
            } else {
                todoList.setCompleted(false);
            }

            todoListRepository.save(todoList);

        }
    }
}
