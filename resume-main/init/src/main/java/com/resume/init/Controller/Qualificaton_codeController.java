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
import com.resume.init.domain.Qualification_code;
import com.resume.init.service.Qualification_codeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class Qualificaton_codeController {

    int resultValue = 0;
    private Qualification_codeService qualification_codeService = null;

    @Autowired
    public void setQualification_codeService(Qualification_codeService qualification_codeService) {
    this.qualification_codeService = qualification_codeService;
    }

    
    @PostMapping(value = "/qualificatonCodeList")
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

    List<Qualification_code> Qualification_codeList = qualification_codeService.getAllQualification_code(params);

    String value = "";

    if(!Qualification_codeList.isEmpty()){

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        // total = Total Page
        // record = Total Records
        // rows = list data
        // page = current page

        double total = (double) Qualification_codeList.get(0).getTotcnt() / rows;
        modelMap.put("total",(int) Math.ceil(total));
        modelMap.put("records", Qualification_codeList.get(0).getTotcnt());
        modelMap.put("rows", Qualification_codeList);
        modelMap.put("page", page);

        value = mapper.writeValueAsString(modelMap);
    }

    return value;
    }

    @RequestMapping (value="/qualificatonCodeEdit",method=RequestMethod.POST)
    public String TaskSave (Qualification_code qualification_code,@RequestParam Map<String,Object> param) throws Exception{

        
        System.err.println(qualification_code.getRnum() + ":" +qualification_code.getQualifi_mc() + ":" +qualification_code.getQualifi_name());


        String oper = (String) param.get("oper");
        String id = (String) param.get("id");

        
        if (oper.equals("edit")){
            resultValue =  qualification_codeService.updateQualification_code(qualification_code);
        } else if (oper.equals("del")){
            qualification_code.setQualifi_mc(id);
            resultValue =   qualification_codeService.deleteQualification_code(qualification_code);
        } else if (oper.equals("add")){
            resultValue =   qualification_codeService.saveQualification_code(qualification_code);
        }

            

        return "setting";
    }

    @RequestMapping (value="/qualificatonCodeAdd",method=RequestMethod.POST)
    public String CareerSave (Qualification_code qualification_code) throws Exception{


        resultValue =   qualification_codeService.saveQualification_code(qualification_code);;

        return "setting";
    }

    @RequestMapping (value="/qualificatonCodeUpdate",method=RequestMethod.POST)
    public String TaskEdit (Qualification_code qualification_code) throws Exception{

        System.err.println(qualification_code.getRnum() + ":" +qualification_code.getQualifi_mc() + ":" +qualification_code.getQualifi_name());

            resultValue =  qualification_codeService.updateQualification_code(qualification_code);

        return "setting";
    }

//     @RequestMapping(value="/TaskDel", method = RequestMethod.POST, produces="application/json;charset=utf-8")
// 	public String TaskDelete (@RequestParam Map<String,Object> param) throws Exception {
// 		Task Task = new Task();

        
//         String id = (String)param.get("id");
//         Task.setTaskmc(id);
        
// 		resultValue =   TaskService.deleteTask(Task);

        
//         return "setting";
// }
}
