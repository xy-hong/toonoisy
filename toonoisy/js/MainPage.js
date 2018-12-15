/**
 * 将地址栏上传递的参数传给master显示框
 */
function initMaster(){
	var name = GetQueryString("username");
	console.log("你的名字"+name);
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
	var username = document.getElementById("master").value;
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			var tbody = document.getElementById("friendList");
			//先清空html列表中的内容
			tbody.innerHTML = "";
			var friendList = JSON.parse(request.responseText);
			console.log(friendList);
			var s = "";
			for(var i=0; i< friendList.length; i++){
				//console.log(friend);
				s += "<tr><td><a href='#' id='"+friendList[i].friend_id+"' onclick='floatMune(this.id),mousePosition(this.id)'>"+friendList[i].friend_id+"</td><td>"+friendList[i].friend_nick+"</a></td><div class='mune' id='"+friendList[i].friend_id+"_div'><a href='#' onclick=invite('"+friendList[i].friend_id+"')>邀请进入房间</a><a href='#'>修改备注</a></div></tr>";				
			}
			
			tbody.innerHTML = s;
			
		}
	}
	
	request.open("POST","FriendList",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var data = "username="+username;
	request.send(data);
	
}

/**
* 修改密码的AJAX
*/
function alterPassword(){
	var username = document.getElementById("master").value;
	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;
	
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			alert(request.responseText);
		}
	}
	
	request.open("POST","AlterPassword",true);
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

/**
 * 
*/
/**
 * 添加emoji到文本输入框
 */
function addEmoji(emoji){
	document.getElementById("input").value+=emoji.innerHTML;
}

/**
 * 添加用户关注
 * @returns
 */
function followFriend(){
    var http;
    var friendid = document.getElementById("followId").value;
    var master = document.getElementById("master").value;
    
    if(window.XMLHttpRequest){
        http = new XMLHttpRequest();
    }else{
        http = new ActiveXObject("Microsoft.XMLHTTP");
    }

    http.onreadystatechange=function()
    {
        if(http.readyState==4 && http.status==200){
            alert(http.responseText);
        }
    }
    
    http.open("GET","FollowFriends?username="+master+"&friendid="+friendid,true);
    http.send();

}

