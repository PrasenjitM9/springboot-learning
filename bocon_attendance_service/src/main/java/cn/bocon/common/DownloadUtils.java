package cn.bocon.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class DownloadUtils {
	public static Logger logger = Logger.getLogger(DownloadUtils.class);
	
	public static void doload(String filePath,HttpServletResponse response,String filaName) throws Exception{
		File file = new File(filePath);
		String filename = filaName;// 在浏览器下载框中显示的文件名称
		// 设置response的编码方式
		response.setContentType("application/octet-stream");
		// 写明要下载的文件的大小
		response.setContentLength((int) file.length());
		// 解决中文乱码
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(filename.getBytes("gbk"), "iso-8859-1"));
		// 读出文件到i/o流
		FileInputStream input = new FileInputStream(file);
		// 从response对象中得到输出流,准备下载
		OutputStream output = response.getOutputStream(); 
		byte[] cache = new byte[1024 * 8];// 缓存数组
		int length = 0; // 该值用于计算当前实际下载了多少字节
		while ((length = input.read(cache)) != -1) {
			output.write(cache, 0, length);
		}
		output.close();
		input.close();
	}
	
	public static void doloadToDevmanager(String filePath,HttpServletResponse response,String filaName) throws Exception{
		File file = new File(filePath);
		// 写明要下载的文件的大小
		response.setContentLength((int) file.length());
		// 读出文件到i/o流 
		response.setHeader("Connection", "keep-alive");
		// 解决中文乱码
		response.setHeader("Content-Disposition", "attachment;filename="+filaName);
		response.setContentType("application/octet-stream");
		FileInputStream input = new FileInputStream(file);
		// 从response对象中得到输出流,准备下载
		OutputStream output = response.getOutputStream(); 
		byte[] cache = new byte[1024 * 8];// 缓存数组
		int length = 0; // 该值用于计算当前实际下载了多少字节
		while ((length = input.read(cache)) != -1) {
			output.write(cache, 0, length);
		}
		output.close();
		input.close();
	}
	
	/**
	 * 
	* @Title: writeFile
	* @Description: 下载文件
	* @param response
	* @param filePath
	* @param isDelete 下载后是否删除 默认false不删除
	 */
	public static void writeFile(HttpServletResponse response, String filePath, boolean isDelete) {
		try {
			if(StringUtils.isEmpty(filePath)){
				logger.error("返回文件失败！文件路径为Null");
				return;
			}
			File file = new File(filePath);
			if(!file.exists()){
				logger.error("返回文件失败，文件不存在...");
				return;
			}
			String filename = file.getName();
			// 设置response的编码方式
			response.setContentType("application/vnd.ms-excel");
			// 写明要下载的文件的大小
			response.setContentLength((int) file.length());
			// 解决中文乱码
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes("gbk"), "iso-8859-1"));
			// 读出文件到i/o流
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream buff = new BufferedInputStream(fis);

			byte[] b = new byte[1024];// 相当于我们的缓存
			long k = 0; // 该值用于计算当前实际下载了多少字节
			OutputStream myout = response.getOutputStream(); // 从response对象中得到输出流,准备下载
			while (k < file.length()) {
				int j = buff.read(b, 0, 1024);
				k += j;
				myout.write(b, 0, j); // 将b中的数据写到客户端的内存
			}
			myout.flush();
			myout.close();
			buff.close();
			
			if (isDelete == true) {
				file.delete();
			}
		} catch (Exception e) {
			logger.error("返回文件失败", e);
		}
	}	
}
