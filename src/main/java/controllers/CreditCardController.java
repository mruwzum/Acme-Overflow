/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package controllers;

import domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CreditCardService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("creditcard")
public class CreditCardController extends AbstractController {


    //Services ----------------------------------------------------------------

    @Autowired
    private CreditCardService creditCardService;


    //Constructors----------------------------------------------

    public CreditCardController() {
        super();
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView creditcardList() {

        ModelAndView result;
        Collection<CreditCard> creditCard;
        creditCard = creditCardService.findAll();
        result = new ModelAndView("credit-card/list");
        result.addObject("creditCard", creditCard);
        result.addObject("requestURI", "creditcard/list.do");

        return result;
    }


    //Create Method -----------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        CreditCard creditCard = creditCardService.create();
        result = createEditModelAndView(creditCard);

        return result;

    }

    // Edition ---------------------------------------------------------

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int propertyId) {
        ModelAndView result;
        CreditCard creditCard;

        creditCard = creditCardService.findOne(propertyId);
        Assert.notNull(creditCard);
        result = createEditModelAndView(creditCard);
        result.addObject("creditCard", creditCard);


        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid CreditCard creditCard, BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(creditCard);
        } else {
            try {

                creditCardService.save(creditCard);
                result = new ModelAndView("redirect:list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(creditCard, "property.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam int propertyId) {
        ModelAndView result;
        CreditCard p = creditCardService.findOne(propertyId);

        try {
            creditCardService.delete(p);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = createEditModelAndView(p, "creditCard.commit.error");
        }


        return result;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int propertyId) {
        ModelAndView res;
        CreditCard p = creditCardService.findOne(propertyId);
        res = new ModelAndView("credit-card/view");
        res.addObject("creditCard", p);

        return res;
    }

    // Ancillary methods ------------------------------------------------

    protected ModelAndView createEditModelAndView(CreditCard creditCard) {
        ModelAndView result;

        result = createEditModelAndView(creditCard, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(CreditCard creditCard, String message) {
        ModelAndView result;

        result = new ModelAndView("credit-card/edit");
        result.addObject("creditCard", creditCard);
        result.addObject("message", message);

        return result;

    }


}








