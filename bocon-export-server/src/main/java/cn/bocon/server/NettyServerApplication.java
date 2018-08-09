package cn.bocon.server;

import java.io.File;
import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import cn.bocon.server.netty.BoconServer;
import io.netty.channel.ChannelFuture;

@SpringBootApplication
//mapper 接口类扫描包配置
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
		File file = new File("D:\\rtd_data.csv");
		if (file.exists()) {
			file.delete();
		}
		file = new File("D:\\minute_data.csv");
		if (file.exists()) {
			file.delete();
		}
		file = new File("D:\\hour_data.csv");
		if (file.exists()) {
			file.delete();
		}
		file = new File("D:\\day_data.csv");
		if (file.exists()) {
			file.delete();
		}
		
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