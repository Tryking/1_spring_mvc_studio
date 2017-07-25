package com.migu.web;

import com.migu.domain.User;
import com.migu.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class LoginController {
    private UserService userService;
    private Log log = LogFactory.getLog(LoginController.class);

    @RequestMapping(value = "/")
    public String loginPage() {
        log.error("********进入index.html页面***********");
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand command) {
        log.error("********进入loginCheck.html页面***********");
        boolean isValidUser = userService.hasMatchUser(command.getUserName(), command.getPassword());

        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名密码不符");
        } else {
            User user = userService.findUserByUserName(command.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            // TODO: 2017/7/25  防止用户登录成功后一直刷新增加积分.
            userService.loginSuccess(user);

            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
