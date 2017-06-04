/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package controllers;


import domain.LearningMaterial;
import domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.LearningMaterialService;
import services.ModuleService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/learningMaterial")
public class LearningMaterialController extends AbstractController {

    //Services ----------------------------------------------------------------

    @Autowired
    private LearningMaterialService learningMaterialService;
    @Autowired
    private ModuleService moduleService;


    //Constructors----------------------------------------------

    public LearningMaterialController() {
        super();
    }

    protected static ModelAndView createEditModelAndView(LearningMaterial learningMaterial) {
        ModelAndView result;

        result = createEditModelAndView(learningMaterial, null);

        return result;
    }


    protected static ModelAndView createEditModelAndView(LearningMaterial learningMaterial, String message) {
        ModelAndView result;

        result = new ModelAndView("learning-material/edit");
        result.addObject("learningMaterial", learningMaterial);
        result.addObject("message", message);

        return result;

    }


    //Create Method -----------------------------------------------------------


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView learningMaterialsList() {

        ModelAndView result;
        Collection<LearningMaterial> learningMaterial;

        learningMaterial = learningMaterialService.findAll();
        result = new ModelAndView("learningMaterial/list");
        result.addObject("learningMaterial", learningMaterial);
        result.addObject("requestURI", "learningMaterial/list.do");

        return result;
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam int moduleId) {

        ModelAndView result;

        Module module = moduleService.findOne(moduleId);

        LearningMaterial learningMaterial = learningMaterialService.create();
        learningMaterial.setModule(module);
        result = createEditModelAndView(learningMaterial);

        return result;

    }


    // Ancillary methods ------------------------------------------------


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int learningMaterialId) {
        ModelAndView result;
        LearningMaterial learningMaterial;

        learningMaterial = learningMaterialService.findOne(learningMaterialId);
        Assert.notNull(learningMaterial);
        result = createEditModelAndView(learningMaterial);

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid LearningMaterial learningMaterial, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = createEditModelAndView(learningMaterial);
        } else {
            try {
                learningMaterialService.save(learningMaterial);
                result = new ModelAndView("administrator/action-1");
            } catch (Throwable oops) {
                result = createEditModelAndView(learningMaterial, "learningMaterial.commit.error");
            }
        }
        return result;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete2(@RequestParam int learningMaterialId) {
        ModelAndView result;
        try {
            LearningMaterial learningMaterial = learningMaterialService.findOne(learningMaterialId);
            learningMaterial.setModule(null);
            learningMaterialService.delete(learningMaterial);
            result = new ModelAndView("administrator/action-1");
        } catch (Throwable oops) {
            LearningMaterial learningMaterial = learningMaterialService.findOne(learningMaterialId);
            result = createEditModelAndView(learningMaterial, "learningMaterial.commit.error");
        }

        return result;
    }

}
