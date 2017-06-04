/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package controllers;

import domain.Banner;
import domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BannerService;
import services.QuestionService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private BannerService bannerService;

    // Constructors -----------------------------------------------------------

    public WelcomeController() {
        super();
    }

    // Index ------------------------------------------------------------------

    @RequestMapping(value = "/index")
    public ModelAndView index(@RequestParam(required = false, defaultValue = "John Doe") final String name) {
        ModelAndView result;
        SimpleDateFormat formatter;
        String moment;


        Collection<Question> res = questionService.notBannedQuestions();


        Random randomGenerator = new Random();


        List<Banner> banner = new ArrayList<>();
        banner.addAll(bannerService.findAll());


        int index = randomGenerator.nextInt(banner.size());
        String bannerOut = banner.get(index).getUrl();

        String ppoImg = "<img src=\"";
        String finImg = "\" alt=\"Banner\" height=\"400\" width=\"700\">";

        String bannerFin = ppoImg + bannerOut + finImg;


        result = new ModelAndView("welcome/index");
        result.addObject("questions", res);
        result.addObject("banner", bannerFin);


        return result;
    }
}
