package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Occupation;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OccupationMapper {

    List<Occupation> getAllOccupation(HashMap<String, Object> hashMap);

    int deleteOccupation(Occupation occupation) throws Exception;

    int saveOccupation(Occupation occupation) throws Exception;

    int updateOccupation(Occupation occupation) throws Exception;

}

