package br.com.widsl.todolist.service.impl;

import br.com.widsl.todolist.domain.dto.TodoDTO;
import br.com.widsl.todolist.domain.entity.TodoEntity;
import br.com.widsl.todolist.exception.impl.TodoNotFoundException;
import br.com.widsl.todolist.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TodoServiceImpl tests")
class TodoServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    private List<TodoDTO> todoDTOList;
    private List<TodoEntity> todoEntityList;

    @BeforeEach
    void setup() {
        todoDTOList = List.of(
                TodoDTO.builder().id(1L).name("Todo 1").description("Description 1").priority(1).realized(false)
                        .build(),
                TodoDTO.builder().id(2L).name("Todo 2").description("Description 2").priority(2).realized(true).build(),
                TodoDTO.builder().id(3L).name("Todo 3").description("Description 3").priority(3).realized(false)
                        .build());

        todoEntityList = List.of(
                new TodoEntity(1L, "Todo 1", "Description 1", 1, false),
                new TodoEntity(2L, "Todo 2", "Description 2", 2, true),
                new TodoEntity(3L, "Todo 3", "Description 3", 3, false));
    }

    private static Sort getSort() {
        return Sort.by(Sort.Direction.DESC, "priority")
                .and(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    @DisplayName("Should return an empty page when pageable is provided but no todos exist")
    void testFindAndSortTodosPageWhenNoTodosExist() {

        List<TodoEntity> fakeTodoList = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, 10);
        Page<TodoEntity> fakeTodoPage = new PageImpl<>(fakeTodoList, pageable, 0);

        when(todoRepository.findAll(any(Pageable.class))).thenReturn(fakeTodoPage);

        Page<TodoDTO> result = todoService.findAndSortTodosPage(pageable);

        verify(todoRepository, times(1)).findAll(any(Pageable.class));
        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getContent().size());

    }

    @Test
    @DisplayName("Should return a page of sorted TodoDTOs when valid pageable is provided")
    void testFindAndSortTodosPage() {

        List<TodoEntity> fakeTodoList = new ArrayList<>();
        fakeTodoList.add(new TodoEntity());
        fakeTodoList.add(new TodoEntity());

        Pageable pageable = PageRequest.of(0, 10);
        Page<TodoEntity> fakeTodoPage = new PageImpl<>(fakeTodoList, pageable, fakeTodoList.size());

        when(todoRepository.findAll(any(Pageable.class))).thenReturn(fakeTodoPage);

        Page<TodoDTO> resultPage = todoService.findAndSortTodosPage(pageable);

        verify(todoRepository, times(1)).findAll(any(Pageable.class));
        assertEquals(1, resultPage.getTotalPages());
        assertEquals(2, resultPage.getNumberOfElements());
        assertEquals(10, resultPage.getSize());

    }

    @Test
    @DisplayName("Should return a list of TodoDTOs sorted by priority in descending order and id in ascending order")
    void testFindAndSortTodos() {

        when(todoRepository.findAll(getSort())).thenReturn(todoEntityList);
        when(modelMapper.map(any(TodoEntity.class), eq(TodoDTO.class))).thenReturn(todoDTOList.get(0), todoDTOList.get(1), todoDTOList.get(2));

        List<TodoDTO> result = todoService.findAndSortTodos();

        assertEquals(todoDTOList, result);
        verify(todoRepository, times(1)).findAll(getSort());
        verify(modelMapper, times(3)).map(any(TodoEntity.class), eq(TodoDTO.class));

    }

    @Test
    @DisplayName("Should create a new todo and return a sorted list of todos")
    void testCreateTodo() {

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(1L);
        todoEntity.setName("Test Todo");

        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(1L);
        todoDTO.setName("Test Todo");

        when(todoRepository.save(todoEntity)).thenReturn(todoEntity);
        when(modelMapper.map(todoDTO, TodoEntity.class)).thenReturn(todoEntity);

        when(todoRepository.findAll(getSort())).thenReturn(todoEntityList);
        when(modelMapper.map(any(TodoEntity.class), eq(TodoDTO.class))).thenReturn(todoDTOList.get(0),
                todoDTOList.get(1), todoDTOList.get(2));

        List<TodoDTO> result = todoService.createTodo(todoDTO);

        verify(todoRepository, times(1)).save(todoEntity);
        assertEquals(todoDTO.getId(), result.get(0).getId());

    }

    @Test
    @DisplayName("Should throw a TodoNotFoundException when the todo with the given id does not exist")
    void testUpdateTodoWhenTodoDoesNotExistThenThrowException() {

        Long todoId = 4L;
        TodoDTO todoDTO = TodoDTO.builder()
                .id(todoId)
                .name("Todo 4")
                .description("Description 4")
                .priority(4)
                .realized(false)
                .build();

        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

        TodoNotFoundException exception = assertThrows(TodoNotFoundException.class,
                () -> todoService.updateTodo(todoDTO));

        assertEquals("The specified todo 4 does not exist", exception.getMessage());

        verify(todoRepository, times(1)).findById(todoId);
        verify(todoRepository, never()).save(any(TodoEntity.class));

    }

    @Test
    @DisplayName("Should update the todo when the todo with the given id exists")
    void testUpdateTodoWhenTodoExists() {

        TodoDTO todoDTO = TodoDTO.builder()
                .id(1L)
                .name("Updated Todo")
                .description("Updated Description")
                .priority(2)
                .realized(true)
                .build();

        TodoEntity existingTodoEntity = new TodoEntity(1L, "Todo 1", "Description 1", 1, false);
        TodoEntity updatedTodoEntity = new TodoEntity(1L, "Updated Todo", "Updated Description", 2, true);

        when(todoRepository.findById(todoDTO.getId())).thenReturn(Optional.of(existingTodoEntity));
        when(modelMapper.map(todoDTO, TodoEntity.class)).thenReturn(updatedTodoEntity);

        todoService.updateTodo(todoDTO);

        verify(todoRepository, times(1)).findById(todoDTO.getId());
        verify(todoRepository, times(1)).save(updatedTodoEntity);

    }

    @Test
    @DisplayName("Should throw an exception when the id does not exist")
    void deleteTodoWhenIdDoesNotExistThenThrowException() {

        Long id = 1L;
        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        TodoNotFoundException exception = assertThrows(TodoNotFoundException.class, () -> todoService.deleteTodo(id));

        assertEquals("The specified todo 1 does not exist", exception.getMessage());

        verify(todoRepository, times(1)).findById(id);
        verify(todoRepository, never()).delete(any(TodoEntity.class));
        verify(todoRepository, never()).delete(any(TodoEntity.class));
        verifyNoMoreInteractions(todoRepository);

    }

    @Test
    @DisplayName("Should delete the todo when the id exists")
    void deleteTodoWhenIdExists() {

        Long id = 1L;
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(id);

        when(todoRepository.findById(id)).thenReturn(Optional.of(todoEntity));

        List<TodoDTO> result = todoService.deleteTodo(id);

        verify(todoRepository, times(1)).findById(id);
        verify(todoRepository, times(1)).delete(todoEntity);
        assertEquals(result, todoService.findAndSortTodos());

    }
}