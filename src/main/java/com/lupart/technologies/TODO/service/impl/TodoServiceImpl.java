package com.lupart.technologies.TODO.service.impl;

import com.lupart.technologies.TODO.dto.TodoDTO;
import com.lupart.technologies.TODO.entity.Todo;
import com.lupart.technologies.TODO.exceptions.TodoNotFoundException;
import com.lupart.technologies.TODO.repository.TodoRepository;
import com.lupart.technologies.TODO.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private  final TodoRepository todoRepository;

    @Override
    public Todo save(TodoDTO todoDTO) {
        var todo=new Todo();
        BeanUtils.copyProperties(todoDTO,todo);
        return todoRepository.save(todo);
    }

    @Override
    public Todo update(Integer i, TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(i).orElseThrow(
                () -> new TodoNotFoundException("Can't find todo of given id")
        );
        BeanUtils.copyProperties(todoDTO,todo);
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }
    @Override
    public Todo findById(Integer id) {
        return todoRepository.findById(id).orElseThrow(
                ()->new TodoNotFoundException("Can't find todo of given id")
        );
    }
    @Override
    public boolean deleteById(Integer id) {
        todoRepository.deleteById(id);
        return true;
    }
}
