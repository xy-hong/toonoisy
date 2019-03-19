function doPost(){
	var http;
    var ownemail = document.getElementById("OwnEmail").value;
    
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
    
    http.open("GET","DoPost?ownemail="+ownemail,true);
    http.send();
}