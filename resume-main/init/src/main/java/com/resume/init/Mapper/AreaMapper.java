package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Area;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AreaMapper {

    List<Area> getAllArea(HashMap<String, Object> hashMap);

    int deleteArea(Area area) throws Exception;

    int saveArea(Area area) throws Exception;

    int updateArea(Area area) throws Exception;
}
