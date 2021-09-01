package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.Qualification_codeMapper;
import com.resume.init.domain.Qualification_code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Qualification_codeService {
    
    @Autowired
    public Qualification_codeMapper qualification_codeMapper;

    public List<Qualification_code> getAllQualification_code(HashMap<String, Object> hashMap){
        return qualification_codeMapper.getAllQualification_code(hashMap);
    }

    public int updateQualification_code(Qualification_code qualification_code)  throws Exception{
        return qualification_codeMapper.updateQualification_code(qualification_code);
    }

    public int deleteQualification_code(Qualification_code qualification_code) throws Exception {
        return qualification_codeMapper.deleteQualification_code(qualification_code);
    }

    public int saveQualification_code(Qualification_code qualification_code) throws Exception {
        return qualification_codeMapper.saveQualification_code(qualification_code);
    }
}
