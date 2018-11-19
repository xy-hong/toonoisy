/*
	按钮控制60秒后重新发送发送
*/
var wTime = 60;

function setTime(obj){
	if(wTime==0){
		obj.removeAttribute("disabled");
		obj.value = "获取验证码";
		wTime = 60;
		return;
		}else{
			obj.setAttribute("disabled",true);
			obj.value = "(" + wTime + ")s后重新获取";
			wTime--;
			}
	setTimeout(function(){
		setTime(obj)
		},1000)
	
	}


/**
 * 验证两次密码是否一致
 */
function verification(){
	var password_1 = document.getElementById("newPassword").value;
	var password_2 = document.getElementById("aNewPassword").value;

	if(password_1 == password_2&&password_1!=""&&password_2!=""){
		document.getElementById("thisS").innerHTML = "&radic;&nbsp;密码输入一致！";
		document.getElementById("submit_signup").disabled = false;
		document.getElementById("thisS").style.color = "green";
	}
	else if(password_1!=password_2&&password_1!=""&&password_2!=""){
		document.getElementById("thisS").innerHTML = "*&nbsp;密码输入不一致！"
		document.getElementById("thisS").style.color = "red";
	}
	else{
		document.getElementById("thisS").innerHTML = "";
		document.getElementById("thisS").style.color = "red";
	}
}
function verificationEmail(){
	var strs = document.forms["changePasswordForm"]["OwnEmail"].value;
	var indexAite = strs.indexOf("@");
	var indexPoint = strs.indexOf(".");

	if(indexAite < 1|| indexPoint < indexAite+2 || indexPoint+2 >= strs.length){
		
	}
}