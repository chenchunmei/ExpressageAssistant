package com.remarkable.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.remarkable.entity.Company;
import com.remarkable.entity.Order;
import com.remarkable.service.IUserOrderShowService;

/**
 * 用户订单显示
 * @author 王慧
 *
 */
@CrossOrigin
@Controller
public class UserOrderShowController {
	
	@Autowired
	private IUserOrderShowService userOrderShowServiceImpl;
	
	/**
	 * 根据用户ID,订单编号，订单状态查询订单信息 
	 * @param u_id
	 * @param ord_code
	 * @return
	 */
	@RequestMapping("/selectorderbyuid.action")
	@ResponseBody
	public List<Order> findOrderByUid(String ord_code) {
		System.err.println(ord_code);
		List<Order> orderList=new ArrayList<Order>(); 
		orderList = userOrderShowServiceImpl.findOrderByUid(1,ord_code);
		for (Order order : orderList) {
			System.out.println(order);
		}
		return orderList;
	}
	
	
	/**
	 * 根据订单编号查询一个订单详情
	 * @param ord_code 订单编号
	 * @return
	 */
	@RequestMapping("/findOrderdetailsByordId.action")
	@ResponseBody
	public Order findOrderdetailsByordId(String ord_code) {
		Order orders =userOrderShowServiceImpl.findOrderdetailsByordId(ord_code);
		System.out.println("==================");
		System.out.println(orders);
		return orders;
	}
	
	/**
	 * 删除用户订单记录（根据订单编号修改订单状态）
	 * @param ord_code 订单编号
	 * @return
	 */
	@RequestMapping("/deleteUserOrder.action")
	@ResponseBody
	public int deleteUserOrder(String ord_code) {
		int count=userOrderShowServiceImpl.deleteUserOrder(ord_code);
		System.out.println(count);
		return count;
	}
}
