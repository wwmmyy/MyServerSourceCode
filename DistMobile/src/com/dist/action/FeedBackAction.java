package com.dist.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.stereotype.Controller;

import com.dist.action.admin.BasicAction;
import com.dist.entity.SFeedback;
import com.dist.entity.SUsers;
import com.dist.service.SFeedbackServiceI;
import com.dist.service.UserServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class FeedBackAction extends BasicAction implements
		ModelDriven<SFeedback> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9110964178492903153L;

	@Autowired
	private SFeedbackServiceI feedbackService;

	@Autowired
	private UserServiceI userServiceImpl;

	private int page;
	private int rows;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public SFeedback getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void list() {
		List<SFeedback> list = feedbackService.find(page, rows);
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		Map<String, SUsers> userMap = new HashMap<String, SUsers>();
		for (SFeedback feedback : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			SUsers user = getUser(userMap, feedback.getUserId());
			if (user == null) {
				map.put("username", "");
			} else {
				map.put("username", user.getName());
			}

			map.put("feedtime", feedback.getFeedTime());
			map.put("content", feedback.getContent());
			map.put("id", feedback.getUserId()+".jpg");
			output.add(map);
		}
		userMap.clear();
		writeJson(output);
	}

	private SUsers getUser(Map<String, SUsers> userMap, String userId) {
		if (userId == null || userId.length() == 0) {
			return null;
		}
		if (userMap.containsKey(userId)) {
			return userMap.get(userId);
		}
		SUsers user = userServiceImpl.findById(userId);
		userMap.put(userId, user);
		return user;
	}
}
