package com.example.app.todo;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/** Business logic for todos; the controller calls this, and this calls the repository. */
@Service
@Transactional
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Todo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Todo " + id + " not found"));
    }

    public Todo create(TodoRequest request) {
        return repository.save(new Todo(request.title()));
    }

    public Todo update(Long id, TodoUpdateRequest request) {
        Todo todo = findById(id);
        if (request.title() != null) {
            todo.setTitle(request.title());
        }
        if (request.done() != null) {
            todo.setDone(request.done());
        }
        return todo; // Saved automatically when the transaction ends
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
