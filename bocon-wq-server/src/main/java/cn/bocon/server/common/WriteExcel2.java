package cn.bocon.server.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel2 {
	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	public static void writeExcel(Map<String, Object> dataMap, String finalXlsxPath) {
		OutputStream out = null;
		try {
			// 读取Excel文档
			File finalXlsxFile = new File(finalXlsxPath);
			
			Workbook workBook = getWorkbok(finalXlsxFile);
			// sheet 对应一个工作页
			Sheet sheet = workBook.getSheetAt(0);

			// 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
			/**
			 * 往Excel中写新数据
			 */
			
			// 得到要插入的每一条记录
			String mn = dataMap.get("MN").toString();
			if ("88888880000341".equals(mn)) {
				Row row = sheet.createRow(1);
				String dateTime = DateUtils.formatDateTime((Date)dataMap.get("DataTime"));
				String b01 = (String)dataMap.get("B01");
				String p011 = (String)dataMap.get("011");
				String p001 = (String)dataMap.get("001");
				String p101 = (String)dataMap.get("101");
				String p060 = (String)dataMap.get("060");
				String p003 = (String)dataMap.get("003");
				String b00 = (String)dataMap.get("B00");
				
				Cell first = row.createCell(0);
				first.setCellValue(mn);

				Cell second = row.createCell(1);
				second.setCellValue(dateTime);

				Cell third = row.createCell(2);
				third.setCellValue(b01);
				
				Cell four = row.createCell(3);
				four.setCellValue(p011);	
				
				Cell five = row.createCell(4);
				five.setCellValue(p001);	
				
				Cell six = row.createCell(5);
				six.setCellValue(p101);	
				
				Cell seven = row.createCell(6);
				seven.setCellValue(p060);	
				
				Cell eight = row.createCell(7);
				eight.setCellValue(p003);		
				
				Cell nine = row.createCell(8);
				nine.setCellValue(b00);					
			} else if ("88888880000342".equals(mn)) {
				Row row = sheet.createRow(2);
				String dateTime = DateUtils.formatDateTime((Date)dataMap.get("DataTime"));
				String b01 = (String)dataMap.get("B01");
				String p011 = (String)dataMap.get("011");
				String p001 = (String)dataMap.get("001");
				String p101 = (String)dataMap.get("101");
				String p060 = (String)dataMap.get("060");
				String p003 = (String)dataMap.get("003");
				String b00 = (String)dataMap.get("B00");
				
				Cell first = row.createCell(0);
				first.setCellValue(mn);

				Cell second = row.createCell(1);
				second.setCellValue(dateTime);

				Cell third = row.createCell(2);
				third.setCellValue(b01);
				
				Cell four = row.createCell(3);
				four.setCellValue(p011);	
				
				Cell five = row.createCell(4);
				five.setCellValue(p001);	
				
				Cell six = row.createCell(5);
				six.setCellValue(p101);	
				
				Cell seven = row.createCell(6);
				seven.setCellValue(p060);	
				
				Cell eight = row.createCell(7);
				eight.setCellValue(p003);		
				
				Cell nine = row.createCell(8);
				nine.setCellValue(b00);					
			}
					
			// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
			
			File toFile = new File("D:\\TmpData.xls");
			FileUtils.copyFile(finalXlsxFile, toFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("数据导出成功");
	}

	/**
	 * 判断Excel的版本,获取Workbook
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbok(File file) throws IOException {
		Workbook wb = null;
		FileInputStream in = new FileInputStream(file);
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		in.close();
		return wb;
	}
	
}