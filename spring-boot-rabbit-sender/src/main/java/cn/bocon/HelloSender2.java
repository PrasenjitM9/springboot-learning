package cn.bocon;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender2 {
	@Autowired
	private AmqpTemplate template;

	
	/**
	 * 发送字符串
	 * 
	 * @param str
	 */
	public void sendOneToMany(String str) {
		template.convertAndSend("oneToManyQueue", str);
	}	

}
