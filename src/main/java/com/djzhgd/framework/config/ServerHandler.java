
package com.djzhgd.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;


/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: ServerHandler
 * @Author: zhangheng
 * @DATE: 2020/5/13 18:07
 * @Version 1.0
 **/

@Slf4j
public class ServerHandler extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        log.error("出现异常 :" + session.getRemoteAddress().toString() + " : " + cause.toString());
        session.closeNow();

    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.info("连接创建 : " + session.getRemoteAddress().toString());
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println(session);
        log.info("连接打开: " + session.getRemoteAddress().toString());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        log.info("接受到数据 :" + message);


    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.info("返回消息内容 : " + message.toString());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        if (status == IdleStatus.READER_IDLE) {
            //logger.info("进入读空闲状态");
            //session.closeNow();
        } else if (status == IdleStatus.BOTH_IDLE) {
            //  logger.info("BOTH空闲");
            //session.closeNow();
        }
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        String address = session.getRemoteAddress().toString().replace("/", "");
        log.info(address + "  会话关闭了!");
    }

}

