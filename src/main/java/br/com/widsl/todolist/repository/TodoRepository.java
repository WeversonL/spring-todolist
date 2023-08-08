package br.com.widsl.todolist.repository;

import br.com.widsl.todolist.domain.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
