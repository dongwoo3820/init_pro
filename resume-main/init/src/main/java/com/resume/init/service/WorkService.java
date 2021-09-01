package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.WorkMapper;
import com.resume.init.domain.Work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkService {
    
    @Autowired
    public WorkMapper workMapper;

    public List<Work> getAllWork(HashMap<String, Object> hashMap){
        return workMapper.getAllWork(hashMap);
    }

    public int updateWork(Work work)  throws Exception{
        return workMapper.updateWork(work);
    }

    public int deleteWork(Work work) throws Exception {
        return workMapper.deleteWork(work);
    }

    public int saveWork(Work work) throws Exception {
        return workMapper.saveWork(work);
    }
}
