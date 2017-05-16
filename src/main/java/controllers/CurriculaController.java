package controllers;


import domain.Banner;
import domain.Curricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BannerService;
import services.CurriculaService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/curricula")
public class CurriculaController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private CurriculaService curriculaService;


    //Constructors----------------------------------------------

    public CurriculaController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(Curricula curricula) {
        ModelAndView result;

        result = createEditModelAndView(curricula, null);

        return result;
    }


    protected static ModelAndView createEditModelAndView(Curricula curricula, String message) {
        ModelAndView result;

        result = new ModelAndView("curricula/edit");
        result.addObject("curricula", curricula);
        result.addObject("message", message);

        return result;

    }


    //Create Method -----------------------------------------------------------


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView curriculasList() {

        ModelAndView result;
        Collection<Curricula> curricula;

        curricula = curriculaService.findAll();
        result = new ModelAndView("curricula/list");
        result.addObject("curricula", curricula);
        result.addObject("requestURI", "curricula/list.do");

        return result;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Curricula curricula = curriculaService.create();
        result = createEditModelAndView(curricula);

        return result;

    }


    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int curriculaId) {
        ModelAndView result;
        Curricula curricula;

        curricula = curriculaService.findOne(curriculaId);
        Assert.notNull(curricula);
        result = createEditModelAndView(curricula);

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Curricula curricula, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(curricula);
        } else {
            try {
                curriculaService.save(curricula);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(curricula, "curricula.commit.error");
            }
        }
        return result;
    }

    //    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
//    public ModelAndView delete(@Valid int curriculaId){
//        ModelAndView result;
//        try{
//            curricula curricula = curriculaService.findOne(curriculaId);
//            curriculaService.delete(curricula);
//            result=new ModelAndView("redirect:list.do");
//        }catch(Throwable oops){
//            curricula curricula = curriculaService.findOne(curriculaId);
//            result= createEditModelAndView(curricula, "curricula.commit.error");
//        }
//
//        return result;
//    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete2(@RequestParam int curriculaId) {
        ModelAndView result;
        try {
            Curricula curricula = curriculaService.findOne(curriculaId);
            curriculaService.delete(curricula);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            Curricula curricula = curriculaService.findOne(curriculaId);
            result = createEditModelAndView(curricula, "curricula.commit.error");
        }

        return result;
    }

}
