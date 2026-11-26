package com.example.app.todo;

import jakarta.validation.constraints.Size;

/** Request body for updating a todo. Fields left out (null) stay unchanged. */
public record TodoUpdateRequest(
        @Size(min = 1, max = 200) String title,
        Boolean done) {
}
