package com.devcomol.ta.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devcomol.ta.dao.TimeData;
import com.devcomol.ta.dao.User;
import com.devcomol.ta.schedulejob.NewsModel;
import com.devcomol.ta.schedulejob.RSSNewsFeed;
import com.devcomol.ta.service.TimeDataService;
import com.devcomol.ta.service.UserService;

@Controller
public class AdminController {

	@Autowired
	@Qualifier(value = "timeDataService")
	TimeDataService timeDataService;

	@Autowired
	@Qualifier(value = "userService")
	UserService userService;

	@Autowired
	@Qualifier(value = "rssNewsFeed")
	RSSNewsFeed rssNewsFeed;

	@RequestMapping("/adminhome")
	public String showAdminHome(ModelMap model, Principal principal) {

		List<NewsModel> news = rssNewsFeed.getNewsList();

		model.addAttribute("news", news);
		model.addAttribute("usersData", userService.getAllUsers());

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}

		return "adminhome";
	}
	
	/*
	 * When Admin click edit for a User
	 */
	@RequestMapping(value="/updateuserAsAdmin", method= RequestMethod.GET)
	public String showUpdateUser(ModelMap model,Principal principal, @RequestParam(value = "username", required = true) String username) {
		List<NewsModel> news = rssNewsFeed.getNewsList();

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}

		User user = userService.getUser(username);
		user.setPassword(null);
		

		model.addAttribute("updatetype", "user");
		model.addAttribute("updateAttribute","asadmin");
		model.addAttribute("news", news);
		model.addAttribute("user", user);
		model.addAttribute("timeData", new TimeData());

		return "updateinfo";
	}

	/*
	 * Update user information
	 */
	@RequestMapping(value = "/doupdateuserAsAdmin", method = RequestMethod.POST)
	public String doUpdateUser(ModelMap model, Principal principal, @Validated User user, BindingResult result,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "oldpassword", required = false) String oldpassword,
			@RequestParam(value = "changepass", required = false) String changepass) {

		List<NewsModel> news = rssNewsFeed.getNewsList();
		List<Integer> durationList = new ArrayList<Integer>(Arrays.asList(15, 20, 30, 60, 90, 120));

		String pageUser = null;
		if (principal != null) {
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}

		model.addAttribute("updatetype", "user");
		model.addAttribute("news", news);
		model.addAttribute("durationList", durationList);
		model.addAttribute("timeData", new TimeData());

		if (changepass != null) {
			model.addAttribute("checkvalue", "checked");
		} else {
			model.addAttribute("checkvalue", "uschecked");
		}

		if (result.hasErrors()) {
			model.addAttribute("updatetype", "user");

			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateinfo";
		}

		try {
			switch (action) {
			case "Update":

				if (changepass != null) {
					if (oldpassword == null)
						oldpassword = "";
					if (oldpassword.equals("superpass")) {
						System.out.println("Old password match");
						userService.updateProfile(user);
						userService.updateUserPassword(user.getUsername(), user.getPassword());
						model.addAttribute("changeinfo", "Password change successfully for user: " + user.getUsername());
					} else {
						System.out.println("Old password doesn't match");
						model.addAttribute("matchpass", "notmatch");
						return "updateinfo";
					}
				} else {
					userService.updateProfile(user);
					model.addAttribute("changeinfo", "User information successfully updated for user: " + user.getUsername());
				}
				break;
				
			case "Delete":
				userService.deleteUserTimeData(user.getUsername());
				userService.deleteUser(user.getUsername());
				model.addAttribute("changeinfo", "User information successfully deleted");
				break;

			case "Cancel":
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			String errorinfo = e.getMessage() + "\n" + e.getCause() + "\n" + e.getLocalizedMessage();
			model.addAttribute("errorinfo",errorinfo);
			model.addAttribute("changeinfo", "Something went wrong. Couldn't update any information");
		}

		// This model parameter is required to load userhome
		model.addAttribute("usersData", userService.getAllUsers());

		return "adminhome";
	}
}
