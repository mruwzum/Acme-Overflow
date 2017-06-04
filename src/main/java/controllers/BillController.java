/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package controllers;

import domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BillService;
import services.UserService;

import java.util.Collection;

/**
 * Created by mruwzum on 19/12/16.
 */
@Controller
@RequestMapping("/bill")
public class BillController extends AbstractController {

    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;

    public BillController() {
        super();
    }

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Bill> aux = userService.findByPrincipal().getBills();
        result = new ModelAndView("bill/list");
        result.addObject("bills", aux);
        result.addObject("requestURI", "bill/list.do");
        return result;


    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int billId) {

        Bill bill = billService.findOne(billId);
        ModelAndView res;


        res = new ModelAndView("bill/view");
        res.addObject("value", bill.getValue());
        res.addObject("number", bill.getNumber());
        res.addObject("webinar", bill.getWebinar());
        res.addObject("owner", bill.getOwner());

        return res;

    }

}
