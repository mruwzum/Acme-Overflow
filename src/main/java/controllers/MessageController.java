package controllers;


import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.MessageService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private MessageService messageService;


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
        result = createEditModelAndView(message);

        return result;

    }


    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int messageId) {
        ModelAndView result;
        Message message;

        message = messageService.findOne(messageId);
        Assert.notNull(message);
        result = createEditModelAndView(message);

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
                result = createEditModelAndView(message, "message.commit.error");
            }
        }
        return result;
    }

    //    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
//    public ModelAndView delete(@Valid int messageId){
//        ModelAndView result;
//        try{
//            Message message = messageService.findOne(messageId);
//            messageService.delete(message);
//            result=new ModelAndView("redirect:list.do");
//        }catch(Throwable oops){
//            Message message = messageService.findOne(messageId);
//            result= createEditModelAndView(message, "message.commit.error");
//        }
//
//        return result;
//    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete2(@RequestParam int messageId) {
        ModelAndView result;
        try {
            Message message = messageService.findOne(messageId);
            messageService.delete(message);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            Message message = messageService.findOne(messageId);
            result = createEditModelAndView(message, "message.commit.error");
        }

        return result;
    }

}
