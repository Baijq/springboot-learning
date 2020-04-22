package com.biubiu.base.list2tree;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Button {

    private Integer buttonId;

    private String buttonName;

    private List<ButtonStyle> buttonStyles;
}
