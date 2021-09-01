package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Qualifications;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QualificationsMapper {
    
    List<Qualifications> getAllQualifications(HashMap<String, Object> hashMap);

    int deleteQualifications(Qualifications qualifications) throws Exception;

    int saveQualifications(Qualifications qualifications) throws Exception;

    int updateQualifications(Qualifications qualifications) throws Exception;
}
