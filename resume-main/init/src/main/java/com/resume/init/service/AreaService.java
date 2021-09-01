package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.AreaMapper;
import com.resume.init.domain.Area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaService {
    
    @Autowired
    public AreaMapper areaMapper;

    public List<Area> getAllArea(HashMap<String, Object> hashMap){
        return areaMapper.getAllArea(hashMap);
    }

    public int updateArea(Area area)  throws Exception{
        return areaMapper.updateArea(area);
    }

    public int deleteArea(Area area) throws Exception {
        return areaMapper.deleteArea(area);
    }

    public int saveArea(Area area) throws Exception {
        return areaMapper.saveArea(area);
    }
}
