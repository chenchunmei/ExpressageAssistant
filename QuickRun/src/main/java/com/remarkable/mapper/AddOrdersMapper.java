package com.remarkable.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.remarkable.entity.Address;
import com.remarkable.entity.Company;
import com.remarkable.entity.Order;
import com.remarkable.entity.Rectime;

/**
 * 用户发布订单
 * @author 王慧
 *
 */
public interface AddOrdersMapper {
	
	//用户发布订单
	@Insert ("insert into tb_order(ord_code,ord_send_time,ord_pick_code,ord_phone,ord_rec_name,ord_wight,ord_remark,ord_state,rec_id,add_id,com_id,u_id) "
			+ "values(#{ord_code},#{ord_send_time},#{ord_pick_code},#{ord_phone},#{ord_rec_name},#{ord_wight},#{ord_remark},#{ord_state},#{rec_id},#{add_id},#{com_id},#{u_id})")
	public void insertOrder(Order order);
	
	//查询快递信息
	@Select("select com_id,com_name from tb_company")
	List<Company> queryCom();
	
	//查询接收地址信息
	@Select("select add_id,add_detail from tb_address")
	List<Address> queryAdd();
	
	//查询接收时间信息
	@Select("select rec_id,rec_detail from tb_rec")
	List<Rectime> queryTime();
	
	
	
}