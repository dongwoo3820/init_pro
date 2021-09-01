package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.EduMatterMapper;
import com.resume.init.domain.EduMatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduMatterService {
    
    @Autowired
    public EduMatterMapper eduMatterMapper;

    public List<EduMatter> getAllEduMatter(HashMap<String, Object> hashMap){
        return eduMatterMapper.getAllEduMatter(hashMap);
    }

    public int updateEduMatter(EduMatter eduMatter)  throws Exception{
        return eduMatterMapper.updateEduMatter(eduMatter);
    }

    public int deleteEduMatter(EduMatter eduMatter) throws Exception {
        return eduMatterMapper.deleteEduMatter(eduMatter);
    }

    public int saveEduMatter(EduMatter eduMatter) throws Exception {
        return eduMatterMapper.saveEduMatter(eduMatter);
    }
}
