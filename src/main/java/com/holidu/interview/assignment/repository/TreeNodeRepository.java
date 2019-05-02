package com.holidu.interview.assignment.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.holidu.interview.assignment.exception.JSONException;
import com.holidu.interview.assignment.json.JSONUrlReader;
import com.holidu.interview.assignment.models.TreeNode;

@Service
public class TreeNodeRepository {
	private Map<String, List<TreeNode>> treesDataCache = new HashMap<String, List<TreeNode>>();
	private Logger logger = LogManager.getLogger(TreeNodeRepository.class);

	public List<TreeNode> getTreesData(String url) throws JSONException {
		List<TreeNode> treeList = treesDataCache.get(url);
		if (treeList == null || treeList.isEmpty()) {
			logger.debug(
					"The Trees data in the URL[{}] is not found in the cache.Hence fetching from the main repository",
					url);
			treeList = JSONUrlReader.getDataFromJson(url);
			treesDataCache.put(url, treeList);
		}
		return treeList;
	}
}
