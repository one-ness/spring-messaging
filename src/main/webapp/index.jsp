<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<!--Import Google Icon Font-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--Import materialize.css-->
    	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
		
		<!--Let browser know website is optimized for mobile-->
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	</head>
	
	<body>
		<nav class="light-green lighten-1" role="navigation">
			<div class="nav-wrapper container">
				<a id="logo-container" href="#" class="brand-logo">Spring Questions</a>
			</div>
		</nav>
		<div id="questions">
			<div class="row"><div class="col s12">
				<div class="card grey-text">
					<div class="card-content center">
						<p>Spring WebSocket - ClientInbound Channel</p>
					</div>
				</div>
			</div></div>	
		</div>
		<button class="waves-effect waves-light btn right modal-trigger" data-target="newQuestionModal">
			<i class=material-icons>add</i>
		</button>
		<div id="newQuestionModal" class="modal">
			<div class="modal-header modal-close" style="display:flex;justify-content:flex-end;">
				<span><i class="material-icons">close</i></span>
			</div>
			<div class="modal-content">
				<p>
					<form accept-charset="ISO-8859-1" id="newQuestionForm" action="/new-question">
						<div class="input-field col s12">
							<i class="material-icons prefix">mode_comment</i>
							<textarea style="height:40px" id="question" name="question"></textarea>
							<label for="question">Questions</label>
						</div>
					</form>
				</p>
			</div>
			<div class="modal-footer">
				<button id="sendQuestionButton" class="btn waves-effect waves-light modal-close">
					<i class="material-icons right">send</i>
				</button>
			</div>
		</div>
		<script type="text/javascript" src="webjars/jquery/2.1.4/dist/jquery.min.js"></script>
		<!--JavaScript at end of body for optimized loading-->
		<script type="text/javascript" src="webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
		<script type="text/javascript" src="webjars/stomp-websocket/2.3.3/stomp.min.js"></script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
		<script type="text/javascript">
			var ws;
			var stompClient;
			$(function(){
				$('.modal').modal();
				$('#sendQuestionButton').click(function() {
					sendForm();
					$('#question').val('');
				});
				
				// Using SseEmitter in Java Controller to emit, EventSource jquery will listen for the
				// "spring" event, then run the function when the event is broadcast
				
				var source = new EventSource('/questions');
				source.addEventListener('spring', function(event) {
					$('#questions').prepend('<div class="row"><div class="col s12"><div class="card grey-text"><div class="card-content center>"<p>' + event.data + '</p></div></div></div></div>');
				}); 
				
				
				// Using Websocket
				/*
				ws = new WebSocket('ws://localhost:8080/questions');
				ws.onmessage = function(data) {
					$('#questions').prepend('<div class="row"><div class="col s12"><div class="card grey-text"><div class="card-content center>"<p>' + data.data + '</p></div></div></div></div>');
				} 
				*/

				// Using Websocket with SockJS to address compatibility issues
				/*
				ws = new SockJS("/questions");
				ws.onmessage = function(data) {
					$('#questions').prepend('<div class="row"><div class="col s12"><div class="card grey-text"><div class="card-content center>"<p>' + data.data + '</p></div></div></div></div>');
				} 
				*/
				
				// Using RabbitMQ as messagebroker
				/*
				ws = new SockJS("/questions");
				stompClient = Stomp.over(ws);
				stompClient.connect({}, function(frame) {
					stompClient.subscribe("/topic/questions", function(message) {
						$('#questions').prepend('<div class="row"><div class="col s12"><div class="card grey-text"><div class="card-content center>"<p>' + message.body + '</p></div></div></div></div>');
					});
					stompClient.subscribe("/user/queue/private", function(message) {
						M.toast({html:message.body, displayLength:6000});
					});
				}, function(error) {
					console.log("STOMP protocol error: " + error);
				}); 
				*/
 			});
			
			function sendForm() {
				// Using EventSource
			 	$.post('/new-question', $('#newQuestionForm').serialize());

				// Using websocket
				/* ws.send($('#question').val()); */
				
				// Using stompClient/RabbitMQ
				/* stompClient.send("/app/questions", {},$("#question").val()); */
			};
		</script>
	</body>
</html>
        