package socket.SocketDemo2.demo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by lifuqing on  2019/4/8 15:39
 * Email : daqing.lee@hotmail.com
 */
public class UDPSearcher {
    public static void main(String [] args) throws IOException {
        System.out.println("UDPSearcher Started.");

        // 作为搜索方，无需指定端口
        DatagramSocket ds = new DatagramSocket();

        //构建一份请求数据
        String requestData = "Hello world";
        byte[] requestDataBytes = requestData.getBytes();

        // 直接根据发送者构建一份回送信息
        DatagramPacket responsePacket = new DatagramPacket(requestDataBytes, requestDataBytes.length);
        responsePacket.setAddress(InetAddress.getLocalHost());
        responsePacket.setPort(20000);

        //发送
        ds.send(responsePacket);

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
        System.out.println("UDPSearcher receive form ip:" + ip + "\tport:" + port + "\tdata:" + data);

        //
        System.out.println("UDPSearcher Finished.");
        ds.close();
    }
}
