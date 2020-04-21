package com.biubiu.util;

/**
 * Main
 *
 * @author biubiu
 */
public class Main {

    public static void main(String[] args) {

        String url = "https://mobileapi.centanet.com/v6/java/json/reply/CityUrlAddressList?version=v6.2";

        String s = HttpUtil.get(url);

        System.out.println(s);
    }
}
