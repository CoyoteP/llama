var endpoint = 'ws://' + location.host + '/endpoint';
var subscribePrefix = '/topic/';
var stompClient = null;
var studentId = $('#studentUserId').val();
var teacherId = $('#teacherUserId').val();
var socket = new SockJS('/endpoint');


  var socket = new SockJS('/endpoint');

  stompClient = Stomp.over(socket);
  stompClient.connect({}, function() {
    stompClient.subscribe(
        subscribePrefix + 'endpoint/' + studentId + "/" + teacherId,
        function(message) {
          $('#messageList').append($('<div class="bubble me">').text(message.body));
        });

    $('#roomName').prop('disabled', true);
    $('#connectButton').prop('disabled', true);
    $('#disconnectButton').prop('disabled', false);
  });

$('#disconnectButton').click(function() {

  stompClient.disconnect();
  stompClient = null;

  $('#roomName').prop('disabled', false);
  $('#connectButton').prop('disabled', false);
  $('#disconnectButton').prop('disabled', true);
});

$('#sendButton').click(function() {
  if (!stompClient) {
    alert('未接続です。');
    return;
  }

  stompClient.send("/app/endpoint/" + studentId + "/" + teacherId, {},JSON.stringify({'message': $('#message').val()}) );
});