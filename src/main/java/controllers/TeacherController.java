/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package controllers;


import domain.Bill;
import domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.TeacherService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActorService actorService;

    //Constructors----------------------------------------------

    public TeacherController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(Teacher teacher) {
        ModelAndView result;

        result = createEditModelAndView(teacher, null);

        return result;
    }


    //Create Method -----------------------------------------------------------

    protected static ModelAndView createEditModelAndView(Teacher teacher, String message) {
        ModelAndView result;

        result = new ModelAndView("teacher/edit");
        result.addObject("teacher", teacher);
        result.addObject("message", message);

        return result;

    }

    protected static ModelAndView createEditModelAndView2(Teacher teacher) {
        ModelAndView result;

        result = createEditModelAndView2(teacher, null);

        return result;
    }
    // Edition ---------------------------------------------------------

    protected static ModelAndView createEditModelAndView2(Teacher teacher, String message) {
        ModelAndView result;

        result = new ModelAndView("teacher/register");
        result.addObject("teacher", teacher);
        result.addObject("message", message);

        return result;

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView teacherList() {

        ModelAndView result;
        Collection<Teacher> teachers;

        teachers = teacherService.findAll();
        result = new ModelAndView("teacher/list");
        result.addObject("teachers", teachers);
        result.addObject("requestURI", "teacher/list.do");

        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Teacher teacher = teacherService.create();

        result = createEditModelAndView2(teacher);

        return result;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
    public ModelAndView register(@Valid Teacher teacher, BindingResult binding) {
        ModelAndView result;
        if (! binding.hasErrors()) {
            result = createEditModelAndView2(teacher);
        } else {
            try {
                userService.registerAsTeacher(teacher);
                result = new ModelAndView("welcome/index");
            } catch (Throwable oops) {
                result = createEditModelAndView2(teacher, "general.commit.error");
            }
        }
        return result;
    }

    // Ancillary methods ------------------------------------------------
    @RequestMapping(value = "/editp", method = RequestMethod.GET)
    public ModelAndView editp() {
        ModelAndView result;
        Teacher teacher;

        teacher = teacherService.findByPrincipal();
        Assert.notNull(teacher);

        result = createEditModelAndView(teacher);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int teacherId) {
        ModelAndView result;
        Teacher teacher;

        teacher = teacherService.findOne(teacherId);
        Assert.notNull(teacher);
        result = createEditModelAndView(teacher);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Teacher teacher, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(teacher);
        } else {
            try {
                teacherService.save(teacher);
                teacher.getCurricula().setApprobed(false);
                teacher.getCurricula().setOwner(teacherService.findByPrincipal());

                result = new ModelAndView("user/success");
            } catch (Throwable oops) {
                result = createEditModelAndView(teacher, "teacher.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Teacher teacher) {
        ModelAndView result;
        try {
            teacherService.delete(teacher);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(teacher, "teacher.commit.error");
        }

        return result;
    }


    @RequestMapping(value = "/bill", method = RequestMethod.GET)
    public ModelAndView bills() {

        ModelAndView res;

        Collection<Bill> bills = teacherService.myBills(teacherService.findByPrincipal());
        String total = teacherService.totalEarn(teacherService.findByPrincipal());

        res = new ModelAndView("bill/list");
        res.addObject("bills", bills);
        res.addObject("total", total);
        return res;


    }

}
