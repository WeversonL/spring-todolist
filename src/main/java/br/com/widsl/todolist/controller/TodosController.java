package br.com.widsl.todolist.controller;

import br.com.widsl.todolist.domain.dto.TodoDTO;
import br.com.widsl.todolist.service.TodoService;
import br.com.widsl.todolist.service.impl.TodoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodosController {

    private final TodoService todoService;

    public TodosController(TodoServiceImpl todosService) {
        this.todoService = todosService;
    }

    @GetMapping
    public List<TodoDTO> listAllTodos() {
        return todoService.findAndSortTodos();
    }

    @GetMapping("/pages")
    public Page<TodoDTO> allTodosPageable(Pageable pageable) {
        return todoService.findAndSortTodosPage(pageable);
    }

    @PostMapping
    public List<TodoDTO> createTodo(@RequestBody @Valid TodoDTO dto) {
        return todoService.createTodo(dto);
    }

    @PutMapping("/{id}")
    public List<TodoDTO> updateTodo(@PathVariable("id") Long id, @RequestBody @Valid TodoDTO dto) {
        dto.setId(id);
        return todoService.updateTodo(dto);
    }

    @DeleteMapping("/{id}")
    public List<TodoDTO> deleteTodo(@PathVariable("id") Long id) {
        return todoService.deleteTodo(id);
    }

}
