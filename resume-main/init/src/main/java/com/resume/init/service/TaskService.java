package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.TaskMapper;
import com.resume.init.domain.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    
    @Autowired
    public TaskMapper taskMapper;

    public List<Task> getAllTask(HashMap<String, Object> hashMap){
        return taskMapper.getAllTask(hashMap);
    }

    public int updateTask(Task task)  throws Exception{
        return taskMapper.updateTask(task);
    }

    public int deleteTask(Task task) throws Exception {
        return taskMapper.deleteTask(task);
    }

    public int saveTask(Task task) throws Exception {
        return taskMapper.saveTask(task);
    }
}
