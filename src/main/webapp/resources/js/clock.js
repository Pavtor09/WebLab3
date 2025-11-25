function updateTime(){
    var now = new Date();
    var timeStr = now.getHours().toString().padStart(2, '0') + ':' +
                  now.getMinutes().toString().padStart(2, '0') + ':' +
                  now.getSeconds().toString().padStart(2, '0');
    // Плавно скрываем, меняем значение, проявляем
    $("#clock").fadeOut(200, function() {
        $(this).text(timeStr).fadeIn(200);
    });
}

window.onload = function(){
    // Сначала вызываем сразу при загрузке
    updateTime();
    console.log("js here");


    setInterval(updateTime, 7000);
}