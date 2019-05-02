package com.holidu.interview.assignment.api;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holidu.interview.assignment.coordinate.CartesianPoint;
import com.holidu.interview.assignment.exception.ExceptionConstants;
import com.holidu.interview.assignment.exception.JSONException;
import com.holidu.interview.assignment.exception.TreeSearchException;
import com.holidu.interview.assignment.json.JSONUrlReader;
import com.holidu.interview.assignment.models.TreeNode;
import com.holidu.interview.assignment.repository.TreeNodeRepository;
import com.holidu.interview.assignment.statistics.TreeNodeStatisticsEvaluator;

@Service
public class TreeSearchApplication {

	private static Logger logger = LogManager.getLogger(TreeSearchApplication.class);

	@Autowired
	TreeNodeRepository treeNodeRepository;

	public String getTreesDataInRange(double x, double y, double radius) throws TreeSearchException, JSONException {
		String url = "https://data.cityofnewyork.us/resource/nwxe-4ae8.json";
		logger.debug("Fetch complete trees Data from repository URL[{}]", url);
		List<TreeNode> treeList = treeNodeRepository.getTreesData(url);
		if (!treeList.isEmpty()) {
			logger.debug("Filter the trees within the radius[{}] from the point({},{})", radius, x, y);
			CartesianPoint point = new CartesianPoint(x, y);
			Map<String, Integer> mapOfTreesVsCountInRange = TreeNodeStatisticsEvaluator.fetchtreesInRange(point, radius,
					treeList);
			if (mapOfTreesVsCountInRange.isEmpty()) {
				logger.debug("No Trees were found in the radius[{}] from the point({},{})", radius, x, y);
				throw new TreeSearchException(String.format(ExceptionConstants.NO_TREES_FOUND_IN_RANGE, radius, x, y));
			}
			logger.debug("Create JSON of the filtered tree Data[{}])", mapOfTreesVsCountInRange);
			return JSONUrlReader.createJson(mapOfTreesVsCountInRange);
		}
		logger.debug("No Trees were found in the repository URL[{}]", url);
		throw new TreeSearchException(String.format(ExceptionConstants.NO_TREES_FOUND, url));
	}

	public void setNodeRepository(TreeNodeRepository nodeRepository) {
		this.treeNodeRepository = nodeRepository;
	}

}
