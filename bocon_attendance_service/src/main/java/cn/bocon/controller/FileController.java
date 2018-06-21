package cn.bocon.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

import cn.bocon.service.IFileService;

@RestController
public class FileController {
	@Autowired
	private IFileService fileService;
	
	/**
	 * 文件上传具体实现方法（单文件上传）
	 * 
	 * @param file
	 * @return
	 * 
	 * @create 2018年3月7日
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				File newFile = new File("D:\\1.xls");
				Files.write(file.getBytes(), newFile);
				fileService.handleData(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			}
			return "ok";
		} else {
			return "上传失败，因为文件是空的.";
		}
	}
}
