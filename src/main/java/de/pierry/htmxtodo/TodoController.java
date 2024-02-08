package de.pierry.htmxtodo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("todos")
public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("todos", repository.findAllByCompleted(false));
        model.addAttribute("todosCompleted", repository.findAllByCompleted(true));
        return "index";
    }

    @PutMapping("complete/{id}")
    public String complete(@PathVariable("id") Integer id, Model model) {
        Todo todo = repository.findById(id).orElseThrow();
        Todo newTodo = new Todo(todo.id(), todo.title(), true);
        repository.save(newTodo);
        model.addAttribute("todo", newTodo);
        return "todo-complete-response";
    }

    @PutMapping("uncomplete/{id}")
    public String uncomplete(@PathVariable("id") Integer id, Model model) {
        Todo todo = repository.findById(id).orElseThrow();
        Todo newTodo = new Todo(todo.id(), todo.title(), false);
        repository.save(newTodo);
        model.addAttribute("todo", newTodo);
        return "todo-uncomplete-response";
    }

    @PostMapping
    public String add(@RequestParam("title") String title, Model model) {
        Todo todo = repository.save(new Todo(null, title, false));
        model.addAttribute("todo", todo);
        return "index :: todo-item";
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Integer id, Model model) {
        repository.deleteById(id);
        return "";
    }
}