package de.pierry.htmxtodo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Runner implements CommandLineRunner {

    private TodoRepository repo;

    public Runner(TodoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        long entryCount = repo.count();
        if (entryCount > 0) {
            return;
        }

        final List<Todo> todos = List.of(
                new Todo(null, "mein erstes todo", false),
                new Todo(null, "mein zweites todo", false),
                new Todo(null, "take a walk", true),
                new Todo(null, "learn htmx", false));

        repo.saveAll(todos);
    }
}
