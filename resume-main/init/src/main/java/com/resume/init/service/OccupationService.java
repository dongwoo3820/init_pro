package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.OccupationMapper;
import com.resume.init.domain.Occupation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OccupationService {
    
    @Autowired
    public OccupationMapper occupationMapper;


    public List<Occupation> getAllOccupation(HashMap<String, Object> hashMap){
        return occupationMapper.getAllOccupation(hashMap);
    }

    public int updateOccupation(Occupation occupation)  throws Exception{
        return occupationMapper.updateOccupation(occupation);
    }

    public int deleteOccupation(Occupation occupation) throws Exception {
        return occupationMapper.deleteOccupation(occupation);
    }

    public int saveOccupation(Occupation occupation) throws Exception {
        return occupationMapper.saveOccupation(occupation);
    }

}
