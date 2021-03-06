$(function(){
	
	window.$$=window.Zepto = Zepto;
	
	var app=new Vue({
		el:"#app",
		data:{
			list:{},
		},
		methods:{
			
		},
		created:function(){
			loadList();
		}
	})
	
	function loadList(){
		var u_id=current_id;
		$.post(server_url+"showUser.action",{"u_id":u_id},function(result){
			app.list = result.user;
			var srcpath=result.ima_address;
			var path="http://127.0.0.1:8888/QuickRun/"+srcpath.substr(srcpath.indexOf("upload"))
			$("#img").attr("src",path)
		});
	}
		
	$("#btn").click(function(){
		if($("#menu").css("display")=="block"){
			$("#menu").css("display","none");
		}else{
			$("#menu").css("display","block");
		}
	});
	
	$(document).on('click','.create-actions', function () {
      var buttons1 = [
        {
          text: '退出登录',
          label: true
        },
        {
          text: '退出',
          bold: true,
          color: 'danger',
          onClick: function() {
		      $$.confirm('你确定退出吗?', function () {
		      	 $.post(server_url+"loginOut.action",function(result){
		      	 	if(result==1){
		      	 		location.href = "userV/login/login.html";
		      	 	}
				});
		      });
          }
        }
      ];
      var buttons2 = [
        {
          text: '取消',
          bg: 'danger'
        }
      ];
      var groups = [buttons1, buttons2];
      $$.actions(groups);
  });
	 
});