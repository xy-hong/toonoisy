/**
 * 将地址栏上传递的参数传给master显示框
 */
function initMaster(){
	var name = GetQueryString("username");
	document.getElementById("master").value = name;
}

function connectionWebsocket(){
	var name =  document.getElementById("master").value;
	online(name);
}


/**
* 获取好友列表的AJAX
*/
function getFriendList(){
	var username = document.getElementById("").value;
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			var tbody = document.getElementById("friendList");
			//先清空html列表中的内容
			tbody.innerHTML = "";
			var friendList = JSON.parse(request.responseText);
			console.log(friendList);
			var s = "";
			for(friend in friendList){
				console(friend);
				s += "<tr><td>"+friend.friend_id+"</td><td>"+friend.friend_nick+"</td></tr>";				
			}
			
			tbody.innerHTML = s;
			
		}
	}
	
	request.open("POST","URL",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var data = "username="+username;
	request.send(data);
	
}

/**
* 修改密码的AJAX
*/
function changePassword(){
	var username = document.getElementById("").value;
	var oldPassword = document.getElementById("").value;
	var newPassword = document.getElementById("").value;
	
	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			alert(request.responseText);
		}
	}
	
	request.open("POST","URL",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var data = "username="+username+"&oldPassword="+oldPassword+"&newPassword="+newPassword;
	request.send(data);
	
}


/**
 * 查询URL的对应Name参数的值
 * @param name
 * @returns
 */
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}