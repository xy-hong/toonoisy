/**
 * @description 信息处理，传入JSON类型的字符串
 * @param {String} JSONString 
 */
function messageHandler(JSONString){
	var message = JSON.parse(JSONString);
	switch (message.type)
    {
		  case "text":textHandler(message);
		  //case : ;
    	break;
  		default:
  	}
}


/**
 * 处理
 * @param {*} message
 */
function textHandler(message){
	var screen = document.getElementById("screen");
	screen.innerHTML += "<div>"+ message.creatTime+" "+message.send+":"+message.data+"</div>";
}

/**
 * @todo
 * @param {} message
 */
function imageHandler(message){
	
}

/**
 * @todo
 * @param {*} message
 */
function fileHandler(message){
	
}