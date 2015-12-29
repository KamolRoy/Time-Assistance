package com.devcomol.ta.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devcomol.ta.dao.TimeData;
import com.devcomol.ta.dao.User;
import com.devcomol.ta.dao.interfaces.FValidationGroup;
import com.devcomol.ta.schedulejob.NewsModel;
import com.devcomol.ta.schedulejob.RSSNewsFeed;
import com.devcomol.ta.service.TimeDataService;
import com.devcomol.ta.service.UserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier(value = "timeDataService")
	TimeDataService timeDataService;

	@Autowired
	@Qualifier(value = "userService")
	UserService userService;

	@Autowired
	@Qualifier(value = "rssNewsFeed")
	RSSNewsFeed rssNewsFeed;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping("/userhome")
	public String showUserHome(ModelMap model, Principal principal) {

		List<NewsModel> news = rssNewsFeed.getNewsList();
		List<Integer> durationList = new ArrayList<Integer>(Arrays.asList(15, 20, 30, 60, 90, 120));

		model.addAttribute("news", news);
		model.addAttribute("durationList", durationList);
		model.addAttribute("timeData", new TimeData());

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
			List<TimeData> userTimeData = timeDataService.getTimeData(pageUser);
			model.addAttribute("userTimeData", userTimeData);
		}

		return "userhome";
	}

	/*
	 * Creating new Time Data Event and performing necessary validation as per
	 * TimeData Bean
	 */

	@RequestMapping(value = "/createevent", method = RequestMethod.POST)
	public String createEvent(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			Principal principal, @Validated(FValidationGroup.class) TimeData timeData, BindingResult result) {

		List<NewsModel> news = rssNewsFeed.getNewsList();
		List<Integer> durationList = new ArrayList<Integer>(Arrays.asList(15, 20, 30, 60, 90, 120));

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}

		model.addAttribute("updatetype", "event");
		model.addAttribute("news", news);
		model.addAttribute("durationList", durationList);
		model.addAttribute("user", new User());

		if (result.hasErrors()) {
			model.addAttribute("showCEvent", "true");
			return "userhome";
		}

		String pageUser = principal.getName();
		timeData.getUser().setUsername(pageUser);
		timeData.setSaved_time(new Date());

		try {
			timeDataService.saveOrUpdateTimeData(timeData);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("changeinfo", "Something wrong!!! cant create the event \n" + e.getMessage());
		}

		// This model parameter is required to load userhome
		List<TimeData> userTimeData = timeDataService.getTimeData(pageUser);
		model.addAttribute("userTimeData", userTimeData);
		model.addAttribute("changeinfo", "Event Created: " + timeData.getEvent_name());
		model.addAttribute("timeData", new TimeData());

		return "userhome";
	}

	/*
	 * When user click edit button in the event table of userhome page
	 */
	@RequestMapping(value = "/updateevent", method = RequestMethod.GET)
	public String showUpdateEvent(ModelMap model, @RequestParam(value = "id", required = false) String id,
			Principal principal) {

		TimeData timeData = null;

		List<NewsModel> news = rssNewsFeed.getNewsList();
		List<Integer> durationList = new ArrayList<Integer>(Arrays.asList(15, 20, 30, 60, 90, 120));

		if (id != null) {
			int rowID = Integer.parseInt(id);
			timeData = timeDataService.getTimeData(rowID);
		} else {
			timeData = new TimeData();
		}

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}

		model.addAttribute("updatetype", "event");
		model.addAttribute("news", news);
		model.addAttribute("durationList", durationList);
		model.addAttribute("user", new User());
		model.addAttribute("timeData", timeData);

		return "updateinfo";
	}

	/*
	 * Update Delete or Cancel a selected event...
	 */
	@RequestMapping(value = "/doupdate", method = RequestMethod.POST)
	public String doUpdate(ModelMap model, Principal principal, @Validated(FValidationGroup.class) TimeData timeData, BindingResult result,
			@RequestParam(value = "action", required = false) String action) {

		List<NewsModel> news = rssNewsFeed.getNewsList();
		List<Integer> durationList = new ArrayList<Integer>(Arrays.asList(15, 20, 30, 60, 90, 120));

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
		}

		model.addAttribute("updatetype", "event");
		model.addAttribute("news", news);
		model.addAttribute("durationList", durationList);
		model.addAttribute("user", new User());

		if (result.hasErrors()) {
			model.addAttribute("updatetype", "event");
			return "updateinfo";
		}

		try {
			timeData.getUser().setUsername(principal.getName());
			switch (action) {
			case "Update":
				model.addAttribute("changeinfo", "Update Event: " + timeData.getEvent_name());
				timeData.setSaved_time(new Date());
				timeDataService.saveOrUpdateTimeData(timeData);
				break;
			case "Delete":
				model.addAttribute("changeinfo", "Delete Event: " + timeData.getEvent_name());
				timeDataService.deleteTimeData(timeData);
				break;
			case "Cancel":
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// This model parameter is required to load userhome
		List<TimeData> userTimeData = timeDataService.getTimeData(principal.getName());
		model.addAttribute("userTimeData", userTimeData);
		model.addAttribute("timeData", new TimeData());

		return "userhome";
	}

	/*
	 * When user click update profile in userhome page
	 */
	@RequestMapping("/updateuser")
	public String showUpdateUser(ModelMap model, Principal principal) {
		User user = null;
		List<NewsModel> news = rssNewsFeed.getNewsList();

		if (principal != null) {
			String pageUser = null;
			pageUser = principal.getName();
			model.addAttribute("pageUser", pageUser);
			user = userService.getUser(pageUser);
			user.setPassword(null);
		}

		if (user == null) {
			user = new User();
		}

		model.addAttribute("updatetype", "user");
		model.addAttribute("news", news);
		model.addAttribute("user", user);
		model.addAttribute("timeData", new TimeData());

		return "updateinfo";
	}

	/*
	 * Update user information
	 */
	@RequestMapping(value = "/doupdateuser", method = RequestMethod.POST)
	public String doUpdateUser(ModelMap model, Principal principal, @Validated(FValidationGroup.class) User user, BindingResult result,
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
					if (userService.checkOldPwd(pageUser, oldpassword)) {
						System.out.println("Old password match");
						userService.updateProfile(user);
						userService.updatePassword(user);
						model.addAttribute("changeinfo", "Password change successfully");
					} else {
						System.out.println("Old password doesn't match");
						model.addAttribute("matchpass", "notmatch");
						return "updateinfo";
					}
				} else {
					userService.updateProfile(user);
					model.addAttribute("changeinfo", "User information successfully updated");
				}
				break;

			case "Cancel":
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("changeinfo", "Something went wrong. Couldn't update any information");
		}

		// This model parameter is required to load userhome
		List<TimeData> userTimeData = timeDataService.getTimeData(principal.getName());
		model.addAttribute("userTimeData", userTimeData);
		model.addAttribute("timeData", new TimeData());

		return "userhome";
	}

}
