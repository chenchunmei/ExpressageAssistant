
function ps() {
	if(this.loginFrom.pwd.type = "password")
		box.innerHTML = "<input type=\"html\" name=\"pwd\" size=\"20\" value=" + this.loginFrom.pwd.value + ">";
	click.innerHTML = "<a href=\"javascript:txt()\" class=\"iconfont icon-open-eye\"></a>"
}

function txt() {
	if(this.loginFrom.pwd.type = "text")
		box.innerHTML = "<input type=\"password\" name=\"pwd\" size=\"20\" value=" + this.loginFrom.pwd.value + ">";
	click.innerHTML = "<a href=\"javascript:ps()\" class=\"iconfont icon-biyan\"></a>"
}

function loginAdmin() {
	
	var adm_account = $("#account").val();
	if(adm_account == "") {
		alert("手机号不能为空！");
		return;
	};
	var adm_pwd = $("#pwd").val();
	if(adm_pwd == "") {
		alert("密码不能为空！");
		return;
	}

	$.ajax({
		type: "POST",
		url: "http://127.0.0.1:8888/QuickRun/loginAdmin.action",
		data: {
			adm_account : adm_account,
			adm_pwd : adm_pwd
		},
		
		/*用于跨域处理*/
		xhrFields:{
	        withCredentials:true
	    },
	    crossDomain: true,
		dataType: "JSON",
		success: function(result) {
			
			if(result == 1) {
				//跳转到成功页面
				location.href = "../../adminV/index.html";
			} else {
				alert("账号或者密码不正确!");
			}
		}
	});
}