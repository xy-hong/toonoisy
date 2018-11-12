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

// $("ul li").mouseover(function(){
//     $(this).stop().animate({height:114},200);
//     $(this).siblings().stop().animate({height:34},200);
// })

