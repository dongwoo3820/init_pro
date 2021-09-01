package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.InstituteMapper;
import com.resume.init.domain.Institute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstituteService {
    
    @Autowired
    public InstituteMapper instituteMapper;

    public List<Institute> getAllInstitute(HashMap<String, Object> hashMap){
        return instituteMapper.getAllInstitute(hashMap);
    }

    public int updateInstitute(Institute institute)  throws Exception{
        return instituteMapper.updateInstitute(institute);
    }

    public int deleteInstitute(Institute institute) throws Exception {
        return instituteMapper.deleteInstitute(institute);
    }

    public int saveInstitute(Institute institute) throws Exception {
        return instituteMapper.saveInstitute(institute);
    }
}
