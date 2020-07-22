package com.todolist.TodoList.repository;

import com.todolist.TodoList.entity.TodoList;
import org.springframework.data.repository.CrudRepository;

public interface TodoListRepository extends CrudRepository<TodoList, Long> {

}
