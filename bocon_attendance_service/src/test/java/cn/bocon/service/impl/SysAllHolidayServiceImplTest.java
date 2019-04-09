package cn.bocon.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bocon.service.ISysAllHolidayService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysAllHolidayServiceImplTest {
	@Autowired
	private ISysAllHolidayService sysAllHolidayService;
	
	@Test
	public void testInsertHoliday() throws Exception {
		sysAllHolidayService.insertHoliday();
	}

}
