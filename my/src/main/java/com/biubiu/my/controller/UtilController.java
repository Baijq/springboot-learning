package com.biubiu.my.controller;

import com.biubiu.my.controller.request.CreateFileRequest;
import com.biubiu.my.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UtilController
 *
 * @author wbbaijq
 */
@Api("工具类")
@RestController
@RequestMapping("/util")
public class UtilController {

    @ApiOperation("创建文件")
    @GetMapping("/createFile")
    public String util(CreateFileRequest request) {
        FileUtil.createFile(request.getFileName(), request.getSubFix(), request.getPath(), request.getContent());
        return "success";
    }
}
