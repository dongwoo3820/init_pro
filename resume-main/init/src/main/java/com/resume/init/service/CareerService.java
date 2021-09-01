package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.CareerMapper;
import com.resume.init.domain.Career;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerService {
    
    @Autowired
    public  CareerMapper careerMapper;

    public List<Career> getAllCareer(HashMap<String, Object> hashMap){
        return careerMapper.getAllCareer(hashMap);
    }

    public int updateCareer(Career career)  throws Exception{
        return careerMapper.updateCareer(career);
    }

    public int deleteCareer(Career career) throws Exception {
        return careerMapper.deleteCareer(career);
    }

    public int saveCareer(Career career) throws Exception {
        return careerMapper.saveCareer(career);
    }
}
