/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.*;

import domain.Banner;
import domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BannerService;
import services.QuestionService;

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
