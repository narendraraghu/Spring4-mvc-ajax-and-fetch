package com.narendra.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.narendra.web.jsonview.Views;
import com.narendra.web.model.AjaxResponseBody;
import com.narendra.web.model.SearchCriteria;
import com.narendra.web.model.User;

@RestController
public class AjaxController {

	List<User> users;

	// @ResponseBody, not necessary, since class is annotated with @RestController
	// @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
	// @JsonView(Views.Public.class) - Optional, limited the json data display to client.
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getSearchResult")
	public AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search) throws InterruptedException {

		AjaxResponseBody result = new AjaxResponseBody();

		Thread.sleep(590L);

		if (isValidSearchCriteria(search)) {
			List<User> users = findByUserNameOrEmail(search.getUsername(), search.getEmail());

			if (users.size() > 0) {
				result.setCode("200");
				result.setMsg("");
				result.setResult(users);
			} else {
				result.setCode("204");
				result.setMsg("No user!");
			}

		} else {
			result.setCode("400");
			result.setMsg("Search criteria is empty!");
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}

	private boolean isValidSearchCriteria(SearchCriteria search) {

		boolean valid = true;

		if (search == null) {
			valid = false;
		}

		if ((StringUtils.isEmpty(search.getUsername())) && (StringUtils.isEmpty(search.getEmail()))) {
			valid = false;
		}

		return valid;
	}

	// Init some users for testing
	@PostConstruct
	private void iniDataForTesting() {
		users = new ArrayList<User>();

		User user1 = new User("narendra", "pass123", "narendraraghu.89@gmail.com", "012-1234567", "address 123");
		User user2 = new User("kitty", "pass456", "narendra.raghuwanshi@tfc.com", "016-7654321", "address 456");
		User user3 = new User("john", "pass789", "narendraraghu.89@gmail.com", "012-111111", "address 789");
		users.add(user1);
		users.add(user2);
		users.add(user3);

	}

	// Simulate the search function
	private List<User> findByUserNameOrEmail(String username, String email) {

		List<User> result = new ArrayList<User>();

		for (User user : users) {

			if ((!StringUtils.isEmpty(username)) && (!StringUtils.isEmpty(email))) {

				if (username.equals(user.getUsername()) && email.equals(user.getEmail())) {
					result.add(user);
					continue;
				} else {
					continue;
				}

			}
			if (!StringUtils.isEmpty(username)) {
				if (username.equals(user.getUsername())) {
					result.add(user);
					continue;
				}
			}

			if (!StringUtils.isEmpty(email)) {
				if (email.equals(user.getEmail())) {
					result.add(user);
					continue;
				}
			}

		}

		return result;


	}
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/scorePlot")
	public AjaxResponseBody plot1(@RequestBody SearchCriteria search) throws InterruptedException {

		AjaxResponseBody result = new AjaxResponseBody();

		Thread.sleep(590L);
		result.setCode("Hello From scorePlot ");

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}


	@JsonView(Views.Public.class)
	@RequestMapping(value = "/data/getFoldChangeExpressionPlotResult")
	public AjaxResponseBody getFoldChangeExpressionPlotResult(@RequestBody SearchCriteria search) throws InterruptedException {

		AjaxResponseBody result = new AjaxResponseBody();

		Thread.sleep(5900L);
		result.setCode("Hello From getFoldChangeExpressionPlotResult ");

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}

	@JsonView(Views.Public.class)
	@RequestMapping(value = "/get/api/fetchAPIcall")
	public AjaxResponseBody fetchAPIcall() throws InterruptedException {

		AjaxResponseBody result = new AjaxResponseBody();
		Thread.sleep(5900L);
		result.setCode("Hello From fetchAPIcall ");

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}

	@JsonView(Views.Public.class)
	@RequestMapping(value = "/api/currentWeather")
	public AjaxResponseBody currentWeather() throws InterruptedException {

		AjaxResponseBody result = new AjaxResponseBody();
		Thread.sleep(5900L);
		result.setCode("Hello From currentWeather ");

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
}
