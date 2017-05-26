package controllers;


import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

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
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private MezzageService mezzageService;
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


    @RequestMapping(value = "/listMy", method = RequestMethod.GET)
    public ModelAndView webinarMy() {

        ModelAndView result;
        Collection<Webinar> webinars;
        Boolean bol = true;
        webinars = webinarService.myWebinars(teacherService.findByPrincipal());


        result = new ModelAndView("webinar/list");
        result.addObject("webinars", webinars);
        result.addObject("my", bol);
        result.addObject("requestURI", "webinar/listMy.do");

        return result;
    }

    @RequestMapping(value = "/listToGo", method = RequestMethod.GET)
    public ModelAndView webinarListToGo() {

        ModelAndView result;
        Collection<Webinar> res;

        res = webinarService.webinarsToGo(userService.findByPrincipal());
        result = new ModelAndView("webinar/list");
        result.addObject("webinars", res);
        result.addObject("requestURI", "webinar/listToGo.do");

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
        Collection<Webinar> webinars = webinarService.listIncoming();

        result = new ModelAndView("webinar/list");
        result.addObject("webinars", webinars);
        result.addObject("requestURI", "webinar/listIncoming.do");

        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView result;
        //TODO gordito crear webminars
        Webinar webinar = webinarService.create();
        webinar.setOwner(teacherService.findByPrincipal());

        Collection<Category> categories = categoryService.findAll();

        result = createEditModelAndView(webinar);
        result.addObject("categories", categories);

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
        result.addObject("categories", categoryService.findAll());
        webinarService.flush();

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
       Webinar copy = webinarService.create();
       copy.setBills(webinar.getBills());
       copy.setCategories(webinar.getCategories());
       copy.setComments(webinar.getComments());
       copy.setDescription(webinar.getDescription());
       copy.setEvaluations(webinar.getEvaluations());
       copy.setModules(webinar.getModules());
       copy.setName(webinar.getName());
       copy.setOwner(webinar.getOwner());
       copy.setPartakers(webinar.getPartakers());
       copy.setPicture(webinar.getPicture());
       copy.setPrice(webinar.getPrice());
       copy.setStartDate(webinar.getStartDate());
       copy.setURL(webinar.getURL());
       copy.setWebiMezzages(webinar.getWebiMezzages());
       webinar.setWebiMezzages(null);
       webinar.setPartakers(null);
       webinar.setModules(null);
       webinar.setEvaluations(null);
       webinar.setCategories(null);
       webinar.setOwner(null);
       teacherService.findByPrincipal().getWebinars().remove(webinar);
       webinarService.delete(webinar);
       webinarService.save(copy);
       teacherService.findByPrincipal().getWebinars().add(copy);
       result = new ModelAndView("redirect:listMy.do");
//            } catch (Throwable oops) {
//               result = createEditModelAndView(webinar, "webinar.commit.error");
//            }
//        }
        return result;
    }

//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    public ModelAndView delete(@RequestParam int webinarId) {
//        ModelAndView result;
////        try {
//        Webinar webinar = webinarService.findOne(webinarId);
//        webinar.getCategories().getWebinars().remove(webinar);
//        webinar.setBills(null);
//        webinar.setComments(null);
//        webinar.setEvaluations(null);
//
//        webinarService.delete(webinar);
//        result = new ModelAndView("redirect:listAn.do");
////        } catch (Throwable oops) {
////           result = createEditModelAndView(webinar, "general.commit.error");
////        }
//
//        return result;
//    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView webinarView(@RequestParam int webinarId) {
        Boolean registered = false;
        Boolean my = false;

        ModelAndView result;
        Webinar webinar = webinarService.findOne(webinarId);

        if (webinar.getPartakers().contains(actorService.findByPrincipal())) {
            registered = true;
        }

//      if(webinarService.myWebinars(teacherService.findByPrincipal()).contains(webinar)){
//          my = true;
//      }


        List<Evaluation> evaluations = new ArrayList<>(evaluationService.findAll());
        Evaluation master = evaluations.get(0);
        Collection<EvaluationQuestion> evaluationQuestions = master.getEvaluationQuestions();

        Evaluation evaluation = evaluationService.create();
        evaluation.setEvaluationQuestions(evaluationQuestions);
        evaluation.setWebinar(webinar);



        result = new ModelAndView("webinar/view");
        result.addObject("name", webinar.getName());
        result.addObject("description", webinar.getDescription());
        result.addObject("startDate", webinar.getStartDate());
        result.addObject("price", webinar.getPrice());
        result.addObject("categories", webinar.getCategories());
        result.addObject("comments", webinar.getComments());
        result.addObject("webinarId", webinar.getId());
        result.addObject("users", webinar.getPartakers());
        result.addObject("reg", registered);
        result.addObject("modules", webinar.getModules());
        result.addObject("url", webinar.getURL());
        result.addObject("picture",webinar.getPicture());
        result.addObject("questions", evaluation.getEvaluationQuestions());
//       result.addObject("my", my);
//
        result.addObject("requestURI", "webinar/view.do");

        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView apply(@RequestParam int webinarId) {
        ModelAndView result;

        Webinar webinar = webinarService.findOne(webinarId);
        User user = userService.findByPrincipal();
        Boolean op = webinarService.register(user, webinar);


        if (op.equals(false)) {
            result = new ModelAndView("user/error");
        } else {
            result = new ModelAndView("user/success");
        }

        return result;
    }


    @RequestMapping(value = "/broadcastMessage", method = RequestMethod.GET)
    public ModelAndView broadcastToWebinar(@RequestParam int webinarId) {
        ModelAndView result;

        Webinar webinar = webinarService.findOne(webinarId);

        Actor teacher = actorService.findByPrincipal();
        Mezzage mezzage = mezzageService.create();
        mezzage.setSenderEmail(teacher.getEmail());
        mezzage.setSender(teacher);
        mezzage.setWebinar(webinar);

        result = new ModelAndView("mezzage/broadcast");
        result.addObject("mezzage", mezzage);

        return result;
    }





//    @RequestMapping(value = "/unregister", method = RequestMethod.GET)
//    public ModelAndView unapply(@RequestParam int webinarId) {
//        ModelAndView result;
//
//        Webinar webinar = webinarService.findOne(webinarId);
//        User user = userService.findByPrincipal();
//        Boolean op = webinarService.unregister(user, webinar);
//
//
//        if (op.equals(false)) {
//            result = new ModelAndView("user/error");
//        } else {
//            result = new ModelAndView("user/success");
//        }
//
//        return result;
//    }
}
