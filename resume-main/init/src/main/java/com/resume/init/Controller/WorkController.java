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
import com.resume.init.domain.Work;
import com.resume.init.service.WorkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorkController {
    
    
    int resultValue = 0;
    private WorkService workService = null;

    @Autowired
    public void setWorkService(WorkService workService) {
    this.workService = workService;
    }

    
    @PostMapping(value = "detail/{id}/WorkList")
    public @ResponseBody String getCareerList(
    HttpServletRequest request,HttpServletResponse response,
            @PathVariable int id,
            @RequestParam boolean _search,
            @RequestParam long     nd,
            @RequestParam int       rows,
            @RequestParam int       page,
            @RequestParam String   sidx,
            @RequestParam String   sord
    ) throws JsonGenerationException, JsonMappingException,IOException {

    HashMap<String,Object> params = new HashMap<String,Object>();
    int start =  ((page - 1) * rows ) + 1;
    int limit = (start + rows) -1;
    
    System.err.println("start = " + start + " : limit = " + limit);
    params.put("start", start );
    params.put("limit", limit);
    params.put("info_id", id);

    List<Work> WorkList = workService.getAllWork(params);
    
    String value = "";

    if(!WorkList.isEmpty()){
    ObjectMapper mapper = new ObjectMapper();

    Map<String, Object> modelMap = new HashMap<String, Object>();
    
    double total = (double) WorkList.get(0).getTotcnt() / rows;
    modelMap.put("total",(int) Math.ceil(total));
    modelMap.put("records", WorkList.get(0).getTotcnt());
    modelMap.put("rows", WorkList);
    modelMap.put("page", page);

    value = mapper.writeValueAsString(modelMap);
    }
    return value;
    }

    @RequestMapping (value="detail/WorkEdit",method=RequestMethod.POST)
    public String WorkEdit (Work work,@RequestParam Map<String,Object> param) throws Exception{


        String oper = (String) param.get("oper");
        String id = (String) param.get("id");

        
    if (oper.equals("edit")){
        String date = (String) param.get("date");
        String[] arraydate = date.split("~");
        work.setStart_date(arraydate[0].trim());
        work.setEnd_date(arraydate[1].trim());
        work.setId(id);
        resultValue =  workService.updateWork(work);
    } else if (oper.equals("del")){
        work.setId(id);
        System.err.println(work);
        resultValue =   workService.deleteWork(work);
    } else if (oper.equals("add")){
        String date = (String) param.get("date");
        String[] arraydate = date.split("~");
        work.setStart_date(arraydate[0].trim());
        work.setEnd_date(arraydate[1].trim());
        resultValue =   workService.saveWork(work);
    }
    
    return "index";
    }

    @RequestMapping (value="detail/WorkAdd",method=RequestMethod.POST)
    public String WorkSave (Work work) throws Exception{

        System.err.println(
            work.getCompany_name() + ":" +
            work.getDepartment()+ ":" +
            work.getEnd_date()+ ":" +
            work.getInfo_id()+ ":" +
            work.getSpot()+ ":" +
            work.getStart_date()+ ":" +
            work.getTask()+ ":" +
            work.getId());

            resultValue =   workService.saveWork(work);

        return "index";
    }

    @RequestMapping (value="detail/WorkUpdate",method=RequestMethod.POST)
    public String WorkEdit (Work work) throws Exception{

        

            resultValue =  workService.updateWork(work);

        return "index";
    }

//     @RequestMapping(value="detail/WorkDel", method = RequestMethod.POST, produces="application/json;charset=utf-8")
// 	public String WorkDelete (@RequestParam Map<String,Object> param) throws Exception {
// 		Work work = new Work();

        
//         String id = (String)param.get("id");
//         work.setWork_id(id);
        
// 		resultValue =   workService.deleteWork(work);

        
//         return "index";
// }
}
