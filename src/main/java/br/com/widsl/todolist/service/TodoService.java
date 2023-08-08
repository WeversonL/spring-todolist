package br.com.widsl.todolist.service;

import br.com.widsl.todolist.domain.dto.TodoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoService {

    List<TodoDTO> findAndSortTodos();

    Page<TodoDTO> findAndSortTodosPage(Pageable pageable);

    List<TodoDTO> createTodo(TodoDTO todoDTO);

    List<TodoDTO> updateTodo(TodoDTO todoDTO);

    List<TodoDTO> deleteTodo(Long id);

}
