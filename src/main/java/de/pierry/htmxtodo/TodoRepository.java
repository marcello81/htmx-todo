package de.pierry.htmxtodo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
    public List<Todo> findAllByCompleted(boolean completed);

}
