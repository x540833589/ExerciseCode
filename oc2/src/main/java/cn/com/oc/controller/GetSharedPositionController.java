package cn.com.oc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.oc.dao.impl.GetSharedPositionDao;
import cn.com.oc.domain.Position;

@Controller
public class GetSharedPositionController {

	@Autowired
	private GetSharedPositionDao getSharedPositionDao;
	
	@RequestMapping(value = "/getAllSharedPosition" , method = RequestMethod.POST)
	@ResponseBody
	public List<Position> getAllSharedPosition() {
		List<Position> allSharedPositionList = new ArrayList<>();
		allSharedPositionList = getSharedPositionDao.getAllSharedPosition();
		return allSharedPositionList;
	}
	
}
