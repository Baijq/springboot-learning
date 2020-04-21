package com.biubiu.shiro.controller;

import com.biubiu.shiro.util.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 验证码服务
 *
 * @author baijq
 */
@Controller
public class CaptchaController {

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CaptchaUtil.generateCodeAndPic(120, 40, 30);
        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("code", codeMap.get("code").toString());

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);

        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
        sos.close();
    }

    @PostMapping("/validateCaptcha")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码，防止乱码
        response.setCharacterEncoding("UTF-8");

        String code = request.getParameter("code");
        // 验证验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
            if (code.equalsIgnoreCase(sessionCode)) {
                response.getWriter().println("验证通过！");
            } else {
                response.getWriter().println("验证失败！");
            }
        } else {
            response.getWriter().println("验证失败！");
        }
    }
}
