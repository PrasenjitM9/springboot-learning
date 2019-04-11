package cn.bocon;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SenderConf {
	@Bean
	public Queue queue() {
		return new Queue("queue");
	}
	
	@Bean
	public Queue queue0() {
		return new Queue("serialQueue");
	}
	
	@Bean
	public Queue queue1() {
		return new Queue("osdQueue");
	}	
	
	@Bean
	public Queue queue2() {
		return new Queue("oneToManyQueue");
	}	
}
