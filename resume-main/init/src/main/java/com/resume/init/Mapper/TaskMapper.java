package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Task;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TaskMapper {

    List<Task> getAllTask(HashMap<String, Object> hashMap);

    int deleteTask(Task task) throws Exception;

    int saveTask(Task task) throws Exception;

    int updateTask(Task task) throws Exception;

}

