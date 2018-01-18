package com.nilo.netty.push;

import com.nilo.netty.push.common.BaseMsg;
import com.nilo.netty.push.common.MsgType;

/**
 *  
 * 心跳检测的消息类型
 */
public class PingMsg extends BaseMsg {
    public PingMsg() {
        super();
        setType(MsgType.PING);
    }
}
