package br.com.widsl.todolist.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

import static br.com.widsl.todolist.constants.ValidationMessages.FIELD_IS_REQUIRED;

public class TodoDTO {

    private Long id;

    @NotBlank(message = FIELD_IS_REQUIRED)
    private String name;

    @NotBlank(message = FIELD_IS_REQUIRED)
    private String description;

    @NotNull(message = FIELD_IS_REQUIRED)
    private Integer priority;

    private boolean realized;

    public TodoDTO(Long id, String name, String description, Integer priority, boolean realized) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.realized = realized;
    }

    public TodoDTO() {
    }

    public static TodoDTOBuilder builder() {
        return new TodoDTOBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public boolean getRealized() {
        return this.realized;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setRealized(boolean realized) {
        this.realized = realized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TodoDTO todoDTO = (TodoDTO) o;
        return Objects.equals(id, todoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TodoDTO;
    }

    public String toString() {
        return "TodoDTO(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription()
                + ", priority=" + this.getPriority() + ", realized=" + this.getRealized() + ")";
    }

    public static class TodoDTOBuilder {
        private Long id;
        private String name;
        private String description;
        private Integer priority;
        private boolean realized;

        TodoDTOBuilder() {
        }

        public TodoDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TodoDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TodoDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TodoDTOBuilder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public TodoDTOBuilder realized(boolean realized) {
            this.realized = realized;
            return this;
        }

        public TodoDTO build() {
            return new TodoDTO(this.id, this.name, this.description, this.priority, this.realized);
        }

        public String toString() {
            return "TodoDTO.TodoDTOBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description
                    + ", priority=" + this.priority + ", realized=" + this.realized + ")";
        }
    }
}
