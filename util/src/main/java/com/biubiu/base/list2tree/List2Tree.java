package com.biubiu.base.list2tree;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class List2Tree {

    public static List<MenuData> list2TreeV1(List<MenuInfo> menuInfos) {

        //1. menuInfos根据menuId去重得到menuLst
        Map<Integer, MenuInfo> uniqueMenuIdMap = new HashMap<>();
        menuInfos.forEach(menuInfo -> {
            if (!uniqueMenuIdMap.containsKey(menuInfo.getMenuId())) {
                uniqueMenuIdMap.put(menuInfo.getMenuId(), menuInfo);
            }
        });
        ArrayList<MenuInfo> menuLst = new ArrayList<>(uniqueMenuIdMap.values());

        //2. menuInfos根据buttonId去重得到buttonList
        Map<Integer, MenuInfo> uniqueButtonIdMap = new HashMap<>();
        menuInfos.forEach(menuInfo -> {
            if (!uniqueButtonIdMap.containsKey(menuInfo.getButtonId())) {
                uniqueButtonIdMap.put(menuInfo.getButtonId(), menuInfo);
            }
        });
        ArrayList<MenuInfo> buttonList = new ArrayList<>(uniqueButtonIdMap.values());

        //3. 树形组装
        //3.1 组装menu(根据去重后的menuList)
        List<MenuData> menuData = menuLst.stream().map(m -> MenuData.builder()
                .menuId(String.valueOf(m.getMenuId()))
                .menuName(m.getMenuName())
                //3.2 组装button(根据去重后的buttonList)
                .buttons(buildButtons(m, buttonList, menuInfos))
                .build()).collect(Collectors.toList());

        return menuData;
    }

    private static List<Button> buildButtons(MenuInfo m, List<MenuInfo> buttonList, List<MenuInfo> menuInfos) {
        return buttonList.stream()
                .filter(menuInfo -> m.getMenuId().equals(menuInfo.getMenuId()))
                .map(menuInfo -> Button.builder()
                        .buttonId(menuInfo.getButtonId())
                        .buttonName(menuInfo.getButtonName())
                        //3.3 组装样式buttonStyles(根据menuId,buttonId,menuInfos)
                        .buttonStyles(buildButtonStyle(menuInfo.getMenuId(), menuInfo.getButtonId(), menuInfos))
                        .build()).collect(Collectors.toList());
    }

    private static List<ButtonStyle> buildButtonStyle(Integer menuId, Integer buttonId, List<MenuInfo> menuInfos) {
        return menuInfos.stream()
                .filter(menuInfo -> menuId.equals(menuInfo.getMenuId()) && buttonId.equals(menuInfo.getButtonId()))
                .map(menuInfo -> ButtonStyle.builder()
                        .height(menuInfo.getHeight())
                        .width(menuInfo.getWidth())
                        .build()).collect(Collectors.toList());
    }

}
