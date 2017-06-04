/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AbstractController {

    // Panic handler ----------------------------------------------------------

    @ExceptionHandler(Throwable.class)
    public ModelAndView panic(final Throwable oops) {
        ModelAndView result;

        result = new ModelAndView("misc/panic");
        result.addObject("name", ClassUtils.getShortName(oops.getClass()));
        result.addObject("exception", oops.getMessage());
        result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));

        return result;
    }

}
