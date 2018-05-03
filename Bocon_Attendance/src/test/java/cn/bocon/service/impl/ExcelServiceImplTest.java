package cn.bocon.service.impl;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;

import cn.bocon.service.IAttendanceService;
import cn.bocon.service.IExcelService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExcelServiceImplTest {
	@Autowired
	private IExcelService excelService;
	@Autowired
	private IAttendanceService attendanceService;
	
	@Test
	public void testExport() throws Exception {
		List<Map<String, Object>> datas = attendanceService.dealData();
		Map<String, Object> beans = Maps.newHashMap();
		beans.put("records", datas);
		
		String fileName = "D:\\2018年3月份博控打卡记录（处理后）.xls";
		excelService.export("考勤记录表.xls", beans, fileName);
	}

}
