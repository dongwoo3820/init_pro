package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Career;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CareerMapper {
    

    List<Career> getAllCareer(HashMap<String, Object> hashMap);

    int deleteCareer(Career career) throws Exception;

    int saveCareer(Career career) throws Exception;

    int updateCareer(Career career) throws Exception;
}
