package cn.bocon.server.netty;

import java.util.concurrent.TimeUnit;


import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class BoconServerInitializer extends ChannelInitializer<Channel>{
	private final ChannelGroup group;
	private static final StringDecoder DECODER = new StringDecoder();
	private static final StringEncoder ENCODER = new StringEncoder();
	private  final BoconServerHandler SERVER_HANDLER;
	
	public BoconServerInitializer(ChannelGroup group, Integer webPort) {
		this.group = group;
		SERVER_HANDLER = new BoconServerHandler(group, webPort);
	}
	
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//处理日志
		pipeline.addLast(new LoggingHandler(LogLevel.INFO));
		
		//处理心跳
		pipeline.addLast(new IdleStateHandler(0, 0, 180, TimeUnit.SECONDS));
		pipeline.addLast(new HeartbeatHandler());
		
		// Add the text line codec combination first,
		pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters
				.lineDelimiter()));
		// the encoder and decoder are static as these are sharable
		pipeline.addLast(DECODER);
		pipeline.addLast(ENCODER);		
		
		// and then business logic.
		pipeline.addLast(SERVER_HANDLER);		
	}
}
