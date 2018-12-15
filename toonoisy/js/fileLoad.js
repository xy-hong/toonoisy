/**
 * 功能：实现文件的上传控制和显示
 * 步骤：1、文件的获取及判断大小、Ajax上传。
 *       2、后台获取文件、进行存储、返回图片并显示。
 */

 function fileUpload(){
     var e = window.event||event;
     var fileMaxSize = 1024*1024*10;//10Mb
     var oFile = e.target.files[0];

     //控制文件大小
    if(oFile.size>fileMaxSize){
        document.getElementById("fileJudge").innerHTML = "* 上传的图片大小超过10Mb！";
        document.getElementById("fileJudge").style.display="block";
        document.getElementById("fileJudge").style.color = "pink";
    }

    // 限制文件类型
    var fileType = oFile.name.substr(oFile.name.lastIndexOf(".")+1);
    if (['doc', 'docx', 'pdf', 'txt', 'htm', 'html'].indexOf(fileType) < 0) {
        document.getElementById("fileJudge").innerHTML = "* 只支持.doc .docx  .pdf  .txt  .htm .html格式文件!";
        document.getElementById("fileJudge").style.display="block";
        document.getElementById("fileJudge").style.color = "pink";
        document.getElementById("fUButton").disabled = true;
    }else{
        document.getElementById("fUButton").disabled = false;
        document.getElementById("fileJudge").style.display="none";
    }

     
 }

 function fUpload(){
     var upFile = new XMLHttpRequest();
     var form = document.getElementById("fileForm");
     var data = new FormData(form);
    
     var username = document.getElementById("master").value;
     var room = currentRoomName;

     data.append("username",username);
     data.append("room",room);
     
     upFile.onreadystatechange = function(){
         if(upFile.readyState==4&&upFile.status==200){
            alert(upFile.responseText);
            document.getElementById("file").value="";
         }
     }
     upFile.open("POST","UpFile");
     upFile.send(data);

 }