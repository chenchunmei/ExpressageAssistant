package com.remarkable.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.remarkable.entity.Address;
import com.remarkable.entity.Emp;

public interface AddressManagerMapper {
	
	@Select("select add_id,add_detail,add_state,emp_name from tb_address ta "
			+ " left join tb_emp te on te.emp_id = ta.emp_id ")
	List <Address> SelectAdd();
	
	@Delete("delete from  tb_address "
			+ " where add_id  = #{add_id}")
	int deleteAdd(@Param("add_id") Integer add_id);
	
	@Update("update  tb_address set add_state=#{add_state} where add_id=#{add_id}")
	int updateState(@Param("add_state") Integer add_state ,@Param("add_id") String add_id);
	
	@Select("select emp_name,emp_id from tb_emp")
	List<Emp> SelectEmpname();
	
	@Update("update  tb_address set emp_id=#{emp_id} where add_id=#{add_id}")
	int updateEmpid(@Param("emp_id") Integer emp_id ,@Param("add_id") String add_id);

}
