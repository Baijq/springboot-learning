package com.biubiu.okhttp3;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ParamUtils
 *
 * @author wbbaijq
 */
public class ParamUtils {

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * eg：username=tom&password=123456
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, String> map) throws UnsupportedEncodingException {
        List<String> keys = new ArrayList<String>(map.keySet());
        String rs = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = map.get(key);
            value = URLEncoder.encode(value, "UTF-8");
            //最后一个 & 不包括
            if (i == keys.size() - 1) {
                rs += key + "=" + value;
            } else {
                rs += key + "=" + value + "&";
            }
        }
        return rs;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        map.put("name", "张三");
        map.put("password", "1234556");
        map.put("age", "20");
        System.out.println(getUrlParamsByMap(map));

    }
}
