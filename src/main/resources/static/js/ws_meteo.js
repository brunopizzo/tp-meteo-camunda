var stompClient = null;

function connect() {
    var socket = new SockJS('/meteo');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/meteo', function (greeting) {
            meteoAnswer(JSON.parse(greeting.body));
        });
    });
}

function meteoRequest(pid) {
    stompClient.send("/app/meteo", {}, JSON.stringify({'postalCode': $("#cp").val(), 'pid': pid}));
}

function meteoAnswer(answer) {
    $("#meteo-answer").html(answer.content);
}

connect();