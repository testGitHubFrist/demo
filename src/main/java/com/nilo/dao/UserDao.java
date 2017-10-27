package com.nilo.dao;

import java.util.Map;

import com.nilo.model.User;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/22.
 */
@Component
public interface UserDao {

    public User queryByName(String loginAccount);

	public int insert(User registerUser);

	public int updateUserPassword(Map<String, Object> paraMap);

}
