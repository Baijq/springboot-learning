package com.biubiu.mybatismapper.controller;

import com.biubiu.mybatismapper.entity.User;
import com.biubiu.mybatismapper.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * PageController
 *
 * @author biubiu
 */
@Controller
public class PageController {

    @Resource
    private UserService userService;

    @GetMapping("/index")
    public String test(Model model,
                       @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 2);
        List<User> users = userService.queryAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        model.addAttribute("pageInfo", userPageInfo);
        return "index";
    }
}
