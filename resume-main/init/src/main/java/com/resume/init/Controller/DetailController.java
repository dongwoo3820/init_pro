package com.resume.init.Controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.resume.init.domain.Area;
import com.resume.init.domain.Client;
import com.resume.init.domain.FileAttach;
import com.resume.init.domain.ImageAttach;
import com.resume.init.domain.Institute;
import com.resume.init.domain.Occupation;
import com.resume.init.domain.Personal_info;
import com.resume.init.domain.Qualification_code;
import com.resume.init.domain.Spot;
import com.resume.init.domain.Task;
import com.resume.init.domain.WorkArea;
import com.resume.init.service.DetailService;
import com.resume.init.service.PdfService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class DetailController {

    int resultValue = 0;
    private DetailService detailService = null;
    private PdfService pdfService = null;

    @Autowired
    public void setDetailService(DetailService detailService) {
    this.detailService = detailService;
    }

    @Autowired
    public void setPdfService(PdfService pdfService) {
    this.pdfService = pdfService;
    }
// 상세화면 조회
    @RequestMapping (value="detail/{id}",method={RequestMethod.GET,RequestMethod.POST})
    public String getDetail (@PathVariable("id") int id, Model model) throws Exception{

        List<Personal_info> value = detailService.getDetail(id);
        List<FileAttach> fileAttachs = detailService.fileDetail(id);
        ImageAttach imgAttachs = detailService.imgSelect(id);
        List<Task> task = detailService.taskSelect();
        List<Client> client = detailService.clientSelect();
        List<Institute> institute = detailService.instituteSelect();
        List<Spot> spot = detailService.spotSelect();
        List<Area> area = detailService.areaSelect();
        List<WorkArea> workarea = detailService.workAreaYNSelect(id);
        List<Qualification_code> qualification_codes = detailService.qualification_codeSelect();
        List<Occupation> occupation = detailService.occupationSelect();

        
        Personal_info personal_info = new Personal_info();
        personal_info.setId(Integer.toString(id));
        personal_info.setInfo_id(Integer.toString(id));
        personal_info.setName(value.get(0).getName());
        personal_info.setBirth(value.get(0).getBirth());
        personal_info.setPhone(value.get(0).getPhone());
        personal_info.setEmail(value.get(0).getEmail());
        personal_info.setArea(value.get(0).getArea());
        personal_info.setCareer_age(value.get(0).getCareer_age());
        personal_info.setQualifi(value.get(0).getQualifi());
        
        model.addAttribute("area", area);
        model.addAttribute("workarea", workarea);
        model.addAttribute("detailset", personal_info);
        model.addAttribute("file", fileAttachs);
        model.addAttribute("task", task);
        model.addAttribute("occupation", occupation);
        model.addAttribute("client", client);
        model.addAttribute("institute", institute);
        model.addAttribute("spot", spot);
        model.addAttribute("img", imgAttachs);
        model.addAttribute("qualifi", qualification_codes);
        return "detail";
    }
// 파일 다운로드
    @RequestMapping(value="/fileDown/{file_id}")
    private void fileDown(@PathVariable("file_id") int file_id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, Exception {

            
        // if (file_id == null) throw new RuntimeException("올바르지 않은 접근입니다.");

        FileAttach fileAttach = detailService.fileSelect(file_id);
        //|| "Y".equals(fileAttach.getDeleteYn())
        if (fileAttach == null ) {
            throw new RuntimeException("파일 정보를 찾을 수 없습니다.");
        }

        String uploadPath =  fileAttach.getFileurl();

        String filename = fileAttach.getFileoriginname();
        File file = new File(uploadPath, fileAttach.getFilename());

        try {
            byte[] data = FileUtils.readFileToByteArray(file);
            response.setContentType("application/octet-stream");
            response.setContentLength(data.length);
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; fileName=\"" + java.net.URLEncoder.encode(filename, "UTF-8") + "\";");

            response.getOutputStream().write(data);
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드에 실패하였습니다.");

        } catch (Exception e) {
            throw new RuntimeException("시스템에 문제가 발생하였습니다.");
        }
    }

// pdf다운로드
    @GetMapping("/download-pdf/{id}")
    public void downloadPDFResource(@PathVariable int id, HttpServletResponse response) throws Exception {
        try {
            Path file = Paths.get(pdfService.generatePdf(id).getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
