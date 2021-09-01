package com.resume.init.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume.init.domain.Task;
import com.resume.init.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class TaskController {

    int resultValue = 0;
    private TaskService TaskService = null;

    @Autowired
    public void setTaskService(TaskService TaskService) {
    this.TaskService = TaskService;
    }

    
    @PostMapping(value = "/TaskList")
    public @ResponseBody String getTaskList(
        HttpServletRequest request,HttpServletResponse response,
            @RequestParam boolean _search,
            @RequestParam long     nd,
            @RequestParam int       rows,
            @RequestParam int       page,
            @RequestParam String   sidx,
            @RequestParam String   sord
    ) throws JsonGenerationException, JsonMappingException,IOException {

        // System.err.println("search = " + _search + " : nd = " + nd + " : rows = " + rows +
        //                             " : pages = " + page + " : sidx = " + sidx  + " : sord =" + sord);

    HashMap<String,Object> params = new HashMap<String,Object>();
    int start =  ((page - 1) * rows ) + 1;
    int limit = (start + rows) -1;

    System.err.println("start = " + start + " : limit = " + limit);
    params.put("start", start );
    params.put("limit", limit);

    List<Task> TaskList = TaskService.getAllTask(params);

    String value = "";

    if(!TaskList.isEmpty()){

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        // total = Total Page
        // record = Total Records
        // rows = list data
        // page = current page

        double total = (double) TaskList.get(0).getTotcnt() / rows;
        modelMap.put("total",(int) Math.ceil(total));
        modelMap.put("records", TaskList.get(0).getTotcnt());
        modelMap.put("rows", TaskList);
        modelMap.put("page", page);

        value = mapper.writeValueAsString(modelMap);
    }

    return value;
    }

    @RequestMapping (value="/TaskEdit",method=RequestMethod.POST)
    public String TaskSave (Task task,@RequestParam Map<String,Object> param) throws Exception{

        
        System.err.println(task.getRnum() + ":" +task.getTask_nm() + ":" +task.getName());


        String oper = (String) param.get("oper");
        String id = (String) param.get("id");

        
        if (oper.equals("edit")){
            resultValue =  TaskService.updateTask(task);
        } else if (oper.equals("del")){ 
            task.setTask_nm(id);
            resultValue =   TaskService.deleteTask(task);
        } else if (oper.equals("add")){
            resultValue =   TaskService.saveTask(task);
        }

            

        return "setting";
    }

    @RequestMapping (value="/TaskAdd",method=RequestMethod.POST)
    public String TaskSave (Task task) throws Exception{


        resultValue =   TaskService.saveTask(task);;

        return "setting";
    }

    @RequestMapping (value="/TaskUpdate",method=RequestMethod.POST)
    public String TaskEdit (Task task) throws Exception{

        System.err.println(task.getRnum() + ":" +task.getTask_nm() + ":" +task.getName());

            resultValue =  TaskService.updateTask(task);

        return "setting";
    }

}

