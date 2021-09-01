package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;

import com.resume.init.domain.Client;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ClientMapper {

    List<Client> getAllClient(HashMap<String, Object> hashMap);

    int deleteClient(Client client) throws Exception;

    int saveClient(Client client) throws Exception;

    int updateClient(Client client) throws Exception;
}
