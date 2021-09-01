package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Prize;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PrizeMapper {
    
    List<Prize> getAllPrize(HashMap<String, Object> hashMap);

    int deletePrize(Prize prize) throws Exception;

    int savePrize(Prize prize) throws Exception;

    int updatePrize(Prize prize) throws Exception;
}
