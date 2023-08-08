package br.com.widsl.todolist.repository;

import br.com.widsl.todolist.domain.entity.TodoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("TodoRepository Test")
class TodoRepositoryTest {

    @Mock
    private TodoRepository todoRepository;

    @Test
    void testFindByIdRepository() {

        TodoEntity expectedTodo = new TodoEntity();
        expectedTodo.setId(1L);
        expectedTodo.setName("x");
        when(todoRepository.findById(expectedTodo.getId())).thenReturn(Optional.of(expectedTodo));

        Optional<TodoEntity> actualTodo = todoRepository.findById(expectedTodo.getId());

        assertEquals(expectedTodo, actualTodo.get());
    }

}
