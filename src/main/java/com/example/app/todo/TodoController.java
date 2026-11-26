package com.example.app.todo;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API for todos.
 *
 * <pre>
 * GET    /todos        list all
 * GET    /todos/{id}   get one
 * POST   /todos        create   {"title": "Buy milk"}
 * PATCH  /todos/{id}   update   {"done": true} and/or {"title": "..."}
 * DELETE /todos/{id}   delete
 * </pre>
 */
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Todo get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@Valid @RequestBody TodoRequest request) {
        return service.create(request);
    }

    @PatchMapping("/{id}")
    public Todo update(@PathVariable Long id, @Valid @RequestBody TodoUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
