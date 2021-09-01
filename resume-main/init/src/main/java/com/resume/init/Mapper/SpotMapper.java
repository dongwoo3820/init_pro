package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Spot;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SpotMapper {

    List<Spot> getAllSpot(HashMap<String, Object> hashMap);

    int deleteSpot(Spot spot) throws Exception;

    int saveSpot(Spot spot) throws Exception;

    int updateSpot(Spot spot) throws Exception;
}
