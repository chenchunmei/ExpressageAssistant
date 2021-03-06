package com.remarkable.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remarkable.entity.Address;
import com.remarkable.entity.Company;
import com.remarkable.entity.Order;
import com.remarkable.entity.Rectime;
import com.remarkable.mapper.AddOrdersMapper;
import com.remarkable.service.IAddOrdersService;

/**
 * 用户发布订单接口实现层
 * @author 王慧
 *
 */
@Service
public class AddOrdersServiceImpl implements IAddOrdersService {

	@Autowired
	private AddOrdersMapper addOrdersMapper;

	/**
	 * 用户发布订单
	 * @param order
	 */
	@Override
	public void insertOrder(Order order) {
		Integer emp_id = addOrdersMapper.findAllEmpId(order.getAdd_id());
		order.setEmp_id(emp_id);
		addOrdersMapper.insertOrder(order);
	}

	/**
	 * 查询快递信息
	 * @return
	 */
	@Override
	public List<Company> queryCom() {
		return addOrdersMapper.queryCom();
	}

	/**
	 * 查询接收地址信息
	 * @return
	 */
	@Override
	public List<Address> queryAdd() {
		return addOrdersMapper.queryAdd();
	}

	/**
	 * 查询接收时间信息
	 * @return
	 */
	@Override
	public List<Rectime> queryTime() {
		return addOrdersMapper.queryTime();
	}
}
