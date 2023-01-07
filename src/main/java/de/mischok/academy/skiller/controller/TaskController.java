package de.mischok.academy.skiller.controller;

import de.mischok.academy.skiller.domain.Task;
import de.mischok.academy.skiller.dto.TaskDto;
import de.mischok.academy.skiller.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasks() {

        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();
        List<Task> listOfTasks = taskService.getAllTasks();
        List<TaskDto> listOfTaskDto = new ArrayList<TaskDto>();
        if(!listOfTasks.isEmpty()) {
            for (Task task: listOfTasks) {
                listOfTaskDto.add(modelMapper.map(task, TaskDto.class));
            }
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", listOfTaskDto);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } else {
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Data not found");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto) {

        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();
        Task task = modelMapper.map(taskDto, Task.class);
        taskService.saveTask(task);
        jsonResponseMap.put("status", 1);
        jsonResponseMap.put("message", "Record is Saved");
        return new ResponseEntity<>(jsonResponseMap, HttpStatus.CREATED);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();

        try {

            Task task = taskService.getTaskById(id);
            TaskDto taskDto = modelMapper.map(task, TaskDto.class);
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", taskDto);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.FOUND);
        } catch (Exception ex) {

            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Data is not Found");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();
        try {
            Task task = taskService.getTaskById(id);
            task.setDescription(taskDto.getDescription());
            task.setTitle(taskDto.getTitle());
            task.setDone(taskDto.getDone());
            task.setDueDate(taskDto.getDueDate());
            taskService.saveTask(task);

            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("message", "Data updated");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } catch (Exception ex) {
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Data not found");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();

        try {

            Task task = taskService.getTaskById(id);
            taskService.deleteTask(task);
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("message", "Record is deleted");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);

        } catch(Exception ex) {

            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Data is not found");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/tasks/{id}/{title}")
    public ResponseEntity<?> updateTitle(@PathVariable Long id, @PathVariable String title) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();
        try {
            Task task = taskService.getTaskById(id);
            task.setTitle(title);
            TaskDto taskDto  = modelMapper.map(task, TaskDto.class);
            taskService.saveTask(task);
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", taskDto);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } catch (Exception ex) {
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Error occured by update");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/tasks/{id}/{description}")
    public ResponseEntity<?> updateDescription(@PathVariable Long id, @PathVariable String description) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();
        try {
            Task task = taskService.getTaskById(id);
            task.setDescription(description);
            TaskDto taskDto  = modelMapper.map(task, TaskDto.class);
            taskService.saveTask(task);
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", taskDto);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } catch (Exception ex) {
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Error occured by update");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/tasks/{id}/{done}")
    public ResponseEntity<?> updateDone(@PathVariable Long id, @PathVariable boolean done) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();
        try {
            Task task = taskService.getTaskById(id);
            task.setDone(done);
            TaskDto taskDto  = modelMapper.map(task, TaskDto.class);
            taskService.saveTask(task);
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", taskDto);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } catch (Exception ex) {
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Error occured by update");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/tasks/{id}/{dueDate}")
    public ResponseEntity<?> updateDueDate(@PathVariable Long id, @PathVariable String dueDate) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();
        try {
            Task task = taskService.getTaskById(id);
            LocalDateTime dateTime = LocalDateTime.parse(dueDate);
            task.setDueDate(dateTime);
            TaskDto taskDto  = modelMapper.map(task, TaskDto.class);
            taskService.saveTask(task);
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", taskDto);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } catch (Exception ex) {
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Error occured by update");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.BAD_REQUEST);
        }
    }
}
