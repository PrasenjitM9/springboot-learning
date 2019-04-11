package cn.bocon;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws Exception {

		// 执行批处理文件
		Runtime rt = Runtime.getRuntime();
		String strcmd = "cmd /k start D:\\test\\startup.bat";
		Process ps = null;
		try {
			ps = rt.exec(strcmd);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ps.destroy();
		ps = null;

		// 批处理执行完后，根据cmd.exe进程名称 kill掉cmd窗口(这个方法是好不容易才找到了，网上很多介绍的都无效)
		new Test().killProcess();

	}

	public void killProcess() {
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		try {
			rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
