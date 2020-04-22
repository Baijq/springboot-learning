package com.biubiu.base.list2tree;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuInfo {

    private Integer menuId;

    private String menuName;

    private Integer buttonId;

    private String buttonName;

    private String width;

    private String height;

}
