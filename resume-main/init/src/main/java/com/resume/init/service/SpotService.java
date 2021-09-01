package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.SpotMapper;
import com.resume.init.domain.Spot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotService {
    
    @Autowired
    public SpotMapper spotMapper;

    public List<Spot> getAllSpot(HashMap<String, Object> hashMap){
        return spotMapper.getAllSpot(hashMap);
    }

    public int updateSpot(Spot spot)  throws Exception{
        return spotMapper.updateSpot(spot);
    }

    public int deleteSpot(Spot spot) throws Exception {
        return spotMapper.deleteSpot(spot);
    }

    public int saveSpot(Spot spot) throws Exception {
        return spotMapper.saveSpot(spot);
    }
}
