package br.com.widsl.todolist.exception.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("NotFoundException Test")
class TodoNotFoundExceptionTest {

    @Test
    @DisplayName("Validate that the TodoNotFoundException exception is being instantiated correctly")
    void testTodoNotFoundException() {
        TodoNotFoundException exception = new TodoNotFoundException("message");
        assertEquals("message", exception.getMessage());
    }

}