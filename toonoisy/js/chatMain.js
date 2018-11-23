function verification(){
    var fPassword = document.getElementById("newPassword").value
    var sPassword = document.getElementById("aNewPassword").value

    if(fPassword == sPassword){
        document.getElementById("thisS").innerHTML = "&radic; 两次密码输入一致";
        document.getElementById("thisS").style.color="green";
        document.getElementById("submit").disabled = false;
    }else if(fPassword==""&&sPassword==""){
        document.getElementById("thisS").style.display="none";
    }else{
        document.getElementById("thisS").innerHTML = "* 两次密码输入不一致";
        document.getElementById("thisS").style.color="red";
        document.getElementById("submit").disabled = true;
    }
}
$(document).ready(function(){
    $("#emotion").click(function(){
        $("#functionArea").hide();
    })
})

function changecolor() {


    var getcolor = document.getElementById("fontColor").value;
    var texts =  document.getElementsByClassName("myText");
    for(var i=0; i<texts.length; i++){
        texts[i].style.color = getcolor;
    }
    
}

/***
 * 动态增加tabs标签页
 */
function addTabFunction(){
   
   // var tabTitle = $("#tab_Title");//获取房间标签节点
  //  var tabtitle = tabTitle.val();
	var tabtitle = document.getElementById("tab_Title").value;
    
    //标签页格式
 //   tabTemplate = "<li id="+tabtitle+"><a href='#{href}' data-toggle='tab' >#{tabtitle}<span class='glyphicon glyphicon-remove' style='cursor:pointer;' onclick='parent().id'></span></a></li>";

    //ul id mytab  div id screen
	
	
    var div_pane = tabtitle+'_screen';
    document.getElementById("screen").innerHTML+="<div id='"+div_pane +"' class='tab-pane'></div>"
    document.getElementById("mytab").innerHTML+="<li  id='"+ tabtitle +"'><a href='#"+div_pane + "' data-toggle='tab' onclick='changeRoomName(parentElement.id)' >"+tabtitle+"<span class='glyphicon glyphicon-remove' style='cursor:pointer;' onclick='removeTab(parentElement.parentElement.id)'></span></a></li>";
    
    
    document.getElementById(div_pane).scrollIntoView(false);
    
}
/**
 * 关闭标签页 
 */
function removeTab(roomName){

    document.getElementById(roomName).remove();
    document.getElementById(roomName+"_screen").remove();
}

function displayBottom(roomName){
	document.getElementById(roomName).scrollIntoView(false);
}

/**

 * 菜单栏

 */
var isOpen = false;

function floatMune(Id){
	var div_id = Id+"_div"
    var blockUl = document.getElementById(div_id);
   
        isOpen = !isOpen;

        if(isOpen){ 

          blockUl.style.display="block";

        }else{

          blockUl.style.display = 'none';

        }

}

/**

 * 定位鼠标的位置

 */



function mousePosition(Id){
	var div_id = Id+"_div"
    var odiv = document.getElementById(div_id);
    var obj = document.getElementById("functionArea");
    var oEvent = event;


    odiv.style.top = oEvent.clientY-obj.offsetTop +'px';

    odiv.style.left = oEvent.clientX-obj.offsetLeft +'px';



}
	

