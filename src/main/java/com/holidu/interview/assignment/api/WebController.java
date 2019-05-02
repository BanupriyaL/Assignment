package com.holidu.interview.assignment.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.holidu.interview.assignment.exception.JSONException;
import com.holidu.interview.assignment.exception.TreeSearchException;

@RestController
public class WebController {
	
	@Autowired
	TreeSearchApplication treeSearchApplication;

	@RequestMapping(name = "healthCheckEndpoint", method = RequestMethod.GET, value = "/")
	public String healthCheck() {
		return "Greetings from the Holidu interview assignment!";
	}

	@RequestMapping(name = "treeStatistics", method = RequestMethod.GET, value = "/treeData")
	public String getTreeStatistics(@RequestParam(value = "x") double x, @RequestParam(value = "y") double y,
			@RequestParam(value = "radius") double radius) throws TreeSearchException, JSONException {
		return treeSearchApplication.getTreesDataInRange(x, y, radius);
	}

	public void setTreeSearchApplication(TreeSearchApplication treeSearchApplication) {
		this.treeSearchApplication = treeSearchApplication;
	}
}
