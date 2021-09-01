package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Qualification_code;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Qualification_codeMapper {

    List<Qualification_code> getAllQualification_code(HashMap<String, Object> hashMap);

    int deleteQualification_code(Qualification_code qualification_code) throws Exception;

    int saveQualification_code(Qualification_code qualification_code) throws Exception;

    int updateQualification_code(Qualification_code qualification_code) throws Exception;
}
