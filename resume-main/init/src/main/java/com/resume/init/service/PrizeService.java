package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.PrizeMapper;
import com.resume.init.domain.Prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrizeService {
    
    @Autowired
    public PrizeMapper prizeMapper;

    public List<Prize> getAllPrize(HashMap<String, Object> hashMap){
        return prizeMapper.getAllPrize(hashMap);
    }

    public int updatePrize(Prize prize)  throws Exception{
        return prizeMapper.updatePrize(prize);
    }

    public int deletePrize(Prize prize) throws Exception {
        return prizeMapper.deletePrize(prize);
    }

    public int savePrize(Prize prize) throws Exception {
        return prizeMapper.savePrize(prize);
    }
}
