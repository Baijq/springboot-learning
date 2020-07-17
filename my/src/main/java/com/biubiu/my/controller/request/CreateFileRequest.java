package com.biubiu.my.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * CreateFileRequest
 *
 * @author wbbaijq
 */
@ApiModel
@Data
public class CreateFileRequest implements Serializable {

    @ApiModelProperty(value = "文件名", dataType = "String", example = "a")
    private String fileName;

    @ApiModelProperty(value = "后缀名", dataType = "String", example = ".txt")
    private String subFix;

    @ApiModelProperty(value = "路径名", dataType = "String", example = "D:\\test")
    private String path;

    @ApiModelProperty(value = "内容", dataType = "String", example = "啊！美丽的一天又来了，让坚持成为品质，让优秀成为习惯！")
    private String content;
}

