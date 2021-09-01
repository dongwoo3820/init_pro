package com.resume.init.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume.init.domain.FileAttach;
import com.resume.init.domain.ImageAttach;
import com.resume.init.domain.UserExt;
import com.resume.init.domain.WorkArea;
import com.resume.init.service.DetailService;
import com.resume.init.service.UserService;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class UserController {

    int resultValue = 0;

    private UserService userService = null;

    @Autowired
    public void setUserService(UserService userService) {
    this.userService = userService;
    }

    private DetailService detailService = null;

    @Autowired
    public void setDetailService(DetailService detailService) {
    this.detailService = detailService;
    }

    
    @PostMapping(value = "/userList")
    public @ResponseBody String getUserList(
        HttpServletRequest request,HttpServletResponse response,
        @RequestParam HashMap<String,Object> param 
    ) throws JsonGenerationException, JsonMappingException,IOException {
        int            rows = Integer.parseInt(String.valueOf(param.get("rows")));
        int            page = Integer.parseInt(String.valueOf(param.get("page")));
        
        
    int start =  ((page - 1) * rows ) + 1;
    int limit = (start + rows) -1;


    //System.err.println("start = " + start + " : limit = " + limit);
    param.put("start", start);
    param.put("limit", limit);
    System.out.println(param);
    

    List<UserExt> userExtList = userService.getAllUser(param);
    String value = "";

    if(!userExtList.isEmpty()){
    

    ObjectMapper mapper = new ObjectMapper();

    Map<String, Object> modelMap = new HashMap<String, Object>();
    // total = Total Page
    // record = Total Records
    // rows = list data
    // page = current page
    
    double total = (double) userExtList.get(0).getTotcnt() / rows;
    modelMap.put("total",(int) Math.ceil(total));
    modelMap.put("records", userExtList.get(0).getTotcnt());
    modelMap.put("rows", userExtList);
    modelMap.put("page", page);

    value = mapper.writeValueAsString(modelMap);
    }

    return value;
    }

    @RequestMapping (value="/userEdit",method=RequestMethod.POST)
    public String userEdit (UserExt user,@RequestParam Map<String,Object> param) throws Exception{


        String oper = (String) param.get("oper");
        String id = (String) param.get("id");

    if (oper.equals("edit")){
        resultValue =  userService.updateUser(user);
    } else if (oper.equals("del")){
        user.setId(id);
        resultValue =   userService.deleteUser(user);
    } else if (oper.equals("add")){
        resultValue =   userService.saveUser(user);
    }
    
    return "index";
    }

    @RequestMapping (value="/userAdd",method=RequestMethod.POST)
    public String userSave (@ModelAttribute UserExt user) throws Exception{

        userService.saveUser(user);

        userService.workAreaInsert(userService.selectInfoID().getId());
        
        List<WorkArea> workarea =  detailService.workAreaSelect(Integer.parseInt(userService.selectInfoID().getId()));
        System.err.println(workarea);
        String[] areaId = user.getArea().split(",");

        if(user.getArea() != null){
            for(int i = 0; i < workarea.size(); i++){
                for(int k = 0; k < areaId.length; k++){
                    if(!workarea.get(i).getArea_id().equals(areaId[k])){
                        workarea.get(i).setArea_yn("N");
                    }
                    if(workarea.get(i).getArea_id().equals(areaId[k])){
                        workarea.get(i).setArea_yn("Y");
                        k +=areaId.length;
                    }
                }//for
                userService.workAreaUpdate(workarea.get(i));
            }//for
        }//if

        return "index";
    }

    @RequestMapping (value="/userUpdate",method=RequestMethod.POST)
    public String userUpdate (@ModelAttribute UserExt user, @RequestPart MultipartFile attachFile, @RequestPart MultipartFile attachImg, HttpServletRequest request) throws IllegalStateException, IOException, Exception{
        
        List<WorkArea> workarea =  detailService.workAreaSelect(Integer.parseInt(user.getInfo_id()));
        System.err.println(workarea);
        String[] areaId = user.getArea().split(",");
        
        if(attachFile.isEmpty() && attachImg.isEmpty()){ //둘 다 없을 때
            if(user.getArea() != null){
                for(int i = 0; i < workarea.size(); i++){
                    for(int k = 0; k < areaId.length; k++){
                        if(!workarea.get(i).getArea_id().equals(areaId[k])){
                            workarea.get(i).setArea_yn("N");
                        }
                        if(workarea.get(i).getArea_id().equals(areaId[k])){
                            workarea.get(i).setArea_yn("Y");
                            k +=areaId.length;
                        }
                    }//for
                    userService.workAreaUpdate(workarea.get(i));
                }//for
            }//if
            workarea =  detailService.workAreaSelect(Integer.parseInt(user.getInfo_id()));
            System.err.println(workarea);
            resultValue =  userService.updateUser(user);
        } //if
        else if(!attachFile.isEmpty() && attachImg.isEmpty()){  // 파일만 있을 때

            if(user.getArea() != null){
                for(int i = 0; i < workarea.size(); i++){
                    for(int k = 0; k < areaId.length; k++){
                        if(!workarea.get(i).getArea_id().equals(areaId[k])){
                            workarea.get(i).setArea_yn("N");
                        }
                        if(workarea.get(i).getArea_id().equals(areaId[k])){
                            workarea.get(i).setArea_yn("Y");
                            k +=areaId.length;
                        }
                    }//for
                    userService.workAreaUpdate(workarea.get(i));
                }//for
            }//if
            
            String fileName = attachFile.getOriginalFilename();


            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            File destinationFile;
            String destinationFileName;

            
            //String fileurl = "C:\\Users\\USER\\Desktop\\work-space\\resume\\init\\src\\main\\resources\\static\\uploads\\";
            String fileurl = "/home/init/gitrepo/resume/init/src/main/resources/static/uploads/";
            
            do{
                destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
                destinationFile = new File(fileurl + destinationFileName);
            } while(destinationFile.exists());

            destinationFile.getParentFile().mkdirs();
            attachFile.transferTo(destinationFile);

            userService.updateUser(user);

            FileAttach file = new FileAttach();
            file.setInfo_id(user.getInfo_id());
            file.setFilename(destinationFileName);
            file.setFileoriginname(fileName);
            file.setFileurl(fileurl);

            
            userService.fileInsert(file);
        }
        else if(attachFile.isEmpty() && !attachImg.isEmpty()){   //이미지만 있을 떄

            if(user.getArea() != null){
                for(int i = 0; i < workarea.size(); i++){
                    for(int k = 0; k < areaId.length; k++){
                        if(!workarea.get(i).getArea_id().equals(areaId[k])){
                            workarea.get(i).setArea_yn("N");
                        }
                        if(workarea.get(i).getArea_id().equals(areaId[k])){
                            workarea.get(i).setArea_yn("Y");
                            k +=areaId.length;
                        }
                    }//for
                    userService.workAreaUpdate(workarea.get(i));
                }//for
            }//if
            
            String imgName = attachImg.getOriginalFilename();


            String imgNameExtension = FilenameUtils.getExtension(imgName).toLowerCase();
            File destinationImg;
            String destinationImgName;

            //String imgurl = "C:\\Users\\USER\\Desktop\\work-space\\resume\\init\\src\\main\\resources\\static\\img\\";
            String imgurl = "/home/init/gitrepo/resume/init/src/main/resources/static/img/";
            
            do{
                destinationImgName = RandomStringUtils.randomAlphanumeric(32) + "." + imgNameExtension;
                destinationImg = new File(imgurl + destinationImgName);
            } while(destinationImg.exists());

            destinationImg.getParentFile().mkdirs();
            attachImg.transferTo(destinationImg);

            userService.updateUser(user);

            System.out.println(request.getParameter("photo"));

            ImageAttach img = new ImageAttach();
            img.setInfo_id(user.getInfo_id());
            img.setImgname(destinationImgName);
            img.setImgoriginname(imgName);
            img.setImgurl(imgurl);
            if(request.getParameter("photo") == null){
                userService.imgInsert(img);
            }else{
                ImageAttach beforeAttach = detailService.imgSelect(Integer.parseInt(user.getInfo_id()));
                Path filePath = FileSystems.getDefault().getPath(beforeAttach.getImgurl(), beforeAttach.getImgname());
                Files.delete(filePath);
                userService.imgDelete(Integer.parseInt(user.getInfo_id()));
                userService.imgInsert(img);
            }
        }
        else if(!attachFile.isEmpty() && !attachImg.isEmpty()){  // 둘 다 있을 때

            if(user.getArea() != null){
                for(int i = 0; i < workarea.size(); i++){
                    for(int k = 0; k < areaId.length; k++){
                        if(!workarea.get(i).getArea_id().equals(areaId[k])){
                            workarea.get(i).setArea_yn("N");
                        }
                        if(workarea.get(i).getArea_id().equals(areaId[k])){
                            workarea.get(i).setArea_yn("Y");
                            k +=areaId.length;
                        }
                    }//for
                    userService.workAreaUpdate(workarea.get(i));
                }//for
            }//if
            
            //파일
            String fileName = attachFile.getOriginalFilename();
            //이미지
            String imgName = attachImg.getOriginalFilename();

            //파일
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            File destinationFile;
            String destinationFileName;
            //이미지
            String imgNameExtension = FilenameUtils.getExtension(imgName).toLowerCase();
            File destinationImg;
            String destinationImgName;

            //String fileurl = "C:\\Users\\USER\\Desktop\\work-space\\resume\\init\\src\\main\\resources\\static\\uploads\\";
            String fileurl = "/home/init/gitrepo/resume/init/src/main/resources/static/uploads/";

            //String imgurl = "C:\\Users\\USER\\Desktop\\work-space\\resume\\init\\src\\main\\resources\\static\\images\\";
            String imgurl = "/home/init/gitrepo/resume/init/src/main/resources/static/img/";
            
            do{
                destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
                destinationFile = new File(fileurl + destinationFileName);
            } while(destinationFile.exists());

            do{
                destinationImgName = RandomStringUtils.randomAlphanumeric(32) + "." + imgNameExtension;
                destinationImg = new File(imgurl + destinationImgName);
            } while(destinationImg.exists());

            destinationFile.getParentFile().mkdirs();
            attachFile.transferTo(destinationFile);

            destinationImg.getParentFile().mkdirs();
            attachImg.transferTo(destinationImg);

            userService.updateUser(user);

            FileAttach file = new FileAttach();
            file.setInfo_id(user.getInfo_id());
            file.setFilename(destinationFileName);
            file.setFileoriginname(fileName);
            file.setFileurl(fileurl);
            

            ImageAttach img = new ImageAttach();
            img.setInfo_id(user.getInfo_id());
            img.setImgname(destinationImgName);
            img.setImgoriginname(imgName);
            img.setImgurl(imgurl);

            if(request.getParameter("photo") == null){
                userService.imgInsert(img);
                userService.fileInsert(file);
            }else{
                ImageAttach beforeAttach = detailService.imgSelect(Integer.parseInt(user.getInfo_id()));
                Path filePath = FileSystems.getDefault().getPath(beforeAttach.getImgurl(), beforeAttach.getImgname());
                Files.delete(filePath);
                userService.imgDelete(Integer.parseInt(user.getInfo_id()));
                userService.imgInsert(img);
                userService.fileInsert(file);
            }

        }

        return "index";
    }

    @RequestMapping(value = "/fileDelete/{id}")
    public String fileDelete(@PathVariable("id") int id) throws Exception{

        FileAttach fileAttach = detailService.fileSelect(id);
        Path filePath = FileSystems.getDefault().getPath(fileAttach.getFileurl(), fileAttach.getFilename());
        try {
            Files.delete(filePath);
            userService.fileDelete(id);
        } catch (IOException | SecurityException e) {
            return "index";
        }
        return "index";
    }

    
    

//     @RequestMapping(value="/userDel", method = RequestMethod.POST, produces="application/json;charset=utf-8")
// 	public String userDelete (@RequestParam Map<String,Object> param) throws Exception {
// 		UserExt user = new UserExt();

//         String id = (String)param.get("id");
//         user.setInfo_id(id);
//         System.out.println(user);

// 		resultValue =   userService.deleteUser(user);
        
//         return "index";
// }
}
