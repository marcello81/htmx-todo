package de.pierry.htmxtodo;

import org.springframework.data.annotation.Id;

public record Todo(@Id Integer id, String title, boolean completed) {
}
