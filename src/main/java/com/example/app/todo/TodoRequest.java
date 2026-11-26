package com.example.app.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Request body for creating a todo: {"title": "..."} */
public record TodoRequest(
        @NotBlank @Size(max = 200) String title) {
}
