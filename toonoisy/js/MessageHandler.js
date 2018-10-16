/**
 * 
 */
function messageHandler(JSONString){
	var obj = JSON.parse(JSONString);
	switch (obj.type)
    {
  		case "text":textHandler(obj);
    	break;
  		default:
  	}
}

function textHandler(obj){
	var screen = document.getElementById("screen");
	screen.value += obj.data + "\n";
}

function imageHandler(obj){
	
}

function fileHandler(obj){
	
}