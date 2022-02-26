package com.imooc.mall.service.impl;

import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.dao.UserMapper;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.service.UserService;
import com.imooc.mall.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;


/**
 * UserService implements
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * Get User By Id
     * @return
     */
    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }

    /**
     * User register
     * @param username
     * @param password
     */
    @Override
    public void register(String username, String password) throws ImoocMallException {
//        Users are not allowed to duplicate names
        User result = userMapper.selectByName(username);
        if (result != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        User user = new User();
        user.setUsername(username);
//        user.setPassword(password);
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.INSERT_FAILED);
        }
    }

    /**
     * user login
     * @param username
     * @param password
     * @return
     * @throws ImoocMallException
     */
    @Override
    public User login(String username, String password) throws ImoocMallException {
        String md5password =null;
        try {
            md5password = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userMapper.selectLogin(username, md5password);
        if (user == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.WRONG_USERNAME_OR_PASSWORD);
        }
        return user;
    }

    /**
     * update user info
     * @param user
     * @throws ImoocMallException
     */
    @Override
    public void updateInformation(User user) throws ImoocMallException {
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount < 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }

    /**
     * check user role
     * @param user
     * @return
     */
    @Override
    public boolean checkAdminRole(User user) {
//        1 is a normal user, 2 is an administrator
        return user.getRole().equals(2);
    }

}
