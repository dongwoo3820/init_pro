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
import com.resume.init.domain.Education;
import com.resume.init.service.EducationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EducationController {
    
    
    int resultValue = 0;
    private EducationService educationService = null;

    @Autowired
    public void setEducationService(EducationService educationService) {
    this.educationService = educationService;
    }

    
    @PostMapping(value = "detail/{id}/EducationList")
    public @ResponseBody String getEducationList(
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

    List<Education> EducationList = educationService.getAllEducation(params);
    
    String value = "";

    if(!EducationList.isEmpty()){
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        
        double total = (double) EducationList.get(0).getTotcnt() / rows;
        modelMap.put("total",(int) Math.ceil(total));
        modelMap.put("records", EducationList.get(0).getTotcnt());
        modelMap.put("rows", EducationList);
        modelMap.put("page", page);

        value = mapper.writeValueAsString(modelMap);
    }
    
    return value;
    }

    @RequestMapping (value="detail/EducationEdit",method=RequestMethod.POST)
    public String EducationEdit (Education education,@RequestParam Map<String,Object> param) throws Exception{


        String oper = (String) param.get("oper");
        String id = (String) param.get("id");
        
    if (oper.equals("edit")){
        education.setId(id);
        String date = (String) param.get("date");
        String[] arraydate = date.split("~");
        education.setStart_date(arraydate[0].trim());
        education.setEnd_date(arraydate[1].trim());
        resultValue =  educationService.updateEducation(education);
    } else if (oper.equals("del")){
        education.setId(id);
        resultValue =   educationService.deleteEducation(education);
    } else if (oper.equals("add")){
        String date = (String) param.get("date");
        String[] arraydate = date.split("~");
        education.setStart_date(arraydate[0].trim());
        education.setEnd_date(arraydate[1].trim());
        resultValue =   educationService.saveEducation(education);
    }
    
    return "index";
    }

    @RequestMapping (value="detail/EducationAdd",method=RequestMethod.POST)
    public String EducationSave (Education education) throws Exception{

        System.err.println(
            education.getName() + ":" +
            education.getDepartment()+ ":" +
            education.getEnd_date()+ ":" +
            education.getInfo_id()+ ":" +
            education.getDegree()+ ":" +
            education.getStart_date()+ ":" +
            education.getId());

            resultValue =   educationService.saveEducation(education);

        return "index";
    }

    @RequestMapping (value="detail/EducationUpdate",method=RequestMethod.POST)
    public String EducationEdit (Education education) throws Exception{
        System.err.println(education);
            resultValue =  educationService.updateEducation(education);

        return "index";
    }

//     @RequestMapping(value="detail/EducationDel", method = RequestMethod.POST, produces="application/json;charset=utf-8")
// 	public String EducationDelete (@RequestParam Map<String,Object> param) throws Exception {
// 		Education education = new Education();

        
//         String id = (String)param.get("id");
//         education.setEdu_ID(id);
        
// 		resultValue =   educationService.deleteEducation(education);

        
//         return "index";
// }
}
