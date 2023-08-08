package br.com.widsl.todolist.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ExceptionMessages Test")
class ExceptionMessagesTest {

    @Test
    void testNotFoundException() {
        assertEquals("Not Found", ExceptionMessages.NOT_FOUND_EXCEPTION);
    }

    @Test
    void testNotFoundExceptionDescription() {
        assertEquals("No data found for this todo", ExceptionMessages.NOT_FOUND_EXCEPTION_DESCRIPTION);
    }

    @Test
    void testBadRequestException() {
        assertEquals("Bad Request", ExceptionMessages.BAD_REQUEST_EXCEPTION);
    }

    @Test
    void testBadRequestExceptionDescription() {
        assertEquals("There were validation errors in the submitted fields",
                ExceptionMessages.BAD_REQUEST_EXCEPTION_DESCRIPTION);
    }
}
