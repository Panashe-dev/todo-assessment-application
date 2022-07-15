package com.lupart.technologies.TODO.controller;

import com.lupart.technologies.TODO.dto.TodoDTO;
import com.lupart.technologies.TODO.entity.Todo;
import com.lupart.technologies.TODO.service.TodoService;
import com.lupart.technologies.TODO.utils.Response;
import com.lupart.technologies.TODO.utils.ResponseBuild;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "todo")
@Api(tags = {"Todo Controller"})
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final ResponseBuild<Todo> todoResponseBuild;
    private final ResponseBuild<Boolean>booleanResponseBuild;

    @PostMapping
    @ApiOperation(notes = "Process  creating a todo",
            value = "Process creating a todo ", nickname = "TO-DO")
    public ResponseEntity<Response> newTodo(@RequestBody @Valid TodoDTO todoDTO) {
        log.info("Create Todo Request");
        return new ResponseEntity<>(todoResponseBuild.successResponse
                .apply(todoService.save(todoDTO),null), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    @ApiOperation(notes = "Update an Todo",
            value = "Update todo type", nickname = "updateTodo")
    public ResponseEntity<Response> alterTodo(@PathVariable Integer id,@RequestBody @Valid TodoDTO todoDTO) {
        log.info("Update Todo Request");
        return new ResponseEntity<>(todoResponseBuild.successResponse
                .apply(todoService.update(id,todoDTO),null),HttpStatus.CREATED);

    }
    @GetMapping("/all")
    @ApiOperation(notes = "Get All Todos",
            value = "Get all todo", nickname = "Todo")
    public ResponseEntity<Response> getAllTodo() {
        return new ResponseEntity<>(todoResponseBuild.successResponse
                .apply(null,todoService.findAll()),HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    @ApiOperation(notes = "Get Todo by id",
            value = "Get Todo", nickname = "Todo")
    public ResponseEntity<Response> getTodoById(@PathVariable Integer id) {
        return new ResponseEntity<>(todoResponseBuild.successResponse
                .apply(todoService.findById(id),null),HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ApiOperation(notes = "Delete Todo by id",
            value = "Delete Todo", nickname = "Todo")
    public ResponseEntity<Response> deleteTodoById(@PathVariable Integer id) {
        return new ResponseEntity<>(booleanResponseBuild.successResponse
                .apply(todoService.deleteById(id),null),HttpStatus.CREATED);
    }

}
