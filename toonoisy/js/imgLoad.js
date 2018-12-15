
/**
 * 功能：实现图片的上传控制和显示
 * 步骤：1、图片的获取及判断大小、Ajax上传。
 *       2、后台获取图片、进行存储、返回图片并显示。
 */

 function uploadImg(){
    var e = window.event||event;
    var imgMaxSize = 1024*1024*4;//4Mb
    var oFile = e.target.files[0];

    console.log(oFile);

    //检查上传的文件是否为图片

    if(['jpg','jpeg','png','gif'].indexOf(oFile.type.split("/")[1]) < 0){
        document.getElementById("imageJudge").innerHTML = "* 上传的文件格式必须为图片！";
        document.getElementById("imageJudge").style.display="block";
        document.getElementById("imageJudge").style.color = "pink";
    }else{
        document.getElementById("imageJudge").style.display="none";
        document.getElementById("imgButton").disabled=false;
    }
    
    //控制图片的大小
    if(oFile.size>imgMaxSize){
        document.getElementById("imageJudge").innerHTML = "* 上传的图片大小超过4Mb！";
        document.getElementById("imageJudge").style.display="block";
        document.getElementById("imageJudge").style.color = "pink";
    }

 }
 function upLoad(){
  
     var upImg = new XMLHttpRequest();
     var form = document.getElementById("imgForm");
     var data = new FormData(form);

     var username = document.getElementById("master").value;
     var room = currentRoomName;

     data.append("username",username);
     data.append("room",room);

     console.log(data);
     upImg.onreadystatechange = function(){
         if(upImg.readyState ==4 && upImg.status==200){
            alert(upImg.responseText);
            document.getElementById("image").value="";
         }
     }
     upImg.open("POST",'UpImage');
    // upImg.setRequestHeader("Content-type","application/form-data");
    // upImg.setRequestHeader("Content-type","application/x-www-form-urlencoded");
     upImg.send(data)
 }
 function refresh(){
     document.getElementById("image").value="";
 }