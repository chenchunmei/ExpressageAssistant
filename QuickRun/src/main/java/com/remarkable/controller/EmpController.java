package com.remarkable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.remarkable.entity.Order;
import com.remarkable.service.IEmpService;

/**
 * 骑手控制层
 * @author Sun
 *
 */
@Controller
public class EmpController {

	//调回骑手服务层
	@Autowired
	private IEmpService empServiceImpl;
	
	/**
	 * 查询骑手的所有订单
	 * @param emp_id
	 * @param ord_code
	 * @param com_id
	 * @param add_id
	 * @return
	 */
	@RequestMapping("/orderEmpAll.action")
	public @ResponseBody List<Order> findOrderAll(Order order){
		List<Order> orderList = empServiceImpl.findOrderAll(order.getEmp_id(), order.getOrd_code(), order.getCom_id(), order.getAdd_id());
		return orderList;
	}
	
	/**
	 * 查询骑手未送达的所有订单
	 * @param emp_id
	 * @param ord_code
	 * @param com_id
	 * @param add_id
	 * @return
	 */
	@RequestMapping("/orderEmpNo.action")
	public @ResponseBody List<Order> findNoOrderAndEmpAll(Order order){
		List<Order> orderList = empServiceImpl.findNoOrderAndEmpAll(order.getEmp_id(), order.getOrd_code(), order.getCom_id(), order.getAdd_id());
		return orderList;
	}
	
	/**
	 * 查询骑手已经送达的所有订单
	 * @param emp_id
	 * @param ord_code
	 * @param com_id
	 * @param add_id
	 * @return
	 */
	@RequestMapping("/orderEmp.action")
	public @ResponseBody List<Order> findOrderAndEmpAll(Order order){
		List<Order> orderList = empServiceImpl.findOrderAndEmpAll(order.getEmp_id(), order.getOrd_code(), order.getCom_id(), order.getAdd_id());
		return orderList;
	}
	
	/**
	 * 查询骑手转交的所有订单
	 * @param emp_id
	 * @param ord_code
	 * @param com_id
	 * @param add_id
	 * @return
	 */
	@RequestMapping("/orderForwardSend.action")
	public @ResponseBody List<Order> findSendOrderByForward(Order order){
		List<Order> orderList = empServiceImpl.findSendOrderByForward(order.getEmp_id(), order.getOrd_code(), order.getCom_id(), order.getAdd_id());
		return orderList;
	}
	
	/**
	 * 查询骑手收到转交的所有订单
	 * @param ord_forward
	 * @param ord_code
	 * @param com_id
	 * @return
	 */
	@RequestMapping("/orderForwardPut.action")
	public @ResponseBody List<Order> findPutOrderByForward(Order order){
		List<Order> orderList = empServiceImpl.findPutOrderByForward(order.getOrd_forward(), order.getOrd_code(), order.getCom_id());
		return orderList;
	}
	
	/**
	 * 根据订单id查询订单详情
	 * @param ord_id
	 * @return
	 */
	@RequestMapping("/orderDetailEmp.action")
	public @ResponseBody Order findOrderById(int ord_id){
		Order order = empServiceImpl.findOrderById(ord_id);
		return order;
	}
}
