package cn.bocon.server.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bocon.server.common.Constants;
import cn.bocon.server.common.DataPackageUtils;
import cn.bocon.server.common.DateUtils;
import cn.bocon.server.common.RegexUtils;

import com.google.common.base.Preconditions;

/**
 * Handles a server-side channel.
 */
@Sharable
public class BoconServerHandler extends SimpleChannelInboundHandler<String> {
	
	public static Logger logger = LoggerFactory.getLogger(BoconServerHandler.class);
	private final ChannelGroup group;
	public Integer webPort;
	
	public BoconServerHandler(ChannelGroup group, Integer webPort) {
		this.group = group;
		this.webPort = webPort;
	}
	
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("Current channel active");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    	Preconditions.checkNotNull(msg, "数据报文应不为空");
    	
    	Channel channel = ctx.channel();
    	String token = channel.attr(Constants.CHANNEL_TOKEN_KEY).get();

    	//**代表反控指令
		if(StringUtils.isEmpty(token) && msg.indexOf("**") == -1) {
			String mn = RegexUtils.getString(msg, "MN=(\\w+);");
			Constants.addOnlines(mn, mn);
			ctx.channel().attr(Constants.CHANNEL_TOKEN_KEY).getAndSet(mn);
			group.add(channel);
		}
  
		logger.warn(DateUtils.getCurrentTimeString() + " " + msg);
		//解析时间
		//Map<String, Object> parsedMap = DataPackageUtils.parse(msg);
		//String dataTime = (String)parsedMap.get("DataTime"); //数据时间
		//String cn = (String)parsedMap.get("CN"); //命令编码
		
		HttpClient httpclient = HttpClients.custom().build();
		HttpPost httpPost = new HttpPost("http://localhost:" + webPort + "/dataCenter/resolve"); //解析实时数据
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("msg", msg));
		HttpEntity httpentity=new UrlEncodedFormEntity(params,"utf-8");
		httpPost.setEntity(httpentity);
		HttpResponse response = httpclient.execute(httpPost);
		InputStream inStream = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"utf-8"));  //请注意这里的编码 
		 StringBuilder strber = new StringBuilder();    
         String line = null;    
		  while ((line = reader.readLine()) != null) {
			  strber.append(line );    
		  }
          inStream.close();    
          System.out.println(strber.toString());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("Current channel channelInactive");
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    	logger.info("Current channel handlerRemoved");
		offlines(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error("=====> {}", cause.getMessage());
    	offlines(ctx);
    }
    
	private void offlines(ChannelHandlerContext ctx) {
		Channel channel = ctx.channel();
		String token = channel.attr(Constants.CHANNEL_TOKEN_KEY).get();
		logger.error("下线设备号：" + token);
		Constants.removeOnlines(token);
		
		group.remove(channel);
		ctx.close();
	}
}
