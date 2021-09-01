package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Institute;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface InstituteMapper {

    List<Institute> getAllInstitute(HashMap<String, Object> hashMap);

    int deleteInstitute(Institute institute) throws Exception;

    int saveInstitute(Institute institute) throws Exception;

    int updateInstitute(Institute institute) throws Exception;
}
