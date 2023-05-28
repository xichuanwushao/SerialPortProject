package com.xsd.serialportproject;

import com.xsd.serialportproject.config.MyLister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;

//@SpringBootTest                                 // 默认启动整个程序
//@SpringBootTest(classes = Application.class)    // 启动整个程序
@SpringBootTest(classes = Test.class)           // 启动Test类
class SerialPortProjectApplicationTests {


    @Test
    void contextLoads() {
        String s = "404000090006000400000010f0172323";
        byte[] bytes = s.getBytes();
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
//        System.out.println(s.getBytes());
    }


    /**
     * 字节数组流
     * 取出下列字符串中的所有大写的英文字母:"12446678ADFDHHCNdghjk,>>;;;{]][;/";
     */
    @Test

    //    public void test() {
//        ByteStream jj = new ByteStream();
//        jj.bytes();
//
//    }
    public void bytes() {
        String str = "12446678ADFDHHCNdghjk,>>;;;{]][;/";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1;//每次读取的字节
        while ((len = bais.read()) != -1) {//如果为-1表示已经读取完了
            if ((len >= 65 && len <= 90)) {
                baos.write(len);
            }
        }
        //此时不需要关闭流，因为字节数组流是基于内存的操作流
        System.out.println(baos.toString());

    }


    @Test
    public void test01() throws IOException {
        FileOutputStream fos = new FileOutputStream("d:\\test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //二、通过对象流进行读写
        oos.writeInt(97); //Integer
        oos.writeDouble(100.25); //Double
        oos.writeUTF("对象流"); //String
        //三、关闭流资源
        oos.close();
        fos.close();
        //一、创建流对象
        FileInputStream fis = new FileInputStream("d:\\test.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        //二、通过对象流进行读写 (读取的顺序和写入的顺序要一致)
        System.out.println(ois.readInt());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());
        //三、关闭流资源
        ois.close();
        fis.close();
    }


    @Test
    public void hexStringToByteArray() {
        String s = "4040 0009 0006 00 04 0000 0010 f017 2323";
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        for (byte datum : data) {
            System.out.println(datum);
        }
    }

    @Test
    public void  hex2Byte() {
        String s = "404000090006000400000010f0172323";
        for (byte b : MyLister.hex2byte(s)) {
            System.out.println(b);
        }
    }
}
