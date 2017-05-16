package controllers;

import converters.ActorToStringConverter;
import domain.Actor;
import domain.Message;
import domain.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MessageService;

/**
 * Created by daviddelatorre on 15/12/16.
 */
@Controller
@RequestMapping("actor")
public class MensajesController extends AbstractController {


    public MensajesController() {
        super();
    }

    @Autowired
    private MessageService messageService;
    @Autowired
    private ActorService actorService;


    @RequestMapping(value = "/mensaje/new")
    public ModelAndView newMessage() {
        ModelAndView res;
        Message m = messageService.create();
        res = createGenericEditModelAndView(m);
        return res;
    }

    @RequestMapping(value = "/mensaje/send")
    public ModelAndView sendMessage(@RequestParam String recipient, String subject, String body, String priority) {
        ModelAndView res = new ModelAndView("mensaje/text");
        String replacerecipient = recipient.replaceAll(",", "");
        String replacesubject = subject.replaceAll(",", "");
        String replacebody = body.replaceAll(",", "");


        return res;
    }


    @RequestMapping(value = "/mensaje/delete")
    public ModelAndView deleteMessage(@RequestParam int id) {
        ModelAndView res;
        String text1 = "Message deleted / Mensaje borrado";
        //  Message message = messageService.findOne(id);

        res = new ModelAndView("mensaje/text");
        res.addObject("text1", text1);
        return res;


    }

    @RequestMapping(value = "/mensaje/delete/perm")
    public ModelAndView deleteMessagePerm(@RequestParam int id) {
        ModelAndView res;
        String text1 = "Message Deleted permanently / Mensaje borrado de forma permanente";
        Message message = messageService.findOne(id);
        messageService.delete(message);
        res = new ModelAndView("mensaje/text");
        res.addObject("text1", text1);
        return res;


    }


    protected ModelAndView createGenericEditModelAndView(Message mensaje3) {

        return createGenericEditModelAndView(mensaje3, null);

    }

    protected ModelAndView createGenericEditModelAndView(Message mensaje3, String message2) {
        ActorToStringConverter actorToStringConverter = new ActorToStringConverter();
        String recipient = actorToStringConverter.convert(mensaje3.getReceiver());
        String subject = mensaje3.getSubject();
        String body = mensaje3.getBody();
        ModelAndView res = new ModelAndView("mensaje/edit");
        res.addObject("mensaje3", mensaje3);
        res.addObject("recipient", recipient);
        res.addObject("subject", subject);
        res.addObject("body", body);

        return res;
    }

}
