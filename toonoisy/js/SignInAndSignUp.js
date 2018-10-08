//隐藏注册模块
$(document).ready(function(){
	$("#registerMain_div").hide();
	});
//显示注册模块同时隐藏登陆模块
$(document).ready(function(){
	$("#register_link").click(function(){
		$("#loginMain_div").hide();
		$("#registerMain_div").show();
		});
	});
//显示登陆模块同时隐藏注册模块
$(document).ready(function(){
	$("#login_link").click(function(){
		$("#registerMain_div").hide();
		$("#loginMain_div").show();
		});
	
	});
//立即注册显示注册模块
$(document).ready(function(){
	$("#register_now").click(function(){
		$("#loginMain_div").hide();
		$("#registerMain_div").show();
		$("#tologin").css("background-color","#f3f2f2f3");
		$("#toregister").css("background-color","white");
		});
	});

//控制登陆注册的背景颜色
$(document).ready(function(){
	$("#toregister").css("background-color","#f3f2f2f3");
});

$(document).ready(function(){
	$("#register_link").click(function(){
		$("#tologin").css("background-color","#f3f2f2f3");
		$("#toregister").css("background-color","white");
	});
	$("#login_link").click(function(){
		$("#toregister").css("background-color","#f3f2f2f3");
		$("#tologin").css("background-color","white");
	});

});
