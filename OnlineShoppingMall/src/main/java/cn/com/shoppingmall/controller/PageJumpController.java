package cn.com.shoppingmall.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.shoppingmall.domain.Cart;
import cn.com.shoppingmall.domain.Goods;
import cn.com.shoppingmall.domain.Remark;
import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.relate.OrderItem;
import cn.com.shoppingmall.relate.PageInfo;
import cn.com.shoppingmall.relate.PurchaseItem;
import cn.com.shoppingmall.service.PageJumpService;

/**
 * 页面跳转控制器
 * */
@Controller
public class PageJumpController {

	@Autowired
	private PageJumpService pageJumpService;
	
    @RequestMapping("/gotoLoginPage")
    public ModelAndView gotoLoginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/gotoRegisterPage")
    public ModelAndView gotoRegisterPage() {
        ModelAndView mav = new ModelAndView("register");
        return mav;
    }
    
    @RequestMapping("/gotoPersonalCenterPage")
    public ModelAndView gotoPersonalCenterPage(Integer userId  , Integer centerOrderPageNumber , Integer centerRemarkPageNumber , HttpSession session) {
    	//如果没有传参
    	if(centerOrderPageNumber == null) {
    		//能获取到之前的页码参数
    		if(session.getAttribute("centerOrderPageNumber") != null)
    			//将之前的页码作为当前页码
    			centerOrderPageNumber = (Integer)session.getAttribute("centerOrderPageNumber");
    		//第一次进入个人中心默认进入第一页
    		else
    			centerOrderPageNumber = 1;
    	}
    	//同上
    	if(centerRemarkPageNumber == null) {
    		if(session.getAttribute("centerRemarkPageNumber") != null)
    			centerRemarkPageNumber = (Integer)session.getAttribute("centerRemarkPageNumber");
    		else
    			centerRemarkPageNumber = 1;
    	}	
    	List<Remark> personalRemarkList = new ArrayList<>();
    	List<OrderItem> personalOrderItemList = new ArrayList<>();
    	List<Integer> orderPageIndex = new LinkedList<>();
    	List<Integer> remarkPageIndex = new LinkedList<>();
    	//获取该用户的所有评论
    	personalRemarkList = pageJumpService.getRemarkListByUserId(userId);
    	//获取该用户的所有订单
    	personalOrderItemList = pageJumpService.getOrderItemListByUserId(userId);
    	//获取订单页码索引
    	orderPageIndex = pageJumpService.getOrderPageIndex(userId , 5);
    	//获取评论页码索引
    	remarkPageIndex = pageJumpService.getRemarkPageIndex(userId , 5);
    	//存入会话
    	session.setAttribute("personalRemarkList" , personalRemarkList);
    	session.setAttribute("personalOrderItemList" , personalOrderItemList);
    	session.setAttribute("orderPageIndex" , orderPageIndex);
    	session.setAttribute("remarkPageIndex" , remarkPageIndex);
    	//设置当前订单页
    	session.setAttribute("centerOrderPageNumber" , centerOrderPageNumber);
    	//设置当前评论页
    	session.setAttribute("centerRemarkPageNumber" , centerRemarkPageNumber);
    	//进入个人中心
    	ModelAndView mav = new ModelAndView("personalCenter");
    	return mav;
    }
    
    @RequestMapping("/gotoRemarkPage")
    public ModelAndView gotoRemarkPage(Integer userId , Integer goodsId , HttpSession session) {
    	ModelAndView mav = new ModelAndView();
    	//未登录
    	if(userId == 0) {
    		session.setAttribute("remarkError" , "您尚未登录，无法发表评论。");
    		mav.setViewName("login");
    	//已登录
    	}else {
    		mav.setViewName("remark");
    	}
    	return mav;
    }
    
    /**
     * .进入购物车页面
     * @param previousPageInfo 前一页页面信息
     * @param session 会话
     * @return 购物车页面
     */
    @RequestMapping("/gotoCartPage")
    public ModelAndView gotoCartPage(String previousPageInfo , HttpSession session) {
    	ModelAndView mav = new ModelAndView();
    	//没登录
    	if(session.getAttribute("user") == null) {
    		session.setAttribute("gotoCartError" , "您尚未登录，无法查看购物车。");
    		mav.setViewName("login");
    	//已登录
    	}else {
    		if(previousPageInfo == null)
    			previousPageInfo = "";
    		//判断之前的页面信息
    		switch (previousPageInfo) {
    		//从首页进入购物车
    		case "index":
    			session.setAttribute("previousPageInfo" , "index");
    			break;
    		//从查看商品页面进入购物车
    		case "viewGoods":
    			session.setAttribute("previousPageInfo" , "viewGoods");
    			break;
    		//从个人中心进入购物车
    		case "personalCenter":
    			session.setAttribute("previousPageInfo" , "personalCenter");
    			break;
    		default:
    			break;
    		}
			mav.setViewName("cart");
		}
    	return mav;
    }
    
    /**
     * 添加商品进入购物车的方法
     * @param userId 用户编号
     * @param goodsId 商品编号
     * @param amount 购买数量
     * @param session 会话
     * @return 指定视图
     */
    @RequestMapping("/addGoodsToCart")
    public ModelAndView gotoCart(Integer userId , Integer goodsId , Integer amount , HttpSession session) {
    	ModelAndView mav = new ModelAndView();
    	Goods goods = new Goods();
    	Double totalPrice = 0.0;
    	//未登录
    	if(userId == 0) {
    		session.setAttribute("purchaseError" , "您尚未登录，无法购买。");
    		mav.setViewName("login");
    	//已登录
    	}else {
    		//获取用户的购物车(登陆时就在会话中创建了购物车)
	    	Cart cart = (Cart)session.getAttribute("cart");
	    	//创建和当前用户绑定的购物车
	    	cart.setUserId(userId);
	    	//根据商品编号去数据库中获取商品信息
	    	goods = pageJumpService.getGoodsByGoodsId(goodsId);
	    	//计算总价
	    	totalPrice = goods.getPrice() * amount;
	    	//创建购买的商品对象
	    	PurchaseItem purchaseItem = new PurchaseItem(goods , amount , totalPrice);
	    	//如果购物车中已经存在该商品，则覆盖
	    	for(PurchaseItem item : cart.getGoodsList()) {
	    		if(item.getGoods().getGoodsId() == goodsId) {
	    			cart.getGoodsList().remove(item);
	    			break;
	    		}
	    	}
	    	//加入购物车(如果重复添加商品则覆盖之前的记录)
	    	cart.getGoodsList().add(purchaseItem);
	    	//存入会话
	    	session.setAttribute("cart" , cart);
	    	session.setAttribute("previousPageInfo" , "viewGoods");
	    	mav.setViewName("cart");
    	}
    	return mav;
    }

    /**
     * .创建订单
     * @return 订单页面
     */
    @RequestMapping("/gotoOrderPage")
    public ModelAndView gotoOrderPage() {
    	ModelAndView mav = new ModelAndView("order");
    	return mav;
    }
    
    /*** 初次进入首页的控制器
     * */
    @RequestMapping("/")
    public ModelAndView gotoIndexPage(HttpSession session) {
    	List<Goods> goodsList = new ArrayList<>();
    	List<Integer> pageIndex = new ArrayList<>();
    	//第一次进入首页到达第一页
    	session.setAttribute("currentPageNumber" , 1);
    	//每页显示五条商品记录
    	session.setAttribute("currentPageRecordAmount" , 5);
    	//获取刚刚设置的值
    	Integer currentPageNumber = (Integer)session.getAttribute("currentPageNumber");
    	//获取刚刚设置的值
    	Integer currentPageRecordAmount = (Integer)session.getAttribute("currentPageRecordAmount");
    	//当前页显示的记录的开始索引
    	Integer recordStartIndex = (currentPageNumber - 1) * currentPageRecordAmount;
    	//将开始索引和每页记录数目封装成一个对象，供dao层使用
    	PageInfo pageInfo = new PageInfo(recordStartIndex , currentPageRecordAmount);
    	//根据关键信息去数据库的表中获取指定位置的记录
    	goodsList = pageJumpService.getSpecifiedPageGoods(pageInfo);
    	//根据商品总记录数和每页显示记录数动态生成页码索引
    	pageIndex = pageJumpService.getGoodsPageIndex(pageInfo);
    	//当前页为第一页
    	session.setAttribute("currentPageNumber" , 1);
    	//将检索的商品列表存入会话(当前页面显示的指定的商品记录)
    	session.setAttribute("goodsList" , goodsList);
    	//将页码索引集合存入会话
    	session.setAttribute("pageIndex" , pageIndex);
        //进入首页
    	ModelAndView mav = new ModelAndView("index");
        return mav;
    }
    
    /**
     * 从其他页面进入首页的控制器
     * @param currentPageNumber 当前页页码
     * @param session 会话
     * @return 指定视图
     */
    @RequestMapping("/index")
    public ModelAndView gotoIndexPage(Integer currentPageNumber , HttpSession session) {
    	List<Goods> goodsList = new ArrayList<>();
    	List<Integer> pageIndex = new ArrayList<>();
    	//如果没有传递页码，则从会话中获取上次访问的最后一页的页码
    	if(currentPageNumber == null) {
    		if(session.getAttribute("currentPageNumber") != null)
    			currentPageNumber = (Integer)session.getAttribute("currentPageNumber");
    		else 
				currentPageNumber = 1;
    	}
    	//获取刚刚设置的值
    	Integer currentPageRecordAmount = (Integer)session.getAttribute("currentPageRecordAmount");
    	//当前页显示的记录的开始索引
    	Integer recordStartIndex = (currentPageNumber - 1) * currentPageRecordAmount;
    	//将开始索引和每页记录数目封装成一个对象，供dao层使用
    	PageInfo pageInfo = new PageInfo(recordStartIndex , currentPageRecordAmount);
    	//根据关键信息去数据库的表中获取指定位置的记录
    	goodsList = pageJumpService.getSpecifiedPageGoods(pageInfo);
    	//根据商品总记录数和每页显示记录数动态生成页码索引
    	pageIndex = pageJumpService.getGoodsPageIndex(pageInfo);
    	//设置当前页为上次访问的最后一页
    	session.setAttribute("currentPageNumber" , currentPageNumber);
    	//将检索的商品列表存入会话(当前页面显示的指定的商品记录)
    	session.setAttribute("goodsList" , goodsList);
    	//将页码索引集合存入会话
    	session.setAttribute("pageIndex" , pageIndex);
        //进入首页
    	ModelAndView mav = new ModelAndView("index");
        return mav;
    }
    
    /**
     * 下单成功
     * @return
     */
    @RequestMapping("/gotoSuccessPage")
    public ModelAndView gotoSuccessPage() {
    	ModelAndView mav = new ModelAndView("orderSuccess");
    	return mav;
    }
    
    /**
     * 进入修改资料页面
     * @return 指定视图
     */
    @RequestMapping("/editPersonalAccount")
    public ModelAndView editPersonalAccount(String username , HttpSession session) {
    	//进入修改资料页面
    	ModelAndView mav = new ModelAndView("edit");
    	//通过用户名获取用户
    	User user = pageJumpService.getUserByUserName(username);
    	//将用户信息存入会话
    	session.setAttribute("previousInfo" , user);
    	return mav;
    }
    
    /**
     * 用户注销控制器
     * @param session 会话
     * @return 指定视图
     */
    @RequestMapping("/userLogout")
    public ModelAndView logout(HttpSession session) {
    	ModelAndView mav = new ModelAndView("index");
    	//清除会话中的user属性，用户注销
    	session.setAttribute("user" , null);
    	session.setAttribute("cart" , null);
    	session.setAttribute("currentGoodsId" , null);
    	return mav;
    }
    
    /**
     * .进入管理员登录页面
     * @return 后台
     */
    @RequestMapping("/admin")
    public ModelAndView gotoAdminPage() {
    	ModelAndView mav = new ModelAndView("adminLogin");
    	return mav;
    }
    
    /**
     * .管理员登录
     * @param username 管理员账号
     * @param password 管理员密码
     * @param session 会话
     * @return 指定视图
     */
    @RequestMapping("/adminLogin")
    public ModelAndView gotoOperatingCenter(String username , String password , HttpSession session) {
    	ModelAndView mav = new ModelAndView();
    	if(username.equals("admin") && password.equals("123"))
    		mav.setViewName("operatingCenter");
    	else {
    		session.setAttribute("adminUsernameOrPasswordError" , "用户名或密码错误。");
    		mav.setViewName("adminLogin");
    	}
    	return mav;
    }

}
