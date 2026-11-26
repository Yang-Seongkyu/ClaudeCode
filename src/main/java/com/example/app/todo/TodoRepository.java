package com.example.app.todo;

import org.springframework.data.jpa.repository.JpaRepository;

/** Spring Data generates the save/find/delete SQL for us. */
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
