package br.com.widsl.todolist.service.impl;

import br.com.widsl.todolist.domain.dto.TodoDTO;
import br.com.widsl.todolist.domain.entity.TodoEntity;
import br.com.widsl.todolist.exception.impl.TodoNotFoundException;
import br.com.widsl.todolist.repository.TodoRepository;
import br.com.widsl.todolist.service.TodoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TodoServiceImpl implements TodoService {

    private final Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);

    private final ModelMapper mapper;
    private final TodoRepository todoRepository;

    private static final String SORT_PRIORITY = "priority";
    private static final String SORT_ID = "id";

    public TodoServiceImpl(ModelMapper mapper, TodoRepository todoRepository) {
        this.mapper = mapper;
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoDTO> findAndSortTodos() {

        var result = todoRepository.findAll(getSort());

        return result.stream()
                .map(x -> mapper.map(x, TodoDTO.class))
                .toList();
    }

    @Override
    public Page<TodoDTO> findAndSortTodosPage(Pageable pageable) {

        Pageable pageableRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), getSort());

        var result = todoRepository.findAll(pageableRequest);

        var parsedResult = result.stream()
                .map(x -> mapper.map(x, TodoDTO.class))
                .toList();

        return new PageImpl<>(parsedResult, PageRequest.of(result.getNumber(), result.getSize()),
                result.getTotalElements());

    }

    @Override
    public List<TodoDTO> createTodo(TodoDTO todoDTO) {

        var todo = mapper.map(todoDTO, TodoEntity.class);
        todoRepository.save(todo);
        log.info("The todo {} was successfully created", todo);
        return findAndSortTodos();
    }

    @Override
    public List<TodoDTO> updateTodo(TodoDTO todoDTO) {

        todoRepository.findById(todoDTO.getId()).ifPresentOrElse(existingTodo -> {
            var todo = mapper.map(todoDTO, TodoEntity.class);
            todoRepository.save(todo);
            log.info("The todo {} was successfully updated", todo);
        }, () -> {
            log.info("The todo with id {} does not exist", todoDTO.getId());
            throw new TodoNotFoundException("The specified todo %d does not exist".formatted(todoDTO.getId()));
        });

        return findAndSortTodos();
    }

    @Override
    public List<TodoDTO> deleteTodo(Long id) {

        todoRepository.findById(id).ifPresentOrElse(existingTodo -> {
            todoRepository.delete(existingTodo);
            log.info("The todo {} was successfully deleted", existingTodo);
        },
                () -> {
                    log.info("The todo with id {} does not exist", id);
                    throw new TodoNotFoundException("The specified todo %d does not exist".formatted(id));
                });

        return findAndSortTodos();
    }

    private static Sort getSort() {
        return Sort.by(Direction.DESC, SORT_PRIORITY)
                .and(Sort.by(Direction.ASC, SORT_ID));
    }

}
