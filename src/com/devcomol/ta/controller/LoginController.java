package com.devcomol.ta.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devcomol.ta.dao.User;
import com.devcomol.ta.dao.interfaces.FValidationGroup;
import com.devcomol.ta.schedulejob.NewsModel;
import com.devcomol.ta.schedulejob.RSSNewsFeed;
import com.devcomol.ta.service.UserService;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier(value = "rssNewsFeed")
	RSSNewsFeed rssNewsFeed;

	@RequestMapping("/denied")
	public String showDenied(Model model) {
		List<NewsModel> news = rssNewsFeed.getNewsList();

		model.addAttribute("news", news);
		model.addAttribute("pageInfo", "unusual");
		return "denied";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut(Model model) {
		List<NewsModel> news = rssNewsFeed.getNewsList();

		model.addAttribute("news", news);
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping("/login2")
	public String showLogin2(Model model) {
		List<NewsModel> news = rssNewsFeed.getNewsList();

		model.addAttribute("news", news);

		return "login2";
	}

	@RequestMapping("/403")
	public String showAccessDenied(ModelMap model, Principal principal) {
		List<NewsModel> news = rssNewsFeed.getNewsList();

		model.addAttribute("news", news);

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}
		model.addAttribute("pageInfo", "unusual");

		return "denied";
	}

	@RequestMapping("/dberror")
	public String showDBError(ModelMap model, Principal principal) {
		List<NewsModel> news = rssNewsFeed.getNewsList();

		model.addAttribute("news", news);

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}

		model.addAttribute("pageInfo", "unusual");

		return "dberror";
	}

	/*
	 * Creating New User Account and Performing necessary Validation as per User
	 * Bean
	 */

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createUser(Model model, @Validated(FValidationGroup.class) User user, BindingResult result) {

		List<NewsModel> news = rssNewsFeed.getNewsList();

		model.addAttribute("news", news);

		if (result.hasErrors()) {
			model.addAttribute("showRegister", "true");
			return "login";
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		if (userService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "This username already exists");
			model.addAttribute("showRegister", "true");
			return "login";
		}

		try {
			System.out.println("From Login Controller: " + user);
			userService.createUser(user);
		} catch (Exception e) {

			e.printStackTrace();
		}

		model.addAttribute("pageInfo", "unusual");
		return "login2";
	}
}
