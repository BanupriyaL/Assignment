package com.holidu.interview.assignment.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.holidu.interview.assignment.coordinate.CartesianPoint;
import com.holidu.interview.assignment.models.TreeNode;

public class TreeNodeStatisticsEvaluator {

	public static Map<String, Integer> fetchtreesInRange(CartesianPoint point, double radius, List<TreeNode> treeList) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (TreeNode treeNode : treeList) {
			String spc_common = treeNode.getSpc_common();
			if (!StringUtils.isEmpty(spc_common)) {
				if (point.findDistance(treeNode.getX_sp(), treeNode.getY_sp()) < radius) {
					Integer count = map.getOrDefault(spc_common, 0);
					count++;
					map.put(spc_common, count);
				}
			}
		}
		return map;
	}

}
