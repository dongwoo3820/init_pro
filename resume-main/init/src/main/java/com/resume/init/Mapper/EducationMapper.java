package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Education;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EducationMapper {
    
    List<Education> getAllEducation(HashMap<String, Object> hashMap);

    int deleteEducation(Education education) throws Exception;

    int saveEducation(Education education) throws Exception;

    int updateEducation(Education education) throws Exception;
}
