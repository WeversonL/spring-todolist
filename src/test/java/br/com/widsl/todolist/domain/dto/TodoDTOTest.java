package br.com.widsl.todolist.domain.dto;

import br.com.widsl.todolist.domain.dto.TodoDTO.TodoDTOBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TodoDTO Test")
class TodoDTOTest {

    private TodoDTO todoDTO;

    @BeforeEach
    void setup() {
        todoDTO = new TodoDTO(1L, "Todo 1", "Description 1", 1, false);
    }

    @Test
    @DisplayName("Should correctly convert TodoDTO object to string")
    void toStringCorrectConversion() {
        String expectedString = "TodoDTO(id=1, name=Todo 1, description=Description 1, priority=1, realized=false)";
        String actualString = todoDTO.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Should return different hashcodes for two non-equal TodoDTO objects")
    void hashCodeForNonEqualTodoDTOObjects() {
        TodoDTO todoDTO2 = new TodoDTO(2L, "Todo 2", "Description 2", 2, true);

        assertNotEquals(todoDTO.hashCode(), todoDTO2.hashCode());
    }

    @Test
    @DisplayName("Should return the same hashcode for two equal TodoDTO objects")
    void hashCodeForEqualTodoDTOObjects() {
        TodoDTO todoDTO2 = new TodoDTO(1L, "Todo 1", "Description 1", 1, false);

        assertEquals(todoDTO.hashCode(), todoDTO2.hashCode());
    }

    @Test
    @DisplayName("Should return true when comparing TodoDTO objects with same id")
    void equalsWhenComparingTodoDTOObjectsWithSameId() {
        TodoDTO todoDTO2 = new TodoDTO(1L, "Todo 2", "Description 2", 2, true);

        boolean result = todoDTO.equals(todoDTO2);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return true when comparing the same TodoDTO object")
    void equalsWhenComparingSameTodoDTOObject() {
        TodoDTO sameTodoDTO = new TodoDTO(1L, "Todo 1", "Description 1", 1, false);

        boolean result = todoDTO.equals(sameTodoDTO);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when comparing with different class object")
    void equalsWhenComparingWithDifferentClassObject() {
        Object obj = new Object();
        boolean result = todoDTO.equals(obj);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when comparing TodoDTO objects with different ids")
    void equalsWhenComparingTodoDTOObjectsWithDifferentIds() {
        TodoDTO todoDTO2 = new TodoDTO(2L, "Todo 2", "Description 2", 2, true);
        boolean result = todoDTO.equals(todoDTO2);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when comparing with null")
    void equalsWhenComparingWithNull() {
        boolean result = todoDTO.equals(null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when compared with null")
    void equalsWhenComparedWithNull() {
        boolean result = todoDTO.equals(null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true when compared with object having same id")
    void equalsWhenComparedWithObjectHavingSameId() {
        TodoDTO todoDTO2 = new TodoDTO(1L, "Todo 2", "Description 2", 2, true);

        boolean result = todoDTO.equals(todoDTO2);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return true when compared with the same object")
    void equalsWhenComparedWithSameObject() {
        TodoDTO sameTodoDTO = new TodoDTO(1L, "Todo 1", "Description 1", 1, false);

        boolean result = todoDTO.equals(sameTodoDTO);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when compared with different class object")
    void equalsWhenComparedWithDifferentClassObject() {
        Object obj = new Object();
        boolean result = todoDTO.equals(obj);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when compared with object having different id")
    void equalsWhenComparedWithObjectHavingDifferentId() {
        TodoDTO otherTodoDTO = new TodoDTO(2L, "Todo 2", "Description 2", 2, true);
        assertFalse(todoDTO.equals(otherTodoDTO));
    }

    @Test
    @DisplayName("Should return true when the object is an instance of TodoDTO")
    void canEqualWhenObjectIsInstanceOfTodoDTO() {
        Object other = new TodoDTO(1L, "Todo 1", "Description 1", 1, false);
        boolean result = todoDTO.canEqual(other);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when the object is not an instance of TodoDTO")
    void canEqualWhenObjectIsNotInstanceOfTodoDTO() {
        Object object = new Object();
        boolean result = todoDTO.canEqual(object);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return a new instance of TodoDTOBuilder")
    void builderReturnsNewInstanceOfTodoDTOBuilder() {
        TodoDTOBuilder builder = TodoDTO.builder();
        assertNotNull(builder);
    }

    @Test
    @DisplayName("Should build a new instance of TodoDTO with provided parameters")
    void builderBuildsNewInstanceOfTodoDTOWithProvidedParameters() {
        TodoDTOBuilder builder = TodoDTO.builder();
        Long id = 1L;
        String name = "Test Todo";
        String description = "This is a test todo";
        Integer priority = 1;
        boolean realized = false;

        TodoDTO todoBuilded = builder
                .id(id)
                .name(name)
                .description(description)
                .priority(priority)
                .realized(realized)
                .build();

        assertNotNull(todoBuilded);
        assertEquals(id, todoBuilded.getId());
        assertEquals(name, todoBuilded.getName());
        assertEquals(description, todoBuilded.getDescription());
        assertEquals(priority, todoBuilded.getPriority());
        assertEquals(realized, todoBuilded.getRealized());
    }

    @Test
    @DisplayName("Checks if TodoDTO is building correctly")
    void testTodoDTO() {
        TodoDTO newEntity = new TodoDTO(1L, "Todo 1", "Description 1", 1, false);

        assertEquals(1L, newEntity.getId());
        assertEquals("Todo 1", newEntity.getName());
        assertEquals("Description 1", newEntity.getDescription());
        assertEquals(1, newEntity.getPriority());
        assertEquals(false, newEntity.getRealized());
    }

    @Test
    @DisplayName("Checks if TodoDTO is building correctly without parameters")
    void testTodoDTOWithoutParameters() {
        TodoDTO newEntity = new TodoDTO();
        assertNotNull(newEntity);
    }

    @Test
    @DisplayName("Should set the realized correctly")
    void setRealizedCorrectly() {
        boolean newRealized = true;
        todoDTO.setRealized(newRealized);
        assertEquals(newRealized, todoDTO.getRealized());
    }

    @Test
    @DisplayName("Should set the priority correctly")
    void setPriorityCorrectly() {
        Integer newPriority = 2;
        todoDTO.setPriority(newPriority);
        assertEquals(newPriority, todoDTO.getPriority());
    }

    @Test
    @DisplayName("Should set the description correctly")
    void setDescriptionCorrectly() {
        String newDescription = "Description 2";
        todoDTO.setDescription(newDescription);
        assertEquals(newDescription, todoDTO.getDescription());
    }

    @Test
    @DisplayName("Should set the name correctly")
    void setNameCorrectly() {
        String newName = "Todo 2";
        todoDTO.setName(newName);
        assertEquals(newName, todoDTO.getName());
    }

    @Test
    @DisplayName("Should set the id correctly")
    void setIdCorrectly() {
        Long newId = 2L;
        todoDTO.setId(newId);
        assertEquals(newId, todoDTO.getId());
    }

    @Test
    @DisplayName("Should return the correct realized status of the TodoDTO")
    void getRealizedReturnsCorrectStatus() {
        boolean expectedRealizedStatus = false;
        boolean actualRealizedStatus = todoDTO.getRealized();

        assertEquals(expectedRealizedStatus, actualRealizedStatus);
    }

    @Test
    @DisplayName("Should return the correct priority of the TodoDTO")
    void getPriorityReturnsCorrectValue() {
        int expectedPriority = 1;
        int actualPriority = todoDTO.getPriority();

        assertEquals(expectedPriority, actualPriority);
    }

    @Test
    @DisplayName("Should return the correct description of the TodoDTO")
    void getDescriptionReturnsCorrectDescription() {
        String description = todoDTO.getDescription();

        assertEquals("Description 1", description);
    }

    @Test
    @DisplayName("Should return the correct name of the TodoDTO")
    void getNameReturnsCorrectName() {
        String expectedName = "Todo 1";
        String actualName = todoDTO.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    @DisplayName("Should return the correct id of the TodoDTO")
    void getIdReturnsCorrectId() {
        Long expectedId = 1L;
        Long actualId = todoDTO.getId();
        assertEquals(expectedId, actualId);
    }
}