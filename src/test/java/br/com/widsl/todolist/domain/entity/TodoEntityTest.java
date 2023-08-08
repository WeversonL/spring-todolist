package br.com.widsl.todolist.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TodoEntity Test")
class TodoEntityTest {

    private TodoEntity todoEntity;

    @BeforeEach
    void setup() {
        todoEntity = new TodoEntity(1L, "Todo 1", "Description 1", 1, false);
    }

    @Test
    @DisplayName("Should return false when the object is not an instance of TodoEntity")
    void canEqualWhenObjectIsNotInstanceOfTodoEntity() {
        Object object = new Object();
        boolean result = todoEntity.canEqual(object);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true when the object is an instance of TodoEntity")
    void canEqualWhenObjectIsInstanceOfTodoEntity() {
        Object object = new TodoEntity();

        boolean result = todoEntity.canEqual(object);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return the same hashcode for two equal TodoEntity objects")
    void hashCodeForEqualTodoEntityObjects() {
        TodoEntity todoEntity2 = new TodoEntity(1L, "Todo 1", "Description 1", 1, false);

        assertEquals(todoEntity.hashCode(), todoEntity2.hashCode());
    }

    @Test
    @DisplayName("Should return different hashcodes for two non-equal TodoEntity objects")
    void hashCodeForNonEqualTodoEntityObjects() {
        TodoEntity todoEntity2 = new TodoEntity(2L, "Todo 2", "Description 2", 2, true);

        assertNotEquals(todoEntity.hashCode(), todoEntity2.hashCode());
    }

    @Test
    @DisplayName("Should return false when comparing with different class object")
    void equalsWhenComparingWithDifferentClassObject() {
        Object obj = new Object();
        boolean result = todoEntity.equals(obj);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true when comparing the same TodoEntity instance")
    void equalsWhenComparingSameInstance() {

        TodoEntity todoEntity2 = new TodoEntity(1L, "Todo 1", "Description 1", 1, false);

        boolean result = todoEntity.equals(todoEntity2);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return true when comparing TodoEntities with same id")
    void equalsWhenComparingWithSameId() {
        TodoEntity todoEntity2 = new TodoEntity(1L, "Todo 2", "Description 2", 2, true);

        boolean result = todoEntity.equals(todoEntity2);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when comparing TodoEntities with different ids")
    void equalsWhenComparingWithDifferentIds() {
        TodoEntity todoEntity2 = new TodoEntity(2L, "Todo 2", "Description 2", 2, true);

        boolean result = todoEntity.equals(todoEntity2);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when comparing with null")
    void equalsWhenComparingWithNull() {
        TodoEntity other = null;
        boolean result = todoEntity.equals(other);
        assertFalse(result);
    }

    @Test
    @DisplayName("Checks if TodoEntity is building correctly")
    void testTodoEntity() {
        TodoEntity newEntity = new TodoEntity(1L, "Todo 1", "Description 1", 1, false);

        assertEquals(1L, newEntity.getId());
        assertEquals("Todo 1", newEntity.getName());
        assertEquals("Description 1", newEntity.getDescription());
        assertEquals(1, newEntity.getPriority());
        assertEquals(false, newEntity.getRealized());
    }

    @Test
    @DisplayName("Checks if TodoEntity is building correctly without parameters")
    void testTodoEntityWithoutParameters() {
        TodoEntity newEntity = new TodoEntity();
        assertNotNull(newEntity);
    }

    @Test
    @DisplayName("Should set the realized correctly")
    void setRealizedCorrectly() {
        boolean newRealized = true;
        todoEntity.setRealized(newRealized);
        assertEquals(newRealized, todoEntity.getRealized());
    }

    @Test
    @DisplayName("Should set the priority correctly")
    void setPriorityCorrectly() {
        Integer newPriority = 2;
        todoEntity.setPriority(newPriority);
        assertEquals(newPriority, todoEntity.getPriority());
    }

    @Test
    @DisplayName("Should set the description correctly")
    void setDescriptionCorrectly() {
        String newDescription = "Description 2";
        todoEntity.setDescription(newDescription);
        assertEquals(newDescription, todoEntity.getDescription());
    }

    @Test
    @DisplayName("Should set the name correctly")
    void setNameCorrectly() {
        String newName = "Todo 2";
        todoEntity.setName(newName);
        assertEquals(newName, todoEntity.getName());
    }

    @Test
    @DisplayName("Should set the id correctly")
    void setIdCorrectly() {
        Long newId = 2L;
        todoEntity.setId(newId);
        assertEquals(newId, todoEntity.getId());
    }

    @Test
    @DisplayName("Should return the correct realized status of the TodoEntity")
    void getRealizedReturnsCorrectStatus() {
        boolean expectedRealizedStatus = false;
        boolean actualRealizedStatus = todoEntity.getRealized();

        assertEquals(expectedRealizedStatus, actualRealizedStatus);
    }

    @Test
    @DisplayName("Should return the correct priority of the TodoEntity")
    void getPriorityReturnsCorrectValue() {
        int expectedPriority = 1;
        int actualPriority = todoEntity.getPriority();

        assertEquals(expectedPriority, actualPriority);
    }

    @Test
    @DisplayName("Should return the correct description of the TodoEntity")
    void getDescriptionReturnsCorrectDescription() {
        String description = todoEntity.getDescription();

        assertEquals("Description 1", description);
    }

    @Test
    @DisplayName("Should return the correct name of the TodoEntity")
    void getNameReturnsCorrectName() {
        String expectedName = "Todo 1";
        String actualName = todoEntity.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    @DisplayName("Should return the correct id of the TodoEntity")
    void getIdReturnsCorrectId() {
        Long expectedId = 1L;
        Long actualId = todoEntity.getId();
        assertEquals(expectedId, actualId);
    }
}