package com.biubiu.base.list2tree;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        //问候语QWQ
        System.out.println("[START]");
        //获取菜单信息
        List<MenuInfo> menuInfos = queryMenuInfo();
        //List转成Tree结构
        List<MenuData> menuData = List2Tree.list2TreeV1(menuInfos);
        //查看结果
        System.out.println("转换结果>>：" + JSONObject.toJSONString(menuData));
        //结束语
        System.out.println("[END]");
    }

    private static List<MenuInfo> queryMenuInfo() {
        //搞一个菜单信息的集合
        List<MenuInfo> menuInfos = new ArrayList<MenuInfo>() {
            {
                add(MenuInfo.builder().menuId(1).menuName("菜单1").buttonId(11).buttonName("按键1").width("10").height("10").build());
                add(MenuInfo.builder().menuId(1).menuName("菜单1").buttonId(11).buttonName("按键1").width("100").height("100").build());
                add(MenuInfo.builder().menuId(1).menuName("菜单1").buttonId(12).buttonName("按键2").width("100").height("100").build());
                add(MenuInfo.builder().menuId(1).menuName("菜单1").buttonId(12).buttonName("按键2").width("20").height("20").build());
                add(MenuInfo.builder().menuId(1).menuName("菜单1").buttonId(12).buttonName("按键2").width("10").height("10").build());
                add(MenuInfo.builder().menuId(1).menuName("菜单1").buttonId(12).buttonName("按键2").width("30").height("30").build());

                add(MenuInfo.builder().menuId(2).menuName("菜单2").buttonId(21).buttonName("按键3").width("10").height("10").build());
                add(MenuInfo.builder().menuId(2).menuName("菜单2").buttonId(21).buttonName("按键3").width("100").height("100").build());
                add(MenuInfo.builder().menuId(2).menuName("菜单2").buttonId(21).buttonName("按键3").width("20").height("20").build());
                add(MenuInfo.builder().menuId(2).menuName("菜单2").buttonId(21).buttonName("按键3").width("30").height("30").build());
            }
        };

        return menuInfos;
    }

}
