function alterForgetPass(){
	var http;
    var ownemail = document.getElementById("OwnEmail").value;
    var newpass = document.getElementById("vNewP").value;
    
    if(window.XMLHttpRequest){
        http = new XMLHttpRequest();
    }else{
        http = new ActiveXObject("Microsoft.XMLHTTP");
    }

    http.onreadystatechange=function()
    {
        if(http.readyState==4 && http.status==200){
            alert(http.responseText);
            //console.log("hhh");
           // window.location.href='';
        }
    }
    
    http.open("GET","AlterForgetPass?ownemail="+ownemail+"&newpass="+newpass,true);
    http.send();
}