package socket.SocketDemo1;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;

/**
 * Created by lifuqing on  2019/4/1 19:56
 * Email : daqing.lee@hotmail.com
 */
public class Client {
    public static void main(String [] args) throws IOException{
        Socket socket = new Socket();
        //超时时间
        socket.setSoTimeout(3000);
        //连接本地，端口号，超时时间3000ms
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(),2000),3000);
        System.out.println("已经发起服务器连接，并进入后续流程～");
        System.out.println("客户端信息："+socket.getLocalAddress()+"P:"+socket.getLocalPort());
        System.out.println("客户端信息："+socket.getInetAddress()+"P:"+socket.getPort());
        try {
            todo(socket);
        }catch (Exception e){
            System.out.println("异常关闭");
        }
        socket.close();
        System.out.println("客户端已经退出");
    }

    private static void todo(Socket client) throws IOException{
        //构建键盘输入流程
        InputStream in =System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        //得到Socket输出流，并转换为打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream soketPrintStream = new PrintStream(outputStream);

        //得到Socket输入流,并转换为bufferReader
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = true;
        do{
            //从键盘读取一行
            String str = input.readLine();
            //发送到服务器
            soketPrintStream.println(str);

            //从服务器读取一行
            String echo = socketBufferedReader.readLine();
            if("bye".equalsIgnoreCase(echo)){
                flag = false;
            }else{
                System.out.println(echo);
            }
        }while (flag);

        //资源释放
        soketPrintStream.close();
        socketBufferedReader.close();



  }
}
