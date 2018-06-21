package cn.bocon.service.impl;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bocon.service.IAttendanceService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AttendanceServiceImplTest {
	private Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private IAttendanceService attendanceService;
	@Test
	public void testDealData() throws Exception {
		attendanceService.dealData();
	}

}
