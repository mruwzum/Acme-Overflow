package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;

import java.util.Collection;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class DashboardController extends AbstractController {

    @Autowired
    private AdministratorService administratorService;


    public DashboardController() {
        super();
    }

    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard() {
        ModelAndView res;

        //DASHBOARD C


        res = new ModelAndView("administrator/dashboard");


        return res;
    }
}
