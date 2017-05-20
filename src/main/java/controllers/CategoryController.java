package controllers;

import domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CategoryService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by mruwzum on 19/12/16.
 */
@Controller
@RequestMapping("category")
public class CategoryController extends AbstractController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController() {
        super();
    }

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Category> aux = categoryService.findAll();
        result = new ModelAndView("category/list");
        result.addObject("categories", aux);
        result.addObject("requestURI", "category/list.do");
        return result;


    }

//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    public ModelAndView deleteProperty(@RequestParam int categorieID) {
//        ModelAndView result = new ModelAndView("redirect:list.do");
//        Category categorie  = categoryService.findOne(categorieID);
//        try {
//            categoryService.delete(categorie);
//        }catch (DataIntegrityViolationException e){
//            result = new ModelAndView("sponsor/text");
//
//            String texto1 =  "You can't delete a category that contains any recipe / No puede borrar una categoría que tenga recetas asociadas";
//            result.addObject("texto1", texto1);
//        }
//        return result;
//    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int categoryId) {
        ModelAndView result;
        Category categorie = categoryService.findOne(categoryId);
        Assert.notNull(categorie);
        result = createEditModelAndView(categorie);
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Category categorie, BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(categorie);
        } else {
            try {

                categoryService.save(categorie);
                result = this.list();

            } catch (Throwable oops) {
                result = createEditModelAndView(categorie, "comment.commit.error");
            }
        }

        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView r;
        Category m;
        m = categoryService.create();
        r = createEditModelAndView(m);
        return r;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam int categoryId) {
        ModelAndView result;
        Category category = categoryService.findOne(categoryId);


        categoryService.delete(category);
        result = new ModelAndView("redirect:list.do");

        return result;
    }

    protected ModelAndView createEditModelAndView(Category categorie) {
        ModelAndView result;

        result = createEditModelAndView(categorie, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Category categorie, String message) {
        ModelAndView result;
        result = new ModelAndView("category/edit");
        result.addObject("category", categorie);
//        result.addObject("name",categorie.getName());
//       result.addObject("description",categorie.getDescription());


        return result;


    }
}
