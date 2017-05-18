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
import java.util.*;


@Controller
@RequestMapping("/webinar")
public class WebinarController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private WebinarService webinarService;


    //Constructors----------------------------------------------

    public WebinarController() {
        super();
    }

   protected static ModelAndView createEditModelAndView(Webinar webinar) {
        ModelAndView result;

      result = createEditModelAndView(webinar, null);

        return result;
    }


    //Create Method -----------------------------------------------------------

   protected static ModelAndView createEditModelAndView(Webinar webinar, String message) {
        ModelAndView result;

      result = new ModelAndView("webinar/edit");
      result.addObject("webinar", webinar);
        result.addObject("message", message);

        return result;

    }

   protected static ModelAndView createEditModelAndView2(Webinar webinar) {
        ModelAndView result;

      result = createEditModelAndView2(webinar, null);

        return result;
    }
    // Edition ---------------------------------------------------------

   protected static ModelAndView createEditModelAndView2(Webinar webinar, String message) {
        ModelAndView result;

      result = new ModelAndView("webinar/editLess");
      result.addObject("webinar", webinar);
        result.addObject("message", message);

        return result;

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView webinarList() {

        ModelAndView result;
       Collection<Webinar> webinars;

       webinars = webinarService.findAll();
       result = new ModelAndView("webinar/list");
       result.addObject("webinars", webinars);
       result.addObject("requestURI", "webinar/list.do");

        return result;
    }


   @RequestMapping(value = "/listIncoming", method = RequestMethod.GET)
   public ModelAndView webinarListIncoming() {

      ModelAndView result;
      Collection<Webinar> webinars;
      List<Webinar> res = new ArrayList<>();
      webinars = webinarService.findAll();

      for (Webinar webinar : webinars) {
         if (webinar.getStartDate().after(new Date(System.currentTimeMillis() - 100 * 30 * 24 * 60 * 60L))) {
            res.add(webinar);
         }
      }
      Collections.sort(res, new Comparator<Webinar>() {
         public int compare(Webinar m1, Webinar m2) {
            return m2.getStartDate().toString().compareTo(m1.getStartDate().toString());
         }
      });

      result = new ModelAndView("webinar/list");
      result.addObject("webinars", res);
      result.addObject("requestURI", "webinar/listIncoming.do");

      return result;
   }
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int id) {

        ModelAndView result;

       Webinar webinar = webinarService.create();

       result = createEditModelAndView(webinar);

        return result;

    }

    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int webinarId) {
        ModelAndView result;
       Webinar webinar;

       webinar = webinarService.findOne(webinarId);
       Assert.notNull(webinar);
       result = createEditModelAndView(webinar);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Webinar webinar, BindingResult binding) {
        ModelAndView result;
        if (!binding.hasErrors()) {
           result = createEditModelAndView(webinar);
        } else {
            try {
               webinarService.save(webinar);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
               result = createEditModelAndView(webinar, "webinar.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Webinar webinar) {
        ModelAndView result;
        try {
           webinarService.delete(webinar);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
           result = createEditModelAndView(webinar, "webinar.commit.error");
        }

        return result;
    }

}
