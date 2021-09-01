package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.EducationMapper;
import com.resume.init.domain.Education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    
    @Autowired
    public EducationMapper educationMapper;

    public List<Education> getAllEducation(HashMap<String, Object> hashMap){
        return educationMapper.getAllEducation(hashMap);
    }

    public int updateEducation(Education education)  throws Exception{
        return educationMapper.updateEducation(education);
    }

    public int deleteEducation(Education education) throws Exception {
        return educationMapper.deleteEducation(education);
    }

    public int saveEducation(Education education) throws Exception {
        return educationMapper.saveEducation(education);
    }
}
