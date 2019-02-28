package com.emall.service.impl;

import com.emall.dao.UserDOMapper;
import com.emall.dao.UserPasswordDOMapper;
import com.emall.dataObject.UserDO;
import com.emall.dataObject.UserPasswordDO;
import com.emall.service.Model.UserModel;
import com.emall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDO == null){
            return null;
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = new UserModel();
        if (userPasswordDO != null) {
            userModel = convertFromDataObject(userDO,userPasswordDO);
        }
        return userModel;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){

        if(userDO == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);

        if(userPasswordDO != null){
            userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        }
        return userModel;
    }
}
