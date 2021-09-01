package com.resume.init.Controller;

import java.util.List;

import com.resume.init.domain.Area;
import com.resume.init.domain.Client;
import com.resume.init.domain.Occupation;
import com.resume.init.domain.Qualification_code;
import com.resume.init.domain.Task;
import com.resume.init.service.DetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class indexController {


    private DetailService detailService = null;

    @Autowired
    public void setDetailService(DetailService detailService) {
    this.detailService = detailService;
    }


    @GetMapping("/")
    public String index(Model model) throws Exception{

        List<Area> area = detailService.areaSelect();
        List<Client> client = detailService.clientSelect(); 
        List<Task> task = detailService.taskSelect();
        List<Qualification_code> qualification_codeSelect = detailService.qualification_codeSelect();
        List<Occupation> occupation = detailService.occupationSelect();

        model.addAttribute("occupation", occupation);
        model.addAttribute("area", area);
        model.addAttribute("client", client);
        model.addAttribute("task", task);
        model.addAttribute("qualifi", qualification_codeSelect);

        
        return "index";
    }

    @GetMapping("/setting")
    public String setting() {

        return "setting";
    }

    @RequestMapping("/login_page")
    public String login() {
        return "login_page";
    }
    
}