$(function(){
	
	window.$$=window.Zepto = Zepto;
	
	var app=new Vue({
		el:"#app",
		data:{
			emp:{},
		},
		methods: {
			cancel: function(pccode) {
				cancel();
			},
		},
		created:function(){
			loadList();
		}
	})
	
	function loadList(){
		$.post(server_url+"showEmp.action",function(result){
			var da = result.u_birthday;
		    da = new Date(da);
		    var year = da.getFullYear();
		    var month = da.getMonth()+1;
			var date = da.getDate();
			if(month < 10){
				var time = year+"-0"+month+"-"+date;
			}else{
				var time = year+"-"+month+"-"+date;
			}
			result.u_birthday = time;
			$("#u_birthday").val(time);
			app.emp = result;
			console.log(result)
		})
	}
	
	$("#back").click(function(){
		window.location.href="empset.html";
	})
	
	$("#update").click(function(){
		var emp_id=localStorage.getItem("emp_id");
		var emp_dormitory = $("#emp_dormitory").val();
		var emp_phone = $("#emp_phone").val();
		var u_birthday = $("#u_birthday").val();
		var emp={
				"emp_id":emp_id,
				"emp_dormitory":emp_dormitory,
				"emp_phone": emp_phone
		}
		$.ajax({
			type:"post",
			url:server_url+"updateEmp.action",
			data:emp,
			success:function(data){
				$$.toast("修改成功");
				loadList();
			}
		});
		
	});
	
	function cancel() {
		window.location.href="empset.html";
	}
		
});
