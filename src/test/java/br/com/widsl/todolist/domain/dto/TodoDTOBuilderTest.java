package br.com.widsl.todolist.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TodoDTOBuilder Test")
class TodoDTOBuilderTest {

    @Test
    @DisplayName("Should correctly build TodoDTO with TodoDTOBuilder")
    void buildTodoDTO() {

        TodoDTO expected = new TodoDTO(1L, "Todo 1", "Description 1", 1, false);

        TodoDTO.TodoDTOBuilder builder = new TodoDTO.TodoDTOBuilder();

        TodoDTO dto = builder
                .id(1L)
                .name("Todo 1")
                .description("Description 1")
                .priority(1)
                .realized(false)
                .build();

        assertEquals(expected, dto);
    }

    @Test
    @DisplayName("Should correctly convert TodoDTOBuilder to string")
    void toStringInTodoDTOBuilder() {
        TodoDTO.TodoDTOBuilder todoDTOBuilder = new TodoDTO.TodoDTOBuilder();
        todoDTOBuilder.id(1L);

        String expected = "TodoDTO.TodoDTOBuilder(id=1, name=null, description=null, priority=null, realized=false)";
        assertEquals(expected, todoDTOBuilder.toString());
    }

    @Test
    @DisplayName("Should set the realized correctly in the TodoDTOBuilder")
    void setRealizedInTodoDTOBuilder() {
        boolean realized = true;
        TodoDTO.TodoDTOBuilder todoDTOBuilder = new TodoDTO.TodoDTOBuilder();

        TodoDTO.TodoDTOBuilder result = todoDTOBuilder.realized(realized);

        assertEquals(realized, result.build().getRealized());
    }

    @Test
    @DisplayName("Should set the priority correctly in the TodoDTOBuilder")
    void setPriorityInTodoDTOBuilder() {
        Integer priority = 1;
        TodoDTO.TodoDTOBuilder todoDTOBuilder = new TodoDTO.TodoDTOBuilder();

        TodoDTO.TodoDTOBuilder result = todoDTOBuilder.priority(priority);

        assertEquals(priority, result.build().getPriority());
    }

    @Test
    @DisplayName("Should set the description correctly in the builder")
    void setDescriptionInBuilder() {
        String description = "Todo Builder";
        TodoDTO.TodoDTOBuilder todoDTOBuilder = new TodoDTO.TodoDTOBuilder();

        TodoDTO.TodoDTOBuilder result = todoDTOBuilder.description(description);

        assertEquals(description, result.build().getDescription());
    }

    @Test
    @DisplayName("Should set the name correctly in the builder")
    void setNameInBuilder() {
        String name = "Todo Builder";
        TodoDTO.TodoDTOBuilder todoDTOBuilder = new TodoDTO.TodoDTOBuilder();

        TodoDTO.TodoDTOBuilder result = todoDTOBuilder.name(name);

        assertEquals(name, result.build().getName());
    }

    @Test
    @DisplayName("Should set the id correctly in the TodoDTOBuilder")
    void setIdInTodoDTOBuilder() {
        Long id = 1L;
        TodoDTO.TodoDTOBuilder todoDTOBuilder = new TodoDTO.TodoDTOBuilder();

        TodoDTO.TodoDTOBuilder result = todoDTOBuilder.id(id);

        assertEquals(id, result.build().getId());
    }
}
