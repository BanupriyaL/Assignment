package com.holidu.interview.assignment.json;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.holidu.interview.assignment.exception.ExceptionConstants;
import com.holidu.interview.assignment.exception.JSONException;
import com.holidu.interview.assignment.models.TreeNode;

public class JSONUrlReader {

	private static ObjectMapper mapper = new ObjectMapper();

	public static List<TreeNode> getDataFromJson(String url) throws JSONException {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		try {
			treeList = Arrays.asList(mapper.readValue(new URL(url), TreeNode[].class));
		} catch (IOException e) {
			throw new JSONException(String.format(ExceptionConstants.ERROR_ON_READING_JSON, url), e);
		}

		return treeList;
	}

	public static String createJson(Map<String, Integer> mapOfTreesVsCountInRange) throws JSONException {
		try {
			return mapper.writeValueAsString(mapOfTreesVsCountInRange);
		} catch (JsonProcessingException jpe) {
			throw new JSONException(String.format(ExceptionConstants.JSON_PROCESSING_EXCEPTION), jpe);
		}
	}
}
