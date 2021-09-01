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
import com.resume.init.domain.Occupation;
import com.resume.init.service.OccupationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class OccupationController {

    int resultValue = 0;
    private OccupationService occupationService = null;

    @Autowired
    public void setoccupationService(OccupationService occupationService) {
    this.occupationService = occupationService;
    }


    @PostMapping(value = "/OccupationList")
    public @ResponseBody String getOccupationList(
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

    List<Occupation> occupationList = occupationService.getAllOccupation(params);

    String value = "";

    if(!occupationList.isEmpty()){

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        // total = Total Page
        // record = Total Records
        // rows = list data
        // page = current page

        double total = (double) occupationList.get(0).getTotcnt() / rows;
        modelMap.put("total",(int) Math.ceil(total));
        modelMap.put("records", occupationList.get(0).getTotcnt());
        modelMap.put("rows", occupationList);
        modelMap.put("page", page);

        value = mapper.writeValueAsString(modelMap);
    }

    return value;
    }

    @RequestMapping (value="/OccupationEdit",method=RequestMethod.POST)
    public String OccupationDel (Occupation occupation,@RequestParam Map<String,Object> param) throws Exception{

        
        System.err.println(occupation.getRnum() + ":" +occupation.getOccupation_nm() + ":" +occupation.getName());


        String id = (String) param.get("id");

        occupation.setOccupation_nm(id);
        resultValue =  occupationService.deleteOccupation(occupation);

        return "setting";
    }

    @RequestMapping (value="/OccupationAdd",method=RequestMethod.POST)
    public String OccupationSave (Occupation occupation) throws Exception{


        resultValue =  occupationService.saveOccupation(occupation);

        return "setting";
    }

    @RequestMapping (value="/OccupationUpdate",method=RequestMethod.POST)
    public String OccupationEdit (Occupation occupation) throws Exception{

        System.err.println(occupation.getRnum() + ":" +occupation.getOccupation_nm() + ":" +occupation.getName());

            resultValue =  occupationService.updateOccupation(occupation);

        return "setting";
    }
}

