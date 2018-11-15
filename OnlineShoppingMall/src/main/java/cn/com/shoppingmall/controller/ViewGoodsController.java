package cn.com.shoppingmall.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.shoppingmall.domain.Goods;
import cn.com.shoppingmall.domain.Remark;
import cn.com.shoppingmall.relate.Praise;
import cn.com.shoppingmall.service.ViewGoodsService;

@Controller
public class ViewGoodsController {

	@Autowired
	private ViewGoodsService viewGoodsService;
	
	/**
	 * 查看商品的业务方法
	 * @param goodsId 商品编号
	 * @param currentRemarkPageNumber 当前评论页页码
	 * @param pageRecordAmount 每页记录数
	 * @param session 会话
	 * @return 查看商品页面
	 */
	@RequestMapping("/viewGoods")
	public ModelAndView viewGoods(Integer userId , Integer goodsId , Integer currentRemarkPageNumber , Integer pageRecordAmount , HttpSession session){
		if(userId == null)
			userId = (Integer)session.getAttribute("userId");
		//已登录
		if(userId != 0) {
			Praise praise = new Praise(userId , goodsId);
			boolean isOK = false;
			isOK = viewGoodsService.checkPraiseStatus(praise);
			if(isOK == true)
				session.setAttribute("checkPraiseStatus" , true);
			else
				session.setAttribute("checkPraiseStatus" , false);
		}
		//第一次查看某商品时，记录下商品编号
		if(goodsId != null)
			session.setAttribute("currentGoodsId" , goodsId);
		//若未传递商品编号，则使用之前的商品编号
		else
			goodsId = (Integer)session.getAttribute("currentGoodsId");
		//未传递页码则默认返回评论页第1页
		if(currentRemarkPageNumber == null)
			currentRemarkPageNumber = 1;
		//未传递每页记录数则默认显示5条
		if(pageRecordAmount == null)
			pageRecordAmount = 5;
		ModelAndView mav = new ModelAndView("viewGoods");
		//初始化
		Goods goods = new Goods();
		List<Remark> remarkList = new ArrayList<>();
		List<Integer> pageIndex = new ArrayList<>();
		//根据传入的商品编号获得对应的商品
		goods = viewGoodsService.getGoodsByGoodsId(goodsId);
		//根据传入的商品编号获得对应的评论列表
		remarkList = viewGoodsService.getRemarkListByGoodsId(goodsId);
		//生成页码索引集合
		pageIndex = viewGoodsService.getPageIndex(goodsId , pageRecordAmount);
		//相关数据存入会话
		session.setAttribute("currentRemarkPageNumber" , currentRemarkPageNumber);
		session.setAttribute("currentPageRemarkAmount" , pageRecordAmount);
		session.setAttribute("goods" , goods);
		session.setAttribute("remarkList" , remarkList);
		session.setAttribute("remarkPageIndex" , pageIndex);
		return mav;
	}
	
	@RequestMapping("/viewCategory")
	public ModelAndView getCategoryGoods(String category , Integer currentPageNumber , Integer recordAmount , HttpSession session) {
		//没有传递参数默认返回第1页
		if(currentPageNumber == null)
			currentPageNumber = 1;
		//没有传递每页显示记录数则默认每页显示5条
		if(recordAmount == null)
			recordAmount = 5;
		ModelAndView mav = new ModelAndView("viewCategoryGoods");
		List<Integer> pageIndex = new LinkedList<>();
		List<Goods> goodsList = new LinkedList<>();
		//获取页码索引集合
		pageIndex = viewGoodsService.getPageIndexByCategory(category , recordAmount);
		//获取特定种类的商品
		goodsList = viewGoodsService.getGoodsByCategory(category);
		//存入会话
		session.setAttribute("currentPageNumber" , currentPageNumber);
		session.setAttribute("goodsList" , goodsList);
		session.setAttribute("pageIndex" , pageIndex);
		return mav;
	}
	
}
