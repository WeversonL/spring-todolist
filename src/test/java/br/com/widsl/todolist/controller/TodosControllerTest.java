package br.com.widsl.todolist.controller;

import br.com.widsl.todolist.domain.dto.TodoDTO;
import br.com.widsl.todolist.service.impl.TodoServiceImpl;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TodosController Test")
class TodosControllerTest {

        @Mock
        private TodoServiceImpl todoService;

        @InjectMocks
        private TodosController todosController;

        @Test
        @DisplayName("Should throw an exception when the given id does not exist")
        void deleteTodoWhenIdDoesNotExistThenThrowException() {
                Long id = 1L;
                when(todoService.deleteTodo(id))
                                .thenThrow(new ValidationException("Todo with id " + id + " does not exist"));

                assertThatThrownBy(() -> todosController.deleteTodo(id))
                                .isInstanceOf(ValidationException.class)
                                .hasMessage("Todo with id " + id + " does not exist");

                verify(todoService, times(1)).deleteTodo(id);
        }

        @Test
        @DisplayName("Should delete the todo when the given id exists")
        void deleteTodoWhenIdExists() {
                Long id = 1L;
                List<TodoDTO> expectedTodos = List.of(
                                TodoDTO.builder().id(2L).name("Todo 2").description("Description 2").priority(2)
                                                .realized(false).build(),
                                TodoDTO.builder().id(3L).name("Todo 3").description("Description 3").priority(3)
                                                .realized(true).build());

                when(todoService.deleteTodo(id)).thenReturn(expectedTodos);

                List<TodoDTO> result = todosController.deleteTodo(id);

                assertThat(result).isEqualTo(expectedTodos);
                verify(todoService, times(1)).deleteTodo(id);
        }

        @Test
        @DisplayName("Should return the updated list of todos after deletion")
        void returnUpdatedListAfterDeletion() {
                Long todoId = 1L;

                List<TodoDTO> updatedTodoList = List.of(
                                TodoDTO.builder()
                                                .id(2L)
                                                .name("Todo 2")
                                                .description("Description 2")
                                                .priority(2)
                                                .realized(false)
                                                .build(),
                                TodoDTO.builder()
                                                .id(3L)
                                                .name("Todo 3")
                                                .description("Description 3")
                                                .priority(3)
                                                .realized(false)
                                                .build());

                when(todoService.deleteTodo(todoId)).thenReturn(updatedTodoList);

                List<TodoDTO> result = todosController.deleteTodo(todoId);

                assertThat(result).isEqualTo(updatedTodoList);
                verify(todoService, times(1)).deleteTodo(todoId);
        }

        @Test
        @DisplayName("Should throw an exception when the given id does not exist")
        void updateTodoWhenIdDoesNotExistThenThrowException() {
                Long id = 1L;
                TodoDTO dto = TodoDTO.builder()
                                .id(id)
                                .name("Test Todo")
                                .description("Test Description")
                                .priority(1)
                                .realized(false)
                                .build();

                when(todoService.updateTodo(dto)).thenThrow(new ValidationException("Todo not found"));

                assertThatThrownBy(() -> todosController.updateTodo(id, dto))
                                .isInstanceOf(ValidationException.class)
                                .hasMessage("Todo not found");

                verify(todoService, times(1)).updateTodo(dto);
        }

        @Test
        @DisplayName("Should update the todo when the given id exists")
        void updateTodoWhenIdExists() {
                Long id = 1L;
                TodoDTO dto = TodoDTO.builder()
                                .id(id)
                                .name("Update Todo")
                                .description("Updated description")
                                .priority(2)
                                .realized(false)
                                .build();

                List<TodoDTO> updatedTodos = List.of(dto);

                when(todoService.updateTodo(dto)).thenReturn(updatedTodos);

                List<TodoDTO> result = todosController.updateTodo(id, dto);

                assertThat(result).isEqualTo(updatedTodos);
                verify(todoService, times(1)).updateTodo(dto);
        }

        @Test
        @DisplayName("Should return updated todo list after successful update")
        void returnUpdatedTodoListAfterSuccessfulUpdate() {
                Long id = 1L;
                TodoDTO dto = TodoDTO.builder()
                                .id(id)
                                .name("Update Todo")
                                .description("Updated description")
                                .priority(2)
                                .realized(false)
                                .build();

                List<TodoDTO> updatedTodoList = List.of(dto);

                when(todoService.updateTodo(dto)).thenReturn(updatedTodoList);

                List<TodoDTO> result = todosController.updateTodo(id, dto);

                assertThat(result).isEqualTo(updatedTodoList);
                verify(todoService, times(1)).updateTodo(dto);
        }

        @Test
        @DisplayName("Should throw an exception when the input for the new todo is invalid")
        void createTodoWithInvalidInputThenThrowException() {
                TodoDTO invalidTodo = TodoDTO.builder()
                                .name("")
                                .description("")
                                .priority(null)
                                .realized(false)
                                .build();

                when(todoService.createTodo(invalidTodo)).thenThrow(ValidationException.class);

                assertThatThrownBy(() -> todosController.createTodo(invalidTodo))
                                .isInstanceOf(ValidationException.class);

                verify(todoService, times(1)).createTodo(invalidTodo);
        }

        @Test
        @DisplayName("Should create a new todo and return the updated list of todos")
        void createTodoAndReturnUpdatedList() {
                TodoDTO todoDTO = TodoDTO.builder()
                                .name("Test Todo")
                                .description("Test Description")
                                .priority(1)
                                .realized(false)
                                .build();

                List<TodoDTO> updatedTodoList = List.of(
                                TodoDTO.builder()
                                                .id(1L)
                                                .name("Existing Todo")
                                                .description("Existing Description")
                                                .priority(2)
                                                .realized(true)
                                                .build(),
                                todoDTO);

                when(todoService.createTodo(todoDTO)).thenReturn(updatedTodoList);

                List<TodoDTO> result = todosController.createTodo(todoDTO);

                assertThat(result).isEqualTo(updatedTodoList);
                verify(todoService, times(1)).createTodo(todoDTO);
        }

        @Test
        @DisplayName("Should return a page of todos when pageable is provided")
        void allTodosPageableWhenPageableIsProvided() {
                Pageable pageable = PageRequest.of(0, 10);
                List<TodoDTO> todos = List.of(
                                TodoDTO.builder().id(1L).name("Todo 1").description("Description 1").priority(1)
                                                .realized(false).build(),
                                TodoDTO.builder().id(2L).name("Todo 2").description("Description 2").priority(2)
                                                .realized(true).build(),
                                TodoDTO.builder().id(3L).name("Todo 3").description("Description 3").priority(3)
                                                .realized(false).build());
                Page<TodoDTO> expectedPage = new PageImpl<>(todos, pageable, todos.size());

                when(todoService.findAndSortTodosPage(pageable)).thenReturn(expectedPage);

                Page<TodoDTO> result = todosController.allTodosPageable(pageable);

                assertThat(result).isEqualTo(expectedPage);
                verify(todoService, times(1)).findAndSortTodosPage(pageable);
        }

        @Test
        @DisplayName("Should return all todos sorted")
        void listAllTodos() {
                List<TodoDTO> expectedTodos = List.of(
                                TodoDTO.builder().id(1L).name("Todo 1").description("Description 1").priority(1)
                                                .realized(false).build(),
                                TodoDTO.builder().id(2L).name("Todo 2").description("Description 2").priority(2)
                                                .realized(true).build(),
                                TodoDTO.builder().id(3L).name("Todo 3").description("Description 3").priority(3)
                                                .realized(false).build());

                when(todoService.findAndSortTodos()).thenReturn(expectedTodos);

                List<TodoDTO> actualTodos = todosController.listAllTodos();

                assertThat(actualTodos).isEqualTo(expectedTodos);
                verify(todoService, times(1)).findAndSortTodos();
        }
}