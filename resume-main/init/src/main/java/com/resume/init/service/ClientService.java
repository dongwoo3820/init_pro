package com.resume.init.service;

import java.util.HashMap;
import java.util.List;

import com.resume.init.Mapper.ClientMapper;
import com.resume.init.domain.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    public ClientMapper clientMapper;

    public List<Client> getAllClient(HashMap<String, Object> hashMap){
        return clientMapper.getAllClient(hashMap);
    }

    public int updateClient(Client client)  throws Exception{
        return clientMapper.updateClient(client);
    }

    public int deleteClient(Client client) throws Exception {
        return clientMapper.deleteClient(client);
    }

    public int saveClient(Client client) throws Exception {
        return clientMapper.saveClient(client);
    }
}
