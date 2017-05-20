package controllers;


import domain.Folder;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.FolderService;
import services.MessageService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/folder")
public class FolderController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private FolderService folderService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private MessageService messageService;


    //Constructors----------------------------------------------

    public FolderController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(Folder folder) {
        ModelAndView result;

        result = createEditModelAndView(folder, null);

        return result;
    }


    protected static ModelAndView createEditModelAndView(Folder folder, String message) {
        ModelAndView result;

        result = new ModelAndView("folder/edit");
        result.addObject("folder", folder);
        result.addObject("message", message);

        return result;

    }


    //Create Method -----------------------------------------------------------


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listFolder() {
        ModelAndView result;
        Collection<Folder> folders = actorService.findByPrincipal().getFolders();
        result = new ModelAndView("folder/list");
        result.addObject("folders", folders);
        result.addObject("requestURI", "folder/list.do");
        return result;
    }


    @RequestMapping(value = "/view")
    public ModelAndView insideFolder(@RequestParam int folderId) {
        ModelAndView result;
        Folder folder = folderService.findOne(folderId);
        Boolean isTrashBox = folder.getName().equals("Trashbox");
        Collection<Message> messages = folder.getMessages();
        result = new ModelAndView("message/list");
        result.addObject("messages1", messages);
        result.addObject("isTrashBox", isTrashBox);
        return result;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;
        Folder folder = folderService.create();
        result = createEditModelAndView(folder);
        return result;

    }


    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int folderId) {
        ModelAndView result;
        Folder folder;

        folder = folderService.findOne(folderId);
        Assert.notNull(folder);
        result = createEditModelAndView(folder);

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Folder folder, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(folder);
        } else {
            try {
                folder.setOwner(actorService.findByPrincipal());
                folderService.save(folder);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(folder, "general.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(@Valid Folder folder) {
        ModelAndView result;
        try {

            folderService.delete(folder);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {

            result = createEditModelAndView(folder, "general.commit.error");
        }

        return result;
    }
//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    public ModelAndView delete2(@RequestParam int folderId) {
//        ModelAndView result;
//        try {
//            Folder folder = folderService.findOne(folderId);
//            folderService.delete(folder);
//            result = new ModelAndView("redirect:list.do");
//        } catch (Throwable oops) {
//            Folder folder = folderService.findOne(folderId);
//            result = createEditModelAndView(folder, "folder.commit.error");
//        }
//
//        return result;
//    }

}
