package com.resume.init.service;

import java.util.List;

import com.resume.init.Mapper.DetailMapper;
import com.resume.init.domain.Area;
import com.resume.init.domain.Career;
import com.resume.init.domain.Client;
import com.resume.init.domain.EduMatter;
import com.resume.init.domain.Education;
import com.resume.init.domain.FileAttach;
import com.resume.init.domain.ImageAttach;
import com.resume.init.domain.Institute;
import com.resume.init.domain.Occupation;
import com.resume.init.domain.Personal_info;
import com.resume.init.domain.Prize;
import com.resume.init.domain.Qualification_code;
import com.resume.init.domain.Qualifications;
import com.resume.init.domain.Spot;
import com.resume.init.domain.Task;
import com.resume.init.domain.Work;
import com.resume.init.domain.WorkArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    
    @Autowired
    public DetailMapper detailMapper;

    public List<Personal_info> getDetail(int id){
        return detailMapper.getDetail(id);
    }

    public List<FileAttach> fileDetail(int info_id) throws Exception{
        return detailMapper.fileDetail(info_id);
    }

    public ImageAttach imgSelect(int info_id) throws Exception{
        return detailMapper.imgSelect(info_id);
    }

    public FileAttach fileSelect(int id) throws Exception{
        return detailMapper.fileSelect(id);
    }

    public List<Area> areaSelect() throws Exception{
        return detailMapper.areaSelect();
    }

    public List<WorkArea> workAreaYNSelect(int id) throws Exception{
        return detailMapper.workAreaYNSelect(id);
    }

    public List<WorkArea> workAreaSelect(int id) throws Exception{
        return detailMapper.workAreaSelect(id);
    }

    public List<Task> taskSelect() throws Exception{
        return detailMapper.taskSelect();
    }

    public List<Occupation> occupationSelect() throws Exception{
        return detailMapper.occupationSelect();
    }

    public List<Client> clientSelect() throws Exception{
        return detailMapper.clientSelect();
    }

    public List<Institute> instituteSelect() throws Exception{
        return detailMapper.instituteSelect();
    }

    public List<Spot> spotSelect() throws Exception{
        return detailMapper.spotSelect();
    }

    public List<Work> workSelect(int id) throws Exception{
        return detailMapper.workSelect(id);
    }

    public List<Career> careerSelect(int id) throws Exception{
        return detailMapper.careerSelect(id);
    }

    public List<Qualifications> qualifiSelect(int id) throws Exception{
        return detailMapper.qualifiSelect(id);
    }

    public List<Education> educationSelect(int id) throws Exception{
        return detailMapper.educationSelect(id);
    }

    public List<EduMatter> eduMatterSelect(int id) throws Exception{
        return detailMapper.eduMatterSelect(id);
    }

    public List<Prize> prizeSelect(int id) throws Exception{
        return detailMapper.prizeSelect(id);
    }
    public List<Qualification_code> qualification_codeSelect() throws Exception{
        return detailMapper.qualification_codeSelect();
    }

}
