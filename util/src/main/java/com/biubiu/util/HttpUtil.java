package com.biubiu.util;

import net.sf.json.JSONObject;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * HttpUtil
 *
 * @author biubiu
 */
public class HttpUtil {

    /**
     * post 请求
     *
     * @param address 请求地址
     * @param param   请求参数
     * @return Response
     */
    public static String post(String address, String param) {
        try {
            URL url = new URL(address);
            if (address.toLowerCase().contains("https")) {
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //POST请求
            conn.setRequestMethod("POST");
            //不使用缓存
            conn.setUseCaches(false);
            //允许读入写出
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //连接超时和读取超时
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            //链接并发送
            conn.connect();

            //请求参数写入out流
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            out.write(param);
            out.flush();
            out.close();

            //获取响应到in流
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line;
            while (null != (line = in.readLine())) {
                sb.append(line);
            }
            in.close();
            //关闭连接
            conn.disconnect();
            return sb.toString();
        } catch (Exception e) {
            String errMsg = "Http POST 请求失败！\n" + "【请求地址】" + address + "\n【请求参数】" + param + "\n【异常原因】" + e;
            e.printStackTrace();
            return errMsg;
        }
    }

    public static String get(String address) {
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //连接超时和读取超时
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.connect();
            //获取响应到in流
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line;
            while (null != (line = in.readLine())) {
                sb.append(line);
            }
            in.close();
            //关闭连接
            conn.disconnect();
            return sb.toString();

        } catch (Exception e) {
            String errMsg = "Http GET 请求失败！\n" + "【请求地址】" + address + "\n【异常原因】" + e.getMessage();
            e.printStackTrace();
            return errMsg;
        }
    }

    static class HttpPost {

        private final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        private static void trustAllHosts() {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }
            }};
            // Install the all-trusting trust manager
            try {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static String https(String url, Map<String, String> params) throws Exception {
            // 构建请求参数
            String result = "";
            PrintWriter out = null;
            BufferedReader in = null;

            String sendString = "";
            JSONObject json = JSONObject.fromObject(params);
            System.out.println("发送报文:" + json.toString());
            sendString = json.toString();

            System.out.println("ERP连接:" + url);
            System.out.println("发送给ERP信息:" + sendString);

            try {
                trustAllHosts();
                URL url2 = new URL(url);

                HttpsURLConnection urlCon = (HttpsURLConnection) url2.openConnection();
                urlCon.setHostnameVerifier(DO_NOT_VERIFY);
                urlCon.setDoOutput(true);
                urlCon.setDoInput(true);
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-type", "application/json;charset=UTF-8");
                // 发送POST请求必须设置如下两行
                urlCon.setDoOutput(true);
                urlCon.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                OutputStream os = urlCon.getOutputStream();
                //参数是键值队  , 不以"?"开始
                os.write(sendString.getBytes());
                //os.write("googleTokenKey=&username=admin&password=5df5c29ae86331e1b5b526ad90d767e4".getBytes());
                os.flush();
                // 发送请求参数
                //out.print(a);
                // flush输出流的缓冲
                //out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(urlCon.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {// 使用finally块来关闭输出流、输入流
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return result;
        }

        public static String http(String url, Map<String, String> reqMap) throws Exception {
            URL u = null;
            HttpURLConnection con = null;
            // 构建请求参数
            //StringBuffer sb = new StringBuffer();
            String sendString = "";
            //String tradeCode = params.get(ParamsConfig.keyTradeCode);
            JSONObject json = JSONObject.fromObject(reqMap);
            System.out.println("发送报文:" + json.toString());
            sendString = json.toString();
        /*if(tradeCode.equals(TradeCode.TRANS_WXAPP_PAY) || tradeCode.equals(TradeCode.TRANS_APP_QUERY)
                || tradeCode.equals(TradeCode.TRANS_TYPE_COLLECT) || tradeCode.equals(TradeCode.TRANS_TYPE_QUERY_COLLECT_TXN)){
            JSONObject json = JSONObject.fromObject(params);
            System.out.println("发送报文:"+json.toString());
            sendString = json.toString();
        } else {
            if (params != null) {

                for (Entry<String, String> e : params.entrySet()) {

                    sb.append(e.getKey());

                    sb.append("=");

                    sb.append(e.getValue());

                    sb.append("&");

                }
                sendString = sb.substring(0, sb.length() - 1);
            }
        }*/
            System.out.println("ERP连接:" + url);
            System.out.println("发送给ERP信息:" + sendString);
            // 尝试发送请求
            try {
                u = new URL(url);

                con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setUseCaches(false);
//            con.setConnectTimeout(300*1000);
//            con.setReadTimeout(300*1000);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                osw.write(sendString);
                osw.flush();
                osw.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("与服务器连接发生错误:" + e.getMessage());
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            // 读取返回内容
            StringBuffer buffer = new StringBuffer();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(con
                        .getInputStream(), "UTF-8"));
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("从服务器获取数据失败:" + e.getMessage());
            }
            return buffer.toString();
        }
    }



}
