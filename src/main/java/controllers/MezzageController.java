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
import services.ActorService;
import services.MezzageService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;

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
        //mezzage.setSender(actorService.findByPrincipal());
        mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
        mezzage.setSendDate(new Date(System.currentTimeMillis()-100));
//        Collection<Actor> users = actorService.findAll();

        result = createEditModelAndView(mezzage);
//        result.addObject("users", users);
        return result;

    }


    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int mezzageId) {
        ModelAndView result;
        Mezzage mezzage;

        mezzage = mezzageService.findOne(mezzageId);
//        Collection<Actor> users = actorService.findAll();

        Assert.notNull(mezzage);
        result = createEditModelAndView(mezzage);
//        result.addObject("users", users);

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Mezzage mezzage, BindingResult binding) {
        ModelAndView result;
//        if (binding.hasErrors()) {
//            result = createEditModelAndView(mezzage);
//        } else {
//            try {

        mezzage.setSender(actorService.findByPrincipal());
        mezzage.setSenderEmail(actorService.findByPrincipal().getEmail());
        mezzage.setSendDate(new Date(System.currentTimeMillis()-100));
       actorService.textMessage(mezzage);

       result = new ModelAndView("administrator/action-1");
//            } catch (Throwable oops) {
//                result = createEditModelAndView(mezzage, "general.commit.error");
//                Collection<Actor> users = actorService.findAll();
//                result.addObject("users", users);
//
//            }
//        }
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
    public ModelAndView delete2(@Valid int mezzageId) {
        ModelAndView result;
        Mezzage mezzage = mezzageService.findOne(mezzageId);
//        try {


        if (mezzage.getFolder().getName().equals("Trashbox")) {

            mezzage.setSender(null);
            mezzage.setReceiver(null);
            mezzage.setFolder(null);
                mezzageService.delete(mezzage);
        } else {
            Folder f = actorService.folderByName(actorService.findByPrincipal(), "Trashbox");
            mezzage.setFolder(f);
            mezzageService.save(mezzage);
            // f.getMezzages().add(mezzage);
        }


            result = new ModelAndView("administrator/action-1");
//        } catch (Throwable oops) {
//
//            result = createEditModelAndView(mezzage, "general.commit.error");
//        }

        return result;
    }


//    @RequestMapping(value = "/send")
//    public ModelAndView sendMessage(@RequestParam String recipient, String subject, String body, String priority) {
//        ModelAndView res = new ModelAndView("mezzage/text");
//        String replacerecipient = recipient.replaceAll(",", "");
//        String replacesubject = subject.replaceAll(",", "");
//        String replacebody = body.replaceAll(",", "");
//        String replacepriority = priority.replaceAll(",", "");
//
//        try {
//            Actor recipient2 = actorService.findByName(replacerecipient);
//            Priority priority1 = Priority.valueOf(replacepriority);
//            @SuppressWarnings("unused")
//            Mezzage mezzage = actorService.textMessage(replacesubject, replacebody, recipient2, priority1);
//        } catch (IllegalArgumentException e) {
//            String texto1 = "";
//            res = new ModelAndView("sponsor/text");
//            res.addObject("texto1", texto1);
//        }
//
//        return res;
//    }


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView messageView(@RequestParam int mezzageId) {

        ModelAndView result;
        Mezzage mezzage = mezzageService.findOne(mezzageId);

        result = new ModelAndView("mezzage/view");
        result.addObject("subject", mezzage.getSubject());
        result.addObject("body", mezzage.getBody());
        result.addObject("sendDate", mezzage.getSendDate());
        // result.addObject("sender", mezzage.getSender());
        result.addObject("priority", mezzage.getPriority());
        result.addObject("mezzageId", mezzage.getId());
        result.addObject("requestURI", "mezzage/view.do");

        return result;
    }

    @RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "save")
    public ModelAndView broadcast(@Valid Mezzage mezzage, BindingResult binding) {
        ModelAndView result;

        Actor a = actorService.findByPrincipal();
        Folder f = actorService.folderByName(a, "Outbox");
        mezzage.setFolder(f);
        mezzage.setSendDate(new Date(System.currentTimeMillis() - 100));


        //TODO cuando broadcasteas el message, no se asigna bien a los webimensajes en el otro controller
        mezzageService.save(mezzage);


        result = new ModelAndView("administrator/action-1");

        return result;
    }

}
