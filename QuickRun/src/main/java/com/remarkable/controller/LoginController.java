package com.remarkable.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.remarkable.entity.Admin;
import com.remarkable.entity.Emp;
import com.remarkable.entity.User;
import com.remarkable.service.ILoginService;

/**
 * 登录控制层
 * @author 向林俊
 *
 */
@Controller
public class LoginController {

	@Autowired
	private ILoginService loginService;
	
	/**
	 * 根据管理员账号和密码查询
	 * @param adm_account
	 * @param adm_pwd
	 * @return
	 */
	@RequestMapping("/loginAdmin.action")
	public @ResponseBody int selAdminByAccAndPwd(@RequestParam("adm_account") String adm_account, @RequestParam("adm_pwd") String adm_pwd) {
		Admin admin = loginService.selAdminByAccAndPwd(adm_account, adm_pwd);
		if(admin!=null) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * 骑手登录
	 * @param emp_sno 学号
	 * @param pwd 密码
	 * @return
	 */
	@RequestMapping("/loginEmp.action")
	public @ResponseBody Emp loginEmp(@RequestParam("emp_sno") String emp_sno, @RequestParam("pwd") String pwd) {
		Emp emp = loginService.loginEmp(emp_sno,pwd);
		return emp;
	}
	
	/**
	 * 用户登录
	 * @param phone
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/login.action")
	public @ResponseBody int login(@RequestParam("phone") String phone
			,@RequestParam("pwd") String pwd) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(phone,pwd);
		try {
			//System.out.println("到这来了吗？");
			currentUser.login(token);
			return 1;
		}catch(AuthenticationException ae) {
			return 0;
		}
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/loginOut.action")
	public @ResponseBody int loginOut(){
		//创建一个认证主体
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return 1;
	}
	
	@RequestMapping("/getUser.action")
	public @ResponseBody User getUser(HttpSession session) {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getPrincipal();
		//int u_id = user.getU_id();
		//System.out.println("当前用户的u_id+++++++++++"+u_id);
		//System.out.println("user  : " + user);
		return user;
	}
	
	@RequestMapping("/register.action")
	public @ResponseBody int register(@RequestParam("phone") String phone,@RequestParam("pwd") String pwd) {
		//判断验证码是否正确，如果正确，就执行注册
		int result = loginService.register(phone, pwd);
		if(result == 0) {//注册失败
			return 0;
		}else {//注册成功
			return 1;
		}
	}
	
	@RequestMapping("/UserUpdatePwd")
	@ResponseBody
	public Integer updateUserPwd(@RequestParam("u_id")Integer u_id,@RequestParam("u_pwd")String u_pwd,@RequestParam("u_oldPwd")String u_oldPwd,HttpServletResponse res){
		//跨域设置
		res.setHeader("Access-Control-Allow-Origin", "*");
		Integer count=loginService.updateUserPwd(u_id, u_pwd,u_oldPwd);
		//成功返回1 失败返回0，用于前端提示用户信息
		return count;
	}
	
	
	@RequestMapping("/returnEmpPwd.action")
	@ResponseBody
	public Integer returnEmpPwd(@RequestParam("emp_sno")String emp_sno,@RequestParam("repeatpwd")String repeatpwd){
		Integer count=loginService.returnEmpPwd(emp_sno,repeatpwd);
		//成功返回1 失败返回0，用于前端提示用户信息
		return count;
	}
	
	@RequestMapping("/returnPwd.action")
	@ResponseBody
	public Integer returnPwd(@RequestParam("phone")String phone,@RequestParam("repeatpwd")String repeatpwd){
		Integer count=loginService.returnEmpPwd(phone,repeatpwd);
		//成功返回1 失败返回0，用于前端提示用户信息
		return count;
	}
	
	@RequestMapping("/EmpUpdatePwd.action")
	@ResponseBody
	public Integer updateEmpPwd(@RequestParam("emp_id")Integer emp_id,@RequestParam("emp_pwd")String emp_pwd,@RequestParam("emp_oldPwd")String emp_oldPwd,HttpServletResponse res,HttpServletRequest request){
		//跨域设置
		res.setHeader("Access-Control-Allow-Origin", "*");
		Integer count=loginService.updateEmpPwd(emp_id, emp_pwd,emp_oldPwd);
		//成功返回1 失败返回0，用于前端提示用户信息
		return count;
	}
}





