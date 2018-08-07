package cn.bocon.server;

import io.netty.channel.ChannelFuture;

import java.net.InetSocketAddress;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import cn.bocon.server.netty.BoconServer;

@SpringBootApplication
//mapper 接口类扫描包配置
@MapperScan("cn.bocon.server.mapper")
@EnableScheduling
public class NettyServerApplication implements CommandLineRunner{
	@Autowired
	private BoconServer boconServer;
	@Value("${bocon.port}")
	private Integer port;
	@Value("${server.port}")
	private Integer webPort;	
	
    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }
    
    @Bean
    public BoconServer boconServer() {
    	return new BoconServer();
    }

	@Override
	public void run(String... args) throws Exception {
		InetSocketAddress address = new InetSocketAddress(port);
		ChannelFuture future = boconServer.start(address, webPort);
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				boconServer.destroy();
			}
		});
		
		future.channel().closeFuture().syncUninterruptibly();
	}
}