package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.QualificationsMapper;
import com.resume.init.domain.Qualifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualificationsService {
    
    @Autowired
    public QualificationsMapper qualificationsMapper;

    public List<Qualifications> getAllQualifications(HashMap<String, Object> hashMap){
        return qualificationsMapper.getAllQualifications(hashMap);
    }

    public int updateQualifications(Qualifications qualifications)  throws Exception{
        return qualificationsMapper.updateQualifications(qualifications);
    }

    public int deleteQualifications(Qualifications qualifications) throws Exception {
        return qualificationsMapper.deleteQualifications(qualifications);
    }

    public int saveQualifications(Qualifications qualifications) throws Exception {
        return qualificationsMapper.saveQualifications(qualifications);
    }
}
