package com.xsd.serialportproject;

import com.xsd.serialportproject.config.MyLister;
import com.xsd.serialportproject.service.PortInit;
import com.xsd.serialportproject.utils.SerialPortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class SerialPortProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SerialPortProjectApplication.class, args);
    }

    @PreDestroy
    public void destroy() {
        //关闭应用前 关闭端口
        SerialPortUtil serialPortUtil = SerialPortUtil.getSerialPortUtil();
        serialPortUtil.removeListener(PortInit.serialPort, new MyLister());
        serialPortUtil.closePort(PortInit.serialPort);
    }
}
