package controllers;


import domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ModuleService;


import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/module")
public class ModuleController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private ModuleService moduleService;


    //Constructors----------------------------------------------

    public ModuleController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(Module module) {
        ModelAndView result;

        result = createEditModelAndView(module, null);

        return result;
    }


    protected static ModelAndView createEditModelAndView(Module module, String message) {
        ModelAndView result;

        result = new ModelAndView("module/edit");
        result.addObject("module", module);
        result.addObject("message", message);

        return result;

    }


    //Create Method -----------------------------------------------------------


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView modulesList() {

        ModelAndView result;
        Collection<Module> module;

        module = moduleService.findAll();
        result = new ModelAndView("module/list");
        result.addObject("module", module);
        result.addObject("requestURI", "module/list.do");

        return result;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Module module = moduleService.create();
        result = createEditModelAndView(module);

        return result;

    }


    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int moduleId) {
        ModelAndView result;
        Module module;

        module = moduleService.findOne(moduleId);
        Assert.notNull(module);
        result = createEditModelAndView(module);

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Module module, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(module);
        } else {
            try {
                moduleService.save(module);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(module, "module.commit.error");
            }
        }
        return result;
    }

    //    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
//    public ModelAndView delete(@Valid int moduleId){
//        ModelAndView result;
//        try{
//            Module module = moduleService.findOne(moduleId);
//            moduleService.delete(module);
//            result=new ModelAndView("redirect:list.do");
//        }catch(Throwable oops){
//            Module module = moduleService.findOne(moduleId);
//            result= createEditModelAndView(module, "module.commit.error");
//        }
//
//        return result;
//    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete2(@RequestParam int moduleId) {
        ModelAndView result;
        try {
            Module module = moduleService.findOne(moduleId);
            moduleService.delete(module);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            Module module = moduleService.findOne(moduleId);
            result = createEditModelAndView(module, "module.commit.error");
        }

        return result;
    }

}
