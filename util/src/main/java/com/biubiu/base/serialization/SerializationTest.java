package com.biubiu.base.serialization;

import java.io.*;

public class SerializationTest {

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo("张三", "admin123");
        System.out.println(userInfo);
        //序列化
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("C:\\Users\\017220\\Desktop\\userInfo.out")));
            os.writeObject(userInfo);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //反序列化
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("C:\\Users\\017220\\Desktop\\userInfo.out"));
            UserInfo readObject = (UserInfo) is.readObject();
            System.out.println(readObject);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
