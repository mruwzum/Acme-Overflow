package controllers;


import domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.TeacherService;


import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private TeacherService teacherService;


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

        result = new ModelAndView("teacher/editLess");
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
    public ModelAndView create(@RequestParam int id) {

        ModelAndView result;

        Teacher teacher = teacherService.create();

        result = createEditModelAndView(teacher);

        return result;

    }

    // Ancillary methods ------------------------------------------------


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
        if (!binding.hasErrors()) {
            result = createEditModelAndView(teacher);
        } else {
            try {
                teacherService.save(teacher);
                result = new ModelAndView("redirect:list.do");
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

}
