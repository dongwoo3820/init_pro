package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Work;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WorkMapper {
    
    List<Work> getAllWork(HashMap<String, Object> hashMap);

    int deleteWork(Work work) throws Exception;

    int saveWork(Work work) throws Exception;

    int updateWork(Work work) throws Exception;
}
