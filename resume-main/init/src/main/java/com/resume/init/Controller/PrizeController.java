
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
import com.resume.init.domain.Prize;
import com.resume.init.service.PrizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrizeController {
    
    
    int resultValue = 0;
    private PrizeService PrizeService = null;

    @Autowired
    public void setPrizeService(PrizeService PrizeService) {
    this.PrizeService = PrizeService;
    }

    
    @PostMapping(value = "detail/{id}/PrizeList")
    public @ResponseBody String getPrizeList(
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

    List<Prize> PrizeList = PrizeService.getAllPrize(params);

    String value ="";

    if(!PrizeList.isEmpty()){
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        
        double total = (double) PrizeList.get(0).getTotcnt() / rows;
        modelMap.put("total",(int) Math.ceil(total));
        modelMap.put("records", PrizeList.get(0).getTotcnt());
        modelMap.put("rows", PrizeList);
        modelMap.put("page", page);

        value = mapper.writeValueAsString(modelMap);
    }
    return value;
    }

    @RequestMapping (value="detail/PrizeEdit",method=RequestMethod.POST)
    public String PrizeEdit (Prize prize,@RequestParam Map<String,Object> param) throws Exception{


        String oper = (String) param.get("oper");
        String id = (String) param.get("id");
        // System.err.println(param);
        // System.err.println(prize);
    if (oper.equals("edit")){
        resultValue =  PrizeService.updatePrize(prize);
    } else if (oper.equals("del")){
        prize.setId(id);
        resultValue =   PrizeService.deletePrize(prize);
    } else if (oper.equals("add")){
        resultValue =   PrizeService.savePrize(prize);
    }
    
    return "index";
    }

    @RequestMapping (value="detail/PrizeAdd",method=RequestMethod.POST)
    public String PrizeSave (Prize prize) throws Exception{

        // System.err.println(
        //     prize.getInfo_id() + ":" +
        //     prize.getAgency()+ ":" +
        //     prize.getEvidence()+ ":" +
        //     prize.getKind()+ ":" +
        //     prize.getReg_date()+ ":" +
        //     prize.getPrize_ID());

            resultValue =   PrizeService.savePrize(prize);

        return "index";
    }

    @RequestMapping (value="detail/PrizeUpdate",method=RequestMethod.POST)
    public String PrizeEdit (Prize prize) throws Exception{
        

            resultValue =  PrizeService.updatePrize(prize);

        return "index";
    }

//     @RequestMapping(value="detail/PrizeDel", method = RequestMethod.POST, produces="application/json;charset=utf-8")
// 	public String PrizeDelete (@RequestParam Map<String,Object> param) throws Exception {
// 		Prize prize = new Prize();

        
//         String id = (String)param.get("id");
//         prize.setPrize_ID(id);
        
// 		resultValue =   PrizeService.deletePrize(prize);

        
//         return "index";
// }
}
