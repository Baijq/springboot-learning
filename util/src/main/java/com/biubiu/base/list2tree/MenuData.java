package com.biubiu.base.list2tree;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuData {

    private String menuId;

    private String menuName;

    private List<Button> buttons;

}
