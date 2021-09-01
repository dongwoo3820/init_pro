package com.resume.init.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.resume.init.domain.FileAttach;
import com.resume.init.domain.ImageAttach;
import com.resume.init.domain.UserExt;
import com.resume.init.domain.WorkArea;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    List<UserExt> getAllUser(HashMap<String, Object> hashMap);

    List<UserExt> getSearchUser(Map<String, Object> Map);

    UserExt selectInfoID();

    int deleteUser(UserExt user) throws Exception;

    int saveUser(UserExt user) throws Exception;

    int updateUser(UserExt user) throws Exception;

    int fileInsert(FileAttach file) throws Exception;

    int fileDelete(int id) throws Exception;

    int imgInsert(ImageAttach img) throws Exception;

    int imgDelete(int id) throws Exception;

    int workAreaInsert(String id) throws Exception;

    int workAreaDelete(String id, String area_id) throws Exception;

    int workAreaUpdate(WorkArea workArea) throws Exception;
}
