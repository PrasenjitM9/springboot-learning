package cn.bocon;

import javax.swing.tree.DefaultMutableTreeNode;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;

import cn.bocon.HCNetSDK.FMSGCallBack;

@Service
public class TestVideo {
	
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    //static PlayCtrl playControl = PlayCtrl.INSTANCE;

    public static NativeLong g_lVoiceHandle;//全局的语音对讲句柄
        
    HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
    HCNetSDK.NET_DVR_IPPARACFG  m_strIpparaCfg;//IP参数
    HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;//用户参数

    boolean bRealPlay;//是否在预览.
    String m_sDeviceIP;//已登录设备的IP地址

    NativeLong lUserID;//用户句柄
    NativeLong lPreviewHandle;//预览句柄
    NativeLongByReference m_lPort;//回调预览时播放库端口指针
    
    NativeLong lAlarmHandle;//报警布防句柄
    NativeLong lListenHandle;//报警监听句柄
    
    FMSGCallBack fMSFCallBack;//报警回调函数实现


    int m_iTreeNodeNum;//通道树节点数目
    DefaultMutableTreeNode m_DeviceRoot;//通道树根节点	
	
    public void test(String str)
    {
        lUserID = new NativeLong(-1);
        lPreviewHandle = new NativeLong(-1);
        lAlarmHandle = new NativeLong(-1);
        lListenHandle = new NativeLong(-1);
        g_lVoiceHandle = new NativeLong(-1);
        m_lPort = new NativeLongByReference(new NativeLong(-1));
        fMSFCallBack = null;
        m_iTreeNodeNum = 0;
        
        //注册
        m_sDeviceIP = "192.168.1.64";//设备ip地址
        String name = "admin";
        String pass = "bocon123456";
        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        int iPort = 8000;
        lUserID = hCNetSDK.NET_DVR_Login_V30(m_sDeviceIP,
                (short) iPort, name, pass, m_strDeviceInfo);

        hCNetSDK.NET_DVR_Init();// 1初始化
        long userID = lUserID.longValue();
        System.out.println(userID);
        
        //叠加字符
        HCNetSDK.NET_DVR_SHOWSTRING_V30 m_strShowString = new HCNetSDK.NET_DVR_SHOWSTRING_V30();//叠加字符结构体
        IntByReference ibrBytesReturned = new IntByReference(0);//获取显示字符参数
        m_strShowString.write();
        Pointer lpStringConfig = m_strShowString.getPointer();
        boolean getDVRConfigSuc = hCNetSDK.NET_DVR_GetDVRConfig(lUserID, HCNetSDK.NET_DVR_GET_SHOWSTRING_V30,
                new NativeLong(1), lpStringConfig, m_strShowString.size(), ibrBytesReturned);
        m_strShowString.read();        
        
        m_strShowString.struStringInfo[0].wShowString = (short)1;
        try {
			m_strShowString.struStringInfo[0].sString =str.getBytes("GB2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
        m_strShowString.struStringInfo[0].wStringSize = (short) 40;
        m_strShowString.struStringInfo[0].wShowStringTopLeftY = (short) 64;
        m_strShowString.struStringInfo[0].wShowStringTopLeftX = (short) 0;    
        
        m_strShowString.write();
        Pointer lpShowString = m_strShowString.getPointer();
        boolean setDVRConfigSuc = hCNetSDK.NET_DVR_SetDVRConfig(lUserID, HCNetSDK.NET_DVR_SET_SHOWSTRING_V30,
                new NativeLong(1), lpShowString, m_strShowString.size());
        m_strShowString.read();
        System.out.println("设置显示字符参数" + setDVRConfigSuc);
    }

    @RabbitListener(queues = "osdQueue") // 监听器监听指定的Queue
    public void test2(String str) {
    	final String temp = str;
        try
        {
          javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                boolean initSuc = hCNetSDK.NET_DVR_Init();
                System.out.println("初始化：" + initSuc);
                
            	TestVideo test = new TestVideo();
            	test.test(temp);
            }
        });   	
    	
	}
    
}
