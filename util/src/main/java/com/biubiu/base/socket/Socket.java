package com.biubiu.base.socket;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;

/**
 * @author Administrator
 * @description TODO
 * @date 2019/5/29
 */
public class Socket {

    public static void main(String[] args) throws Exception {
        java.net.Socket socket = new java.net.Socket();
        //超时时间
        socket.setSoTimeout(3000);

        //连接本地，端口2000，超时3000ms
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);

        System.out.println("已发起服务器连接，并进入后续流程~");
        System.out.println("客户端信息：" + socket.getLocalAddress() + "P:" + socket.getLocalPort());
        System.out.println("服务器信息：" + socket.getInetAddress() + "P:" + socket.getPort());

        try {
            //发送数据
            todo(socket);
        } catch (Exception e) {
            System.out.println("异常关闭");
        }
        //释放资源
        socket.close();
        System.out.println("客户端已退出");
    }

    private static void todo(java.net.Socket client) throws Exception {
        //构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        //得到socket输出流，并且转换成打打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);

        //得到socket输入流，并转换成BufferedReader
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = true;
        do {
            //键盘读取一行
            String str = input.readLine();
            //发送到服务器
            socketPrintStream.println(str);

            //接收服务器返回结果
            String rs = socketBufferedReader.readLine();
            if ("bye".equals(rs)) {
                flag = false;
            } else {
                System.out.println(rs);
            }
        } while (false == flag);

        //资源释放
        socketPrintStream.close();
        socketBufferedReader.close();
    }

}
