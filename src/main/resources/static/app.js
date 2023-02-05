var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chat', function (greeting) {
            console.log(greeting);
            showGreeting(JSON.parse(greeting.body));
        });

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    let roomId = $('#roomId').val();
    stompClient.send("/chat/" + roomId, {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
//    console.log(message);
//    console.log(message.name);
//    console.log(JSON.parse(message));
    $("#greetings").append("<tr><td>" + message.name + "</td></tr>");
}

function createChatRoom() {
    const name = $("#chatRoomName").val();
    $.ajax({
        url: "/chat/createRoom",
        data: { 'name': name },
        type: "POST",
        success: function(data) {
            $('#chatRoomsList').append("<tr><td>" + JSON.stringify(data) + "</td></tr>");
            alert('만듬');
        }
    });
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#createChatRoom").click(function() { createChatRoom() });
});