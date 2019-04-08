package socket.SocketDemo2.demo1;

import socket.SocketDemo2.MessageCreator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by lifuqing on  2019/4/8 15:39
 * Email : daqing.lee@hotmail.com
 */
public class UDPProvider {
    public static void main(String [] args) throws IOException {
        System.out.println("UDPProvider Started.");

        // 监听20000 端口
        DatagramSocket ds = new DatagramSocket(20000);

        // 构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

        // 接收数据并将数据放到实体receivePack中
        ds.receive(receivePack);

        // 打印接收到的信息与发送者的信息
        // 发送者的IP地址
        String ip = receivePack.getAddress().getHostAddress();//从实体中获得IP地址
        int port = receivePack.getPort();//端口
        int dataLen = receivePack.getLength();//大小
        String data = new String(receivePack.getData(), 0, dataLen);//获得数据

        //输出
        System.out.println("UDPProvider receive form ip:" + ip + "\tport:" + port + "\tdata:" + data);


        //构建一份回送数据
        String responseData = "Receive data with len:"+data.length();
        byte[] responseDataBytes = responseData.getBytes();


        // 直接根据发送者构建一份回送信息
        DatagramPacket responsePacket = new DatagramPacket(responseDataBytes,
                responseDataBytes.length,
                receivePack.getAddress(),
                receivePack.getPort());

        ds.send(responsePacket);
        System.out.println("UDPProvider Finished.");
        ds.close();
        }


}
