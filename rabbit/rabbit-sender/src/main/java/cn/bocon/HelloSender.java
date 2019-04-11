package cn.bocon;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
	@Autowired
	private AmqpTemplate template;

	/**
	 * 发送字符串
	 * 
	 * @param str
	 */
	public void send0(String str) {
		template.convertAndSend("queue", str);
	}
	
	/**
	 * 发送字符串
	 * 
	 * @param str
	 */
	public void sendOsd(String str) {
		template.convertAndSend("osdQueue", str);
	}	

	/**
	 * 发送字符串
	 * 
	 * @param str
	 */
	public void sendOneToMany(String str) {
		template.convertAndSend("oneToManyQueue", str);
	}

	/**
	 * 发送序列化对象
	 */
	public void send() {
		User user = new User();
		user.setUsername("test");
		user.setPassword("123456");
		template.convertAndSend("serialQueue", user);
	}

	/**
	 * 发送主题
	 */
	public void sendTopic() {
		template.convertAndSend("exchange", "topic.message", "hello, world");
	}

	/**
	 * 发送广播
	 */
	public void sendFanout() {
		template.convertAndSend("fanoutExchange", "", "hello, world");
	}

}
