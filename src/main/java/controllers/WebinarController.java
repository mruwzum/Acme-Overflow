package controllers;


import domain.Webinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.WebinarService;


import javax.validation.Valid;
import java.util.Collection;


@Controller
@RequestMapping("/webinar")
public class WebinarController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private WebinarService werbinarService;


    //Constructors----------------------------------------------

    public WebinarController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(Webinar werbinar) {
        ModelAndView result;

        result = createEditModelAndView(werbinar, null);

        return result;
    }


    //Create Method -----------------------------------------------------------

    protected static ModelAndView createEditModelAndView(Webinar werbinar, String message) {
        ModelAndView result;

        result = new ModelAndView("werbinar/edit");
        result.addObject("werbinar", werbinar);
        result.addObject("message", message);

        return result;

    }

    protected static ModelAndView createEditModelAndView2(Webinar werbinar) {
        ModelAndView result;

        result = createEditModelAndView2(werbinar, null);

        return result;
    }
    // Edition ---------------------------------------------------------

    protected static ModelAndView createEditModelAndView2(Webinar werbinar, String message) {
        ModelAndView result;

        result = new ModelAndView("werbinar/editLess");
        result.addObject("werbinar", werbinar);
        result.addObject("message", message);

        return result;

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView werbinarList() {

        ModelAndView result;
        Collection<Webinar> werbinars;

        werbinars = werbinarService.findAll();
        result = new ModelAndView("werbinar/list");
        result.addObject("werbinars", werbinars);
        result.addObject("requestURI", "werbinar/list.do");

        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int id) {

        ModelAndView result;

        Webinar werbinar = werbinarService.create();

        result = createEditModelAndView(werbinar);

        return result;

    }

    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int werbinarId) {
        ModelAndView result;
        Webinar werbinar;

        werbinar = werbinarService.findOne(werbinarId);
        Assert.notNull(werbinar);
        result = createEditModelAndView(werbinar);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Webinar werbinar, BindingResult binding) {
        ModelAndView result;
        if (!binding.hasErrors()) {
            result = createEditModelAndView(werbinar);
        } else {
            try {
                werbinarService.save(werbinar);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(werbinar, "werbinar.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Webinar werbinar) {
        ModelAndView result;
        try {
            werbinarService.delete(werbinar);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(werbinar, "werbinar.commit.error");
        }

        return result;
    }

}
