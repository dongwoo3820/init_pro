package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.EduMatter;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EduMatterMapper {
    
    List<EduMatter> getAllEduMatter(HashMap<String, Object> hashMap);

    int deleteEduMatter(EduMatter eduMatter) throws Exception;

    int saveEduMatter(EduMatter eduMatter) throws Exception;

    int updateEduMatter(EduMatter eduMatter) throws Exception;
}
