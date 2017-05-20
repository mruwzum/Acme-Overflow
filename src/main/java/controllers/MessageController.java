package controllers;


import converters.ActorToStringConverter;
import domain.Actor;
import domain.Message;
import domain.Priority;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MessageService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private MessageService messageService;
    @Autowired
    private ActorService actorService;


    //Constructors----------------------------------------------

    public MessageController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(Message message) {
        ModelAndView result;

        result = createEditModelAndView(message, null);

        return result;
    }


    protected static ModelAndView createEditModelAndView(Message message2, String message) {
        ModelAndView result;


        result = new ModelAndView("message/edit");
        result.addObject("message2", message2);
        result.addObject("message", message);


        return result;

    }


    //Create Method -----------------------------------------------------------


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView messagesList() {

        ModelAndView result;
        Collection<Message> message;

        message = messageService.findAll();
        result = new ModelAndView("message/list");
        result.addObject("message", message);
        result.addObject("requestURI", "message/list.do");

        return result;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Message message = messageService.create();
        message.setSender(actorService.findByPrincipal());
        Collection<Actor> users = actorService.findAll();

        result = createEditModelAndView(message);
        result.addObject("users", users);
        return result;

    }


    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int messageId) {
        ModelAndView result;
        Message message;

        message = messageService.findOne(messageId);
        Collection<Actor> users = actorService.findAll();

        Assert.notNull(message);
        result = createEditModelAndView(message);
        result.addObject("users", users);

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Message message, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(message);
        } else {
            try {
                messageService.save(message);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(message, "general.commit.error");
            }
        }
        return result;
    }

    //        @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
//    public ModelAndView delete(@Valid int messageId){
//        ModelAndView result;
//        try{
//            Message message = messageService.findOne(messageId);
//            messageService.delete(message);
//            result=new ModelAndView("redirect:list.do");
//        }catch(Throwable oops){
//            Message message = messageService.findOne(messageId);
//            result= createEditModelAndView(message, "general.commit.error");
//        }
//
//        return result;
//    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete2(@RequestParam Message message) {
        ModelAndView result;
        try {
            message.getFolder().getMessages().remove(message);
            messageService.delete(message);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {

            result = createEditModelAndView(message, "general.commit.error");
        }

        return result;
    }


    @RequestMapping(value = "/send")
    public ModelAndView sendMessage(@RequestParam String recipient, String subject, String body, String priority) {
        ModelAndView res = new ModelAndView("mensaje/text");
        String replacerecipient = recipient.replaceAll(",", "");
        String replacesubject = subject.replaceAll(",", "");
        String replacebody = body.replaceAll(",", "");
        String replacepriority = priority.replaceAll(",", "");

        try {
            Actor recipient2 = actorService.findByName(replacerecipient);
            Priority priority1 = Priority.valueOf(replacepriority);
            @SuppressWarnings("unused")
            Message message = actorService.textMessage(replacesubject, replacebody, recipient2, priority1);
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
        Message message = messageService.findOne(messageId);

        result = new ModelAndView("message/view");
        result.addObject("subject", message.getSubject());
        result.addObject("body", message.getBody());
        result.addObject("sendDate", message.getSendDate());
        result.addObject("sender", message.getSender());
        result.addObject("priority", message.getPriority());
        result.addObject("messageId", message.getId());
        result.addObject("requestURI", "message/view.do");

        return result;
    }

}
