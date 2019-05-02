package com.holidu.interview.assignment.api;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.holidu.interview.assignment.exception.ExceptionConstants;
import com.holidu.interview.assignment.exception.JSONException;
import com.holidu.interview.assignment.exception.TreeSearchException;
import com.holidu.interview.assignment.json.JSONUrlReader;
import com.holidu.interview.assignment.models.TreeNode;
import com.holidu.interview.assignment.repository.TreeNodeRepository;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ JSONUrlReader.class })
public class WebControllerTest {

	WebController webController;

	@Before
	public void setUp() {
		webController = new WebController();
		TreeSearchApplication searchApplication = new TreeSearchApplication();
		searchApplication.setNodeRepository(new TreeNodeRepository());
		webController.setTreeSearchApplication(searchApplication);
		PowerMockito.mockStatic(JSONUrlReader.class);
	}

	@Test
	public void testHealthCheck() {
		String healthCheck = webController.healthCheck();
		Assert.assertEquals("Greetings from the Holidu interview assignment!", healthCheck);
	}

	@Test
	public void testGetTreeStatisticsThrowsExceptionWhenNoTreesAreFoundInRadius() throws JSONException {
		List<TreeNode> treesList = new ArrayList<TreeNode>();
		treesList.add(getTreeNode("redMaple", 123.6, 126.5));
		Mockito.when(JSONUrlReader.getDataFromJson("https://data.cityofnewyork.us/resource/nwxe-4ae8.json"))
				.thenReturn(treesList);
		try {
			webController.getTreeStatistics(135, 128, 5);
			fail("Should throw TreeSearchException,When no trees are found in the given range");
		} catch (TreeSearchException e) {
			Assert.assertEquals(String.format(ExceptionConstants.NO_TREES_FOUND_IN_RANGE, 5.0, 135.0, 128.0),
					e.getMessage());
		}
	}

	@Test
	public void testGetTreeStatisticsWhenTreesAreFoundInRadiusRange() throws TreeSearchException, JSONException {
		List<TreeNode> treesList = new ArrayList<TreeNode>();
		treesList.add(getTreeNode("redMaple", 123.6, 126.5));
		Mockito.when(JSONUrlReader.getDataFromJson("https://data.cityofnewyork.us/resource/nwxe-4ae8.json"))
				.thenReturn(treesList);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("redMaple", 1);
		Mockito.when(JSONUrlReader.createJson(map)).thenReturn("{\"redMaple\":1}");
		String treeStatistics = webController.getTreeStatistics(135, 128, 15);

		Assert.assertEquals("{\"redMaple\":1}", treeStatistics);
	}

	@Test
	public void testGetTreeStatisticsWhenTreeListIsEmpty() throws JSONException {
		List<TreeNode> treesList = new ArrayList<TreeNode>();
		Mockito.when(JSONUrlReader.getDataFromJson("https://data.cityofnewyork.us/resource/nwxe-4ae8.json"))
				.thenReturn(treesList);
		try {
			webController.getTreeStatistics(30, 40, 5);
			fail("Should throw TreeSearchException,When no trees are found");
		} catch (TreeSearchException tse) {
			String url = "https://data.cityofnewyork.us/resource/nwxe-4ae8.json";
			Assert.assertEquals(String.format(ExceptionConstants.NO_TREES_FOUND, url), tse.getMessage());
		}
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

	@After
	public void tearDown() {
		webController = null;
	}

}
