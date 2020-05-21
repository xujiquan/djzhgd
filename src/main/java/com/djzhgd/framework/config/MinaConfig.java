package com.djzhgd.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: MinaConfig
 * @Author: zhangheng
 * @DATE: 2020/5/13 18:05
 * @Version 1.0
 **/

@Slf4j
@Configuration
public class MinaConfig {

    // socket占用端口
    @Value("${mina.port}")
    private int port;

    /** 15秒发送一次心跳包 */

    private static final int HEARTBEATRATE = 15;

    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }

    @Bean
    public IoHandler ioHandler() { return new ServerHandler(); }

    @Bean
    public InetSocketAddress inetSocketAddress() {
        return new InetSocketAddress(port);
    }

    @Bean
    public IoAcceptor ioAcceptor() throws Exception {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger", loggingFilter());
        // 使用自定义编码解码工厂类  设置编码过滤器
        acceptor.getFilterChain().addLast("coderc", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        acceptor.getSessionConfig().setReadBufferSize(1024*1024);//设置缓冲区
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);  //配置会话信息

        acceptor.setHandler(ioHandler()); //自定义处理业务的代码：自定义的类
        acceptor.bind(inetSocketAddress());//绑定端口号
        log.info("Socket服务器在端口：" + port + "已经启动");

        return acceptor;
    }

}
