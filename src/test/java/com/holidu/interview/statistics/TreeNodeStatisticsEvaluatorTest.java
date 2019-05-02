package com.holidu.interview.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.holidu.interview.assignment.coordinate.CartesianPoint;
import com.holidu.interview.assignment.models.TreeNode;
import com.holidu.interview.assignment.statistics.TreeNodeStatisticsEvaluator;

public class TreeNodeStatisticsEvaluatorTest {

	@Test
	public void testFetchtreesInRangeReturnEmptyMap() {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		Map<String, Integer> map = TreeNodeStatisticsEvaluator.fetchtreesInRange(new CartesianPoint(10.0, 20.0), 30.0,
				treeList);
		Assert.assertEquals(0, map.size());
	}

	@Test
	public void testFetchtreesInRangeReturnMapWithTreesCount() {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		treeList.add(getTreeNode("redMaple", 123.6, 126.5));
		treeList.add(getTreeNode("redMaple", 130.6, 129.5));
		treeList.add(getTreeNode("", 130.6, 128.5));
		treeList.add(getTreeNode("redMaple", 150.6, 178.5));
		treeList.add(getTreeNode("", 151.6, 178.5));
		Map<String, Integer> map = TreeNodeStatisticsEvaluator.fetchtreesInRange(new CartesianPoint(135, 128), 15.0,
				treeList);
		Assert.assertEquals(1, map.size());
		Assert.assertTrue(map.get("redMaple").equals(2));
	}

	private TreeNode getTreeNode(String spc_common, double x_sp, double y_sp) {
		TreeNode treeNode = new TreeNode();
		treeNode.setAddress("addr1");
		treeNode.setSpc_common(spc_common);
		treeNode.setTree_id("tree2");
		treeNode.setX_sp(x_sp);
		treeNode.setY_sp(y_sp);
		return treeNode;
	}

}
