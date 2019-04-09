package cn.bocon.server.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.netty.util.AttributeKey;

public class Constants {
	public static final AttributeKey<String> CHANNEL_TOKEN_KEY = AttributeKey
			.valueOf("netty.channel.token");
	/** 用来保存当前在线设备 */
	public static Map<String, String> onlines = Maps.newConcurrentMap();
	
	//保存数据包
	public static List<Map<String, String>> dataPackages = Lists.newArrayList();

	public static void addOnlines(String sessionId, String pointnum) {
		onlines.put(sessionId, pointnum);
	}

	public static void removeOnlines(String sessionId) {
		if (StringUtils.isNotBlank(sessionId) && onlines.containsKey(sessionId)) {
			onlines.remove(sessionId);
		}
	}

}
