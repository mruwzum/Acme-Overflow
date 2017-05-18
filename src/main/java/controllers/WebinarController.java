package controllers;


import domain.LearningMaterial;
import domain.Webinar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.TeacherService;
import services.WebinarService;


import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("/webinar")
public class WebinarController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private WebinarService webinarService;
   @Autowired
   private TeacherService teacherService;

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

      result = new ModelAndView("webinar/edit");
      result.addObject("webinar", webinar);
        result.addObject("message", message);

        return result;

    }

   @RequestMapping(value = "/listAn", method = RequestMethod.GET)
   public ModelAndView webinarListAn() {

      ModelAndView result;
      Collection<Webinar> webinars;

      webinars = webinarService.findAll();


      result = new ModelAndView("webinar/list");
      result.addObject("webinars", webinars);
      result.addObject("requestURI", "webinar/list.do");

      return result;
   }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView webinarList() {

        ModelAndView result;
       Collection<Webinar> webinars;

       webinars = webinarService.findAll();
       Collection<Webinar> mines = new HashSet<>();

       for (Webinar w : webinars) {
          if (w.getOwner().equals(teacherService.findByPrincipal())) {
             mines.add(w);
          }
       }
       result = new ModelAndView("webinar/list");
       result.addObject("webinars", webinars);
       result.addObject("mywebinars", mines);
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
         if (webinar.getStartDate().after(new Date(System.currentTimeMillis() - 30 * 24 * 60L)) && webinar.getStartDate().getYear() == 117) {
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
    public ModelAndView create() {
        ModelAndView result;
       //TODO gordito crear webminars
       Webinar webinar = webinarService.create();
       webinar.setOwner(teacherService.findByPrincipal());

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
       result.addObject("categories", webinar.getCategories());

        return result;
    }

   @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Webinar webinar, BindingResult binding) {
        ModelAndView result;
//        if (!binding.hasErrors()) {
//           result = createEditModelAndView(webinar);
//        } else {
//            try {
      //TODO: no se guarda por los learning
               webinarService.save(webinar);
      result = new ModelAndView("redirect:listAn.do");
//            } catch (Throwable oops) {
//               result = createEditModelAndView(webinar, "webinar.commit.error");
//            }
//        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Webinar webinar) {
        ModelAndView result;
        try {
           webinarService.delete(webinar);
           result = new ModelAndView("redirect:listAn.do");
        } catch (Throwable oops) {
           result = createEditModelAndView(webinar, "general.commit.error");
        }

        return result;
    }

   @RequestMapping(value = "/view", method = RequestMethod.GET)
   public ModelAndView webinarView(@RequestParam int webinarId) {

      ModelAndView result;
      Webinar webinar = webinarService.findOne(webinarId);


      result = new ModelAndView("webinar/view");
      result.addObject("name", webinar.getName());
      result.addObject("description", webinar.getDescription());
      result.addObject("startDate", webinar.getStartDate());
      result.addObject("price", webinar.getPrice());
      result.addObject("categories", webinar.getCategories());
      result.addObject("webinarId", webinar.getId());
      result.addObject("requestURI", "webinar/view.do");

      return result;
   }
}
