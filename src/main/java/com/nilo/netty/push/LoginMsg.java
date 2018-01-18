package com.nilo.netty.push;

import com.nilo.netty.push.common.BaseMsg;
import com.nilo.netty.push.common.MsgType;

/**
 *  
 * 登录验证类型的消息
 */
public class LoginMsg extends BaseMsg {
    private String userName;
    private String password;
    public LoginMsg() {
        super();
        setType(MsgType.LOGIN);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
