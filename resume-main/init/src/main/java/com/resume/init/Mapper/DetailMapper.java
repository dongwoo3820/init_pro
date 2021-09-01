package com.resume.init.Mapper;

import java.util.List;

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

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DetailMapper {
    
    List<Personal_info> getDetail(int id); 
    
    List<FileAttach> fileDetail(int info_id) throws Exception;

    ImageAttach imgSelect(int info_id) throws Exception;

    FileAttach fileSelect(int id) throws Exception;

    List<Area> areaSelect() throws Exception;

    List<WorkArea> workAreaYNSelect(int id) throws Exception;

    List<WorkArea> workAreaSelect(int id) throws Exception;

    List<Task> taskSelect() throws Exception;

    List<Occupation> occupationSelect() throws Exception;

    List<Client> clientSelect() throws Exception;

    List<Institute> instituteSelect() throws Exception;

    List<Spot> spotSelect() throws Exception;

    List<Work> workSelect(int id) throws Exception;

    List<Career> careerSelect(int id) throws Exception;

    List<Qualifications> qualifiSelect(int id) throws Exception;

    List<Education> educationSelect(int id) throws Exception;

    List<EduMatter> eduMatterSelect(int id) throws Exception;

    List<Prize> prizeSelect(int id) throws Exception;

    List<Qualification_code> qualification_codeSelect() throws Exception;
}
