
var socket = new WebSocket("ws://localhost:80/mysocket");

socket.onopen = function(){
    alert("ws connect");
}

socket.onclose = function(){
    alert("ws close")
}

socket.onmessage = function(event){
    alert(event.data)
}

socket.onerror = function(){
    alert("error happen");
}

socket.send(data);