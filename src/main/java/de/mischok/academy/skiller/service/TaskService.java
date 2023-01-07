package de.mischok.academy.skiller.service;

import de.mischok.academy.skiller.domain.Task;
import de.mischok.academy.skiller.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }


}
