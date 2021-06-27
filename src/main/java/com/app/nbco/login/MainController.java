package com.app.nbco.login;


import com.app.nbco.user.entity.User;
import com.app.nbco.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import util.UserUtil;


@EnableAutoConfiguration
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public ModelAndView home() {

        System.out.println("index");
        ModelAndView model = new ModelAndView();
        String authUser = UserUtil.getAuthenticatedUser();
        System.out.println("authUser" + authUser);
        try {
            if (!authUser.equals("anonymousUser")) {
                User user = userService.findByUsername(authUser);
                System.out.println("user " + user);
                if (UserUtil.hasRole("ADMIN")) {
                    System.out.println("ADMIN  " + user);
                    model.setViewName("redirect:/api/user/list");

                } else {
                    System.out.println("USER " + user);
                    model.setViewName("redirect:/api/customer/list");

                }
            } else {
                model.setViewName("index");
            }
        } catch (Exception e) {
            model.setViewName("index");

        }
        return model;
    }
}
