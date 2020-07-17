package com.biubiu.my.util;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 创建文件
 *
 * @author wbbaijq
 */
public class FileUtil {

    public static void main(String[] args) {
        List<String> cityList = new ArrayList<>(Arrays.asList("bj", "tj", "cc", "cd", "cq", "cs", "dg", "dl", "gz", "hz", "nc", "nn", "nj", "sy", "sz", "wh", "zj", "zh", "xa", "km", "ganzhou", "fs", "zs", "fz", "xm", "hf", "zz", "jn", "wx", "nb", "hrb", "zq", "xy", "hn", "hq"));
        for (String city : cityList) {
            createFile(city, ".properties", "D:\\test", city);
        }
    }

    /**
     * 创建一个文件
     *
     * @param fileName 文件名称
     * @param suffix   后缀名 .properties
     * @param filePath 保存路径
     * @param content  内容
     */
    public static void createFile(String fileName, String suffix, String filePath, String content) {
        fileName = StringUtils.isEmpty(fileName) ? "a" : fileName;
        suffix = StringUtils.isEmpty(suffix) ? ".txt" : suffix;
        filePath = StringUtils.isEmpty(filePath) ? "C:\\newFile" : filePath;
        //1. 文件夹路径
        File myPath = new File(filePath);
        if (!myPath.exists()) {
            myPath.mkdirs();
        }
        // 2. 文件名称
        fileName = fileName + suffix;
        try {
            // FileWriter 就是牛，如果文件名 的文件不存在，先创建再读写;存在的话直接追加写,关键字true表示追加
            FileWriter fileWriter = new FileWriter(filePath + "\\" + fileName, true);
            if (!StringUtils.isEmpty(content)) {
                fileWriter.write(content);
            }
            // 关闭写文件,每次仅仅写一行数据，因为一个读文件中仅仅一个唯一的od
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
