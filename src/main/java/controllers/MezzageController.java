package controllers;


import domain.Actor;
import domain.Mezzage;
import domain.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MezzageService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/mezzage")
public class MezzageController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private MezzageService mezzageService;
    @Autowired
    private ActorService actorService;


    //Constructors----------------------------------------------

    public MezzageController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(Mezzage mezzage) {
        ModelAndView result;

        result = createEditModelAndView(mezzage, null);

        return result;
    }


    protected static ModelAndView createEditModelAndView(Mezzage mezzage, String message) {
        ModelAndView result;


        result = new ModelAndView("mezzage/edit");
        result.addObject("mezzage", mezzage);
        result.addObject("message", message);


        return result;

    }


    //Create Method -----------------------------------------------------------


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView messagesList() {

        ModelAndView result;
        Collection<Mezzage> mezzages;

        mezzages = mezzageService.findAll();
        result = new ModelAndView("mezzage/list");
        result.addObject("mezzages", mezzages);
        result.addObject("requestURI", "mezzage/list.do");

        return result;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Mezzage mezzage = mezzageService.create();
        mezzage.setSender(actorService.findByPrincipal());
        Collection<Actor> users = actorService.findAll();

        result = createEditModelAndView(mezzage);
        result.addObject("users", users);
        return result;

    }


    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int mezzageId) {
        ModelAndView result;
        Mezzage mezzage;

        mezzage = mezzageService.findOne(mezzageId);
        Collection<Actor> users = actorService.findAll();

        Assert.notNull(mezzage);
        result = createEditModelAndView(mezzage);
        result.addObject("users", users);

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Mezzage mezzage, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(mezzage);
        } else {
            try {
                mezzageService.save(mezzage);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(mezzage, "general.commit.error");
            }
        }
        return result;
    }

    //        @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
//    public ModelAndView delete(@Valid int messageId){
//        ModelAndView result;
//        try{
//            Mezzage mezzage = messageService.findOne(messageId);
//            messageService.delete(mezzage);
//            result=new ModelAndView("redirect:list.do");
//        }catch(Throwable oops){
//            Mezzage mezzage = messageService.findOne(messageId);
//            result= createEditModelAndView(mezzage, "general.commit.error");
//        }
//
//        return result;
//    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete2(@RequestParam Mezzage mezzage) {
        ModelAndView result;
        try {
            mezzage.getFolder().getMezzages().remove(mezzage);
            mezzageService.delete(mezzage);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {

            result = createEditModelAndView(mezzage, "general.commit.error");
        }

        return result;
    }


    @RequestMapping(value = "/send")
    public ModelAndView sendMessage(@RequestParam String recipient, String subject, String body, String priority) {
        ModelAndView res = new ModelAndView("mezzage/text");
        String replacerecipient = recipient.replaceAll(",", "");
        String replacesubject = subject.replaceAll(",", "");
        String replacebody = body.replaceAll(",", "");
        String replacepriority = priority.replaceAll(",", "");

        try {
            Actor recipient2 = actorService.findByName(replacerecipient);
            Priority priority1 = Priority.valueOf(replacepriority);
            @SuppressWarnings("unused")
            Mezzage mezzage = actorService.textMessage(replacesubject, replacebody, recipient2, priority1);
        } catch (IllegalArgumentException e) {
            String texto1 = "";
            res = new ModelAndView("sponsor/text");
            res.addObject("texto1", texto1);
        }

        return res;
    }


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView messageView(@RequestParam int messageId) {

        ModelAndView result;
        Mezzage mezzage = mezzageService.findOne(messageId);

        result = new ModelAndView("mezzage/view");
        result.addObject("subject", mezzage.getSubject());
        result.addObject("body", mezzage.getBody());
        result.addObject("sendDate", mezzage.getSendDate());
        result.addObject("sender", mezzage.getSender());
        result.addObject("priority", mezzage.getPriority());
        result.addObject("mezzageId", mezzage.getId());
        result.addObject("requestURI", "mezzage/view.do");

        return result;
    }

}
