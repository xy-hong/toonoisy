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

//控制发送时间
var Ltime = 60;

function setTime(obj){


	
    if(Ltime==0){
		obj.removeAttribute("disabled");
		obj.value = "获取验证码";
		Ltime = 60;
		return;
		}else{
			obj.setAttribute("disabled",true);
			obj.value = "(" + Ltime + ")s后可重获取";
			Ltime--;
			}
	setTimeout(function(){
		setTime(obj)
		},1000)
	
	}

// 验证两次密码是否输入一致

function verificationPw(){
	var fPassword = document.getElementById("password_signup").value;
	var sPassword = document.getElementById("password_signup_2").value;

	if(fPassword == sPassword){
		document.getElementById("thisS").innerHTML = "&radic; 密码输入一致";
		document.getElementById("submit_signup").disabled = false;
		document.getElementById("thisS").style.color = "green";
	}else if(fPassword==""&&sPassword==""){
		document.getElementById("thisS").style.display="none";
	}else{
		document.getElementById("thisS").innerHTML = "*  密码输入不一致"
		document.getElementById("submit_signup").disabled = true;
		document.getElementById("thisS").style.color = "red";
	}

}
//验证表单email是否正确(意思就是说，输入的数据必须包含 @ 符号和点号(.)。
//同时，@ 不可以是邮件地址的首字符，并且 @ 之后需有至少一个点号：)
function verifyingEmail(){
	//username_signup
	var str2 =  document.forms["myform"]["username_signup"].value;
	var indextAite = str2.indexOf("@");
	var indextPoint = str2.lastIndexOf(".");

	if(indextAite < 1 || indextPoint<indextAite+2 || indextPoint+2 >= str2.length){
		document.getElementById("emailAlert").innerHTML="* 请输入正确的邮箱格式(如：xx@qq.com)"
		document.getElementById("emailAlert").style.color = "red";
		document.getElementById("emailAlert").style.display="block";
		document.getElementById("getVerification").disabled=true;
	}else{
		document.getElementById("emailAlert").style.display="none";
		document.getElementById("getVerification").disabled=false;
	}

}

//ajax控制邮箱获取验证码
$(document).ready(function(){
	$("#getVerification").click(function(){
		$.ajax({
			type:"POST",
			url:"",
			data:{username_signup:$("#username_signup").val()},
			dataType:"json",
			success:function(data){
				if(data.success){
					alert(data.msg);
				}else{alert("发生错误"+data.msg);}
			},error:function(jqXHR){
				alert("发生错误"+jqXHR.status)
			}
		});
	});
});