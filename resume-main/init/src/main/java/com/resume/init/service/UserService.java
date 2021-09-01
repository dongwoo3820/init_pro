package com.resume.init.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.resume.init.Mapper.UserMapper;
import com.resume.init.domain.FileAttach;
import com.resume.init.domain.ImageAttach;
import com.resume.init.domain.UserExt;
import com.resume.init.domain.WorkArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    public UserMapper userMapper;

    public List<UserExt> getAllUser(HashMap<String, Object> hashMap){
        return userMapper.getAllUser(hashMap);
    }

    public List<UserExt> getSearchUser(Map<String, Object> map){
        return userMapper.getSearchUser(map);
    }

    public UserExt selectInfoID()  throws Exception{
        return userMapper.selectInfoID();
    }

    public int updateUser(UserExt user)  throws Exception{
        return userMapper.updateUser(user);
    }

    public int deleteUser(UserExt user) throws Exception {
        return userMapper.deleteUser(user);
    }

    public int saveUser(UserExt user) throws Exception {
        return userMapper.saveUser(user);
    }

    public int fileInsert(FileAttach file) throws Exception {
        return userMapper.fileInsert(file);
    }

    public int fileDelete(int id) throws Exception {
        return userMapper.fileDelete(id);
    }

    public int imgInsert(ImageAttach img) throws Exception {
        return userMapper.imgInsert(img);
    }

    public int imgDelete(int id) throws Exception {
        return userMapper.imgDelete(id);
    }

    public int workAreaInsert(String id) throws Exception {
        return userMapper.workAreaInsert(id);
    }

    public int workAreaDelete(String id, String area_id) throws Exception {
        return userMapper.workAreaDelete(id,area_id);
    }

    public int workAreaUpdate(WorkArea workArea) throws Exception {
        return userMapper.workAreaUpdate(workArea);
    }

    
}
