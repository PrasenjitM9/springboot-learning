package cn.bocon.server.service.impl;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.google.common.io.Files;

import cn.bocon.server.service.IPollantinfoService;

@Service
public class PollantinfoServiceImpl implements IPollantinfoService {

	@Override
	public Map<String, String> getPollantinfo() throws Exception {
		Map<String, String> polMap = Maps.newHashMap();
		File directory = new File("");//设定为当前文件夹 
		File f = new File(directory.getAbsolutePath() + "\\pollantinfo.txt"); //获取绝对路径 
		List<String> readLines = Files.readLines(f, Charset.forName("UTF-8"));
		for (int i = 0; i < readLines.size(); i++) {
			String temp = readLines.get(i);
			String[] arr = temp.split(",");
			polMap.put(arr[0], arr[1]);
			polMap.put(arr[0] + "Z", arr[1] + "折算");
		}
		return polMap;
	}

	
}
