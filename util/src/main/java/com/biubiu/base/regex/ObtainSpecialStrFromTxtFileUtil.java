package com.biubiu.base.regex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * @description 正则测试
 * @date 2019/6/21
 */
public class ObtainSpecialStrFromTxtFileUtil {

    private final static String URL_REGEX = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

    private ObtainSpecialStrFromTxtFileUtil() {
    }

    public static ObtainSpecialStrFromTxtFileUtil getInstance() {
        return new ObtainSpecialStrFromTxtFileUtil();
    }

    private String readFile(String pathName) {

        StringBuffer sb = new StringBuffer();
        //The Java 7 try-with-resources syntax (Automatic Resource Management) is nice
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\r\n");
            }
            System.out.println("读取文件完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void writeFile(String pathName, String data) {
        try {
            //文件不存在的话新建，存在覆盖
            File file = new File(pathName);
            file.createNewFile();
            //The Java 7 try-with-resources syntax (Automatic Resource Management) is nice
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(data);
                bw.flush();
                System.out.println("文件写入完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String filterSpecialStr(String regex, String data) {
        StringBuffer sb = new StringBuffer();
        //正则匹配
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(data);
        while (matcher.find()) {
            sb.append(matcher.group() + "\r\n");
        }
        return sb.toString();
    }

    public void getUrlFromFile(String sourceFilePath, String outFilePath) {
        String srcData = this.readFile(sourceFilePath);
        String data = this.filterSpecialStr(URL_REGEX, srcData);
        this.writeFile(outFilePath, data);
    }

    //测试
    public static void main(String[] args) {
        String pathIn = "";
        String pathOut = "";

        ObtainSpecialStrFromTxtFileUtil.getInstance().getUrlFromFile(pathIn, pathOut);

    }

}
