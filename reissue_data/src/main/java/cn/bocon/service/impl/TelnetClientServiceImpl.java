package cn.bocon.service.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.bocon.netty.client.TelnetClientInitializer;
import cn.bocon.service.ITelnetClientService;

/**
 * Simplistic telnet client.
 */
@Service
@Scope("prototype")
public class TelnetClientServiceImpl implements ITelnetClientService {

    public static Logger logger = Logger.getLogger(TelnetClientServiceImpl.class);
    @Value("${reverse.host}")
    private String host;
    @Value("${reverse.port}")
    private String port;
    
    public synchronized void sendMsg(String msg) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new TelnetClientInitializer());

            // Start the connection attempt.
            Channel ch = b.connect(host, Integer.valueOf(port)).sync().channel();

            // Read commands from the stdin.
            ChannelFuture lastWriteFuture = null;
    		
			lastWriteFuture = ch.writeAndFlush(msg);
            // Wait until all messages are flushed before closing the channel.
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
