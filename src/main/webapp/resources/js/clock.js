function updateTime(){
    var now = new Date();
    var timeStr = now.getHours().toString().padStart(2, '0') + ':' +
                  now.getMinutes().toString().padStart(2, '0') + ':' +
                  now.getSeconds().toString().padStart(2, '0');
    $("#clock").fadeOut(200, function() {
        $(this).text(timeStr).fadeIn(200);
    });
}

window.onload = function(){
    updateTime();
    console.log("js here");


    setInterval(updateTime, 7000);
}