package br.com.widsl.todolist.exception.advice;

import br.com.widsl.todolist.constants.ExceptionMessages;
import br.com.widsl.todolist.exception.impl.TodoNotFoundException;
import br.com.widsl.todolist.exception.model.ApiErrorResponse;
import br.com.widsl.todolist.exception.model.ErrorValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleTodoNotFoundException(TodoNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(ExceptionMessages.NOT_FOUND_EXCEPTION)
                        .description(ExceptionMessages.NOT_FOUND_EXCEPTION_DESCRIPTION)
                        .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ExceptionMessages.BAD_REQUEST_EXCEPTION)
                .description(ExceptionMessages.BAD_REQUEST_EXCEPTION_DESCRIPTION)
                .build();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String description = Objects.requireNonNull(error.getDefaultMessage()).formatted(error.getField());
            apiErrorResponse.addError(new ErrorValidation(description));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

}
