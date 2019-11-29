document.querySelector('.chat[data-chat=person1]').classList.add('active-chat');
document.querySelector('.person[data-chat=person1]').classList.add('active');
var endpoint = 'ws://' + location.host + '/endpoint';
var subscribePrefix = '/topic/';
var stompClient = null;

let friends = {
  list: document.querySelector('ul.people'),
  all: document.querySelectorAll('.left .person'),
  name: '' },

chat = {
  container: document.querySelector('.chat-container .right'),
  current: null,
  person: null,
  name: document.querySelector('.chat-container .right .top .name') };


friends.all.forEach(f => {
  f.addEventListener('mousedown', () => {
    f.classList.contains('active') || setAciveChat(f);
  });
});

function setAciveChat(f) {
	  $("#messageList").empty();
	  
	  stompClient = Stomp.over(new WebSocket(endpoint));
	  stompClient.connect({}, function() {
	    stompClient.subscribe(
	        subscribePrefix + $('#roomName').val(),
	        function(message) {
	          $('.chat').prepend($('<div class="bubble you">').text(message.body));
	        });

	    $('#roomName').prop('disabled', true);
	    $('#connectButton').prop('disabled', true);
	    $('#disconnectButton').prop('disabled', false);
	  });
	
  friends.list.querySelector('.active').classList.remove('active');
  f.classList.add('active');
  chat.current = chat.container.querySelector('.active-chat');
  chat.person = f.getAttribute('data-chat');
  chat.current.classList.remove('active-chat');
  chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');
  friends.name = f.querySelector('.name').innerText;
  chat.name.innerHTML = friends.name;
  

}

$('#sendButton').click(function() {
    if (!stompClient) {
      alert('未接続です。');
      return;
    }

    stompClient.send(subscribePrefix + $('#roomName').val(), {}, $('#message').val());
  });