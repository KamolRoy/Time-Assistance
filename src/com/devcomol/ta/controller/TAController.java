package com.devcomol.ta.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devcomol.ta.dao.User;
import com.devcomol.ta.schedulejob.NewsModel;
import com.devcomol.ta.schedulejob.RSSNewsFeed;

/*
 * It is the home controller
 */

@Controller
public class TAController {

	@Autowired
	@Qualifier(value = "rssNewsFeed")
	RSSNewsFeed rssNewsFeed;

	@RequestMapping(value = { "/", "/login" })
	public String showHome(ModelMap model, Principal principal) {

		List<NewsModel> news = rssNewsFeed.getNewsList();

		model.addAttribute("news", news);
		model.addAttribute("user", new User());
		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
			return "redirect:/userhome";
		} else {
			return "login";
		}
	}

	@RequestMapping("/home")
	public String showSiteHome(ModelMap model, Principal principal) {

		List<NewsModel> news = rssNewsFeed.getNewsList();

		model.addAttribute("news", news);
		model.addAttribute("user", new User());
		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}

		return "login";
	}

	@RequestMapping("/localnews")
	public String showLocalNews(ModelMap model, Principal principal) {

		List<NewsModel> localNews = rssNewsFeed.getNewsListFull();

		model.addAttribute("localNews", localNews);
		model.addAttribute("pageInfo", "unusual");

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}

		return "localnews";
	}

	@RequestMapping("/aboutme")
	public String showAboutMe(ModelMap model, Principal principal) {

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}
		model.addAttribute("pageInfo", "unusual");
		
		return "aboutme";
	}

	@RequestMapping("/help")
	public String showHelp(ModelMap model, Principal principal) {

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}
		model.addAttribute("pageInfo", "unusual");
		
		return "help";
	}

}
