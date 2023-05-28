package com.xsd.serialportproject.job;



import com.xsd.serialportproject.config.MyLister;
import com.xsd.serialportproject.service.PortInit;
import com.xsd.serialportproject.utils.SerialPortUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SendJob {

    /**
     * 定时发送数据
     * initialDelay ：系统启动后，3秒后开始运行此方法
     * fixedDelay：表示此方法运行结束后，过1秒再次运行此方法
     */
    @Scheduled(initialDelay = 1000 * 3, fixedDelay = 1000)
    public void plcAnalytic() {
        String s = "404000090006000400000010f0172323";

        SerialPortUtil.getSerialPortUtil().sendToPort(PortInit.serialPort, MyLister.hex2byte(s));
    }
}
