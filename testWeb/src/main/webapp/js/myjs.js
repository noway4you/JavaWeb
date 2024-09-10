window.onload = function(){
	let start = document.getElementById("start");
	let mesgDiv = document.getElementById("mesgDiv");
	let message = document.getElementById("message");
	let send = document.getElementById("send");
	let log = document.getElementById("log");
	
	let webSocket;
	
	start.addEventListener("click",function(){
		connect("ws://localhost:8080/testWeb/myserver");
	});
	
	send.addEventListener("click",function(){
		let mesg = {
			mesg:message.value
		};
		webSocket.send(JSON.stringify(mesg));
		message.value = "";	
	});
	
	start.style.display = "block";
	mesgDiv.style.display = "none";
	
	function connect(url){
		webSocket = new WebSocket(url);
				
		webSocket.onopen = function(event){
			console.log("onopen");
					
			start.style.display = "none";
			mesgDiv.style.display = "block";
		}
				
		webSocket.onclose = function(event){
			console.log("onclose");
					
			start.style.display = "block";
			mesgDiv.style.display = "none";
		}
		
		webSocket.onmessage = function(event){
			console.log(event.data);
			let backmessage = JSON.parse(event.data);
			log.innerHTML += backmessage.mesg + "<br>";
		}
						
		webSocket.onerror = function(event){
			console.log("onerror");
		}
	}
}