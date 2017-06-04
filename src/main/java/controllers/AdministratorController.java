/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package controllers;


import domain.Administrator;
import domain.Duty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Authority;
import services.ActorService;
import services.AdministratorService;
import services.DutyService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

    //Services ----------------------------------------------------------------


    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private DutyService dutyService;
    @Autowired
    private ActorService actorService;

    //Constructors----------------------------------------------

    public AdministratorController() {
        super();
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView administratorList() {

        ModelAndView result;
        Collection<Administrator> administrators;

        administrators = administratorService.findAll();
        result = new ModelAndView("administrator/list");
        result.addObject("administrators", administrators);
        result.addObject("requestURI", "administrator/list.do");

        return result;
    }


    //Create Method -----------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Administrator administrator = administratorService.create();
        result = createEditModelAndView(administrator);

        return result;

    }

    // Edition ---------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int administratorId) {
        ModelAndView result;
        Administrator administrator;

        administrator = administratorService.findOne(administratorId);
        Assert.notNull(administrator);
        result = createEditModelAndView(administrator);

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Administrator administrator, BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(administrator);
        } else {
            try {
                administratorService.save(administrator);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(administrator, "administrator.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Administrator administrator) {
        ModelAndView result;
        try {
            administratorService.delete(administrator);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(administrator, "administrator.commit.error");
        }

        return result;
    }

    // Ancillary methods ------------------------------------------------

    protected ModelAndView createEditModelAndView(Administrator administrator) {
        ModelAndView result;

        result = createEditModelAndView(administrator, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Administrator administrator, String message) {
        ModelAndView result;

        result = new ModelAndView("administrator/edit");
        result.addObject("administrator", administrator);
        result.addObject("message", message);

        return result;

    }


    @RequestMapping(value = "/editDuty", method = RequestMethod.GET)
    public ModelAndView editDutyCache() {

        ModelAndView res;
        List<Duty> searchCaches = new ArrayList<>(dutyService.findAll());
        res = new ModelAndView("duty/edit");
        res.addObject("duty", searchCaches.get(0));

        return res;

    }

    @RequestMapping(value = "/changeDuty", method = RequestMethod.POST, params = "save")
    public ModelAndView changeDuty(@Valid Duty searchCache) {

        ModelAndView res;

        Authority authority = new Authority();
        authority.setAuthority("ADMIN");

        if (actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority)) {

            dutyService.save(searchCache);
            res = new ModelAndView("user/success");

        } else {
            res = new ModelAndView("/welcome/index");

        }

        return res;


    }


}
