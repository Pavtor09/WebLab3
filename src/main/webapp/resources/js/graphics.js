// document.querySelector('.input-form').addEventListener('submit', function (e) {
//   // e.preventDefault(); // Отключаем стандартную отправку формы
//   // Показываем стандартную ошибку формы если данные невалидны
//   // Получаем значение Y
//   const yValue = document.querySelector('input[name="y"]').value;

//   // Получаем выбранное значение R
//   const rValue = document.querySelector('input[name="r"]').value;



//   // Проверка именно интервала (-5; 5)
//   if (isNaN(yValue) || yValue <= -5 || yValue >= 5) {
//     alert("Y должен быть внутри интервала (-5; 5)");
//     return;
//   }

//   // Получаем все отмеченные чекбоксы X
//   const xValue = document.querySelector('select[name="x"]').value;

//   // Чекаем, что всё прочитали
//   console.log('X:', xValue);
//   console.log('Y:', yValue);
//   console.log('R:', rValue);


//   // drawFigure(rValue);//строим график
// });



var points = []; //сюда пихнём массив с результатами из jsp для рисования точек


//рисовалка графика
window.drawFigure = function(rawR) {
  const canvas = document.getElementById('myCanvas');
  const upscale = 30;

  //не позволяем уродовать наш график
  if (rawR > 5) { rawR = 5; }
  if (rawR < 1) { rawR = 0; }

  let R = rawR * upscale
  const ctx = canvas.getContext('2d');
  ctx.clearRect(0, 0, canvas.width, canvas.height);


  // Центр координат
  const cx = canvas.width / 2;
  const cy = canvas.height / 2;

  ctx.fillStyle = '#379cff';

  // Рисуем четверть круга
  ctx.beginPath();
  ctx.moveTo(cx, cy);
  ctx.arc(cx, cy, R, -Math.PI / 2,-2*Math.PI , false);
  ctx.lineTo(cx, cy);
  ctx.closePath();
  ctx.fill();

  // Рисуем прямоугольник слева внизу
  ctx.beginPath();
  ctx.moveTo(cx, cy);
  ctx.rect(cx - R, cy, R, R/2);
  ctx.closePath();
  ctx.fill();

  // Рисуем треугольник справа снизу
  ctx.beginPath();
  ctx.moveTo(cx, cy);
  ctx.lineTo(cx + R / 2, cy);
  ctx.lineTo(cx, cy + R / 2);
  ctx.closePath();
  ctx.fill();

  drawGrid(ctx, cx, cy, upscale, 1, canvas.width, canvas.height);
  // Оси координат
  ctx.strokeStyle = "black";
  ctx.lineWidth = 2;

  // x
  ctx.beginPath();
  ctx.moveTo(0, cy);
  ctx.lineTo(canvas.width, cy);
  ctx.stroke();

  // y
  ctx.beginPath();
  ctx.moveTo(cx, 0);
  ctx.lineTo(cx, canvas.height);
  ctx.stroke();

  // Подписи R, R/2

  if (rawR > 0) {
    ctx.fillStyle = "#000";
    ctx.font = "12px serif";
    ctx.fillText(-rawR, cx - R, cy + 12);
    ctx.fillText(rawR, cx + R - 15, cy + 12);
    ctx.fillText(-rawR / 2, cx - R / 2, cy + 12);
    ctx.fillText(rawR / 2, cx + R / 2 - 15, cy + 12);

    ctx.fillText(-rawR, cx + 2, cy + R);
    ctx.fillText(rawR, cx + 2, cy - R + 12);
    ctx.fillText(rawR / 2, cx + 2, cy - R / 2 + 12);
    ctx.fillText(-rawR / 2, cx + 2, cy + R / 2 + 12);
  }
}

function drawGrid(ctx, cx, cy, scale, step, canvasWidth, canvasHeight) {

  ctx.save(); // Сохраняем текущие настройки
  ctx.strokeStyle = "#cccccc"; // Цвет сетки
  ctx.lineWidth = 1;

  // Вертикальные линии
  for (let x = cx; x <= canvasWidth; x += step * scale) {
    ctx.beginPath();
    ctx.moveTo(x, 0);
    ctx.lineTo(x, canvasHeight);
    ctx.stroke();
  }
  for (let x = cx - step * scale; x >= 0; x -= step * scale) {
    ctx.beginPath();
    ctx.moveTo(x, 0);
    ctx.lineTo(x, canvasHeight);
    ctx.stroke();
  }

  // Горизонтальные линии
  for (let y = cy; y <= canvasHeight; y += step * scale) {
    ctx.beginPath();
    ctx.moveTo(0, y);
    ctx.lineTo(canvasWidth, y);
    ctx.stroke();
  }
  for (let y = cy - step * scale; y >= 0; y -= step * scale) {
    ctx.beginPath();
    ctx.moveTo(0, y);
    ctx.lineTo(canvasWidth, y);
    ctx.stroke();
  }


  ctx.strokeStyle = "#000";
  ctx.lineWidth = 2;

  // Отметки на оси X
  for (let x = -canvasWidth; x <= canvasWidth; x += step * scale) {
    ctx.beginPath();
    ctx.moveTo(x, cy - 5); // верхняя точка палочки
    ctx.lineTo(x, cy + 5); // нижняя точка палочки
    ctx.stroke();
  }

  // Отметки на оси Y
  for (let y = -canvasHeight; y <= canvasHeight; y += step * scale) {
    ctx.beginPath();
    ctx.moveTo(cx - 5, y); // левая точка палочки
    ctx.lineTo(cx + 5, y); // правая точка палочки
    ctx.stroke();
  }

  ctx.restore();
}


window.onload = onStart();

function onStart() {
  let R = document.getElementById('main-form:r-select');
  drawFigure(R.value);       //рисуем сетку при загрузке
  // restoreParams(); // берём сохранённые значения x y r
  // drawDots(); 

}



//слушатель нажатий на график 
document.getElementById('myCanvas').addEventListener('click', function (event) {
  const canvas = document.getElementById('myCanvas');
  const rect = canvas.getBoundingClientRect();

  // // Координаты клика
  // const clickX = event.clientX - rect.left;
  // const clickY = event.clientY - rect.top;

  // // Центр координат
  // const cx = canvas.width / 2;
  // // const cy = canvas.height / 2;
  // const cy = 200;

  // const upscale = 30;
  // const graphX = (clickX - cx) / upscale;
  // const graphY = (cy - clickY) / upscale;





     // Координаты клика относительно окна браузера и canvas
    const clientX = event.clientX;
    const clientY = event.clientY;
    const canvasX = clientX - rect.left;
    const canvasY = clientY - rect.top;
    
    // Центр canvas
    const cx = canvas.width / 2;
    const cy = canvas.height / 2;
    const upscale = 30;
    
    // Преобразование в математические координаты
    const graphX = (canvasX - cx) / upscale;
    const graphY = (cy - canvasY) / upscale;



  console.log('Clicked coordinates graph:', graphX, graphY);

  CanvasSet(graphX.toFixed(2),graphY.toFixed(2),false);
  CanvasSend();

  const xButtons = document.querySelectorAll('.x-btn');
  let selectedX = null;
  
  xButtons.forEach(button => {
      if (button.classList.contains('active')) {
          selectedX = button.value;
      }
      drawPointsFromBean();
  });
  const yInput = document.getElementById('main-form:y-input');
  const yValue = yInput ? yInput.value : "";
    
  CanvasSet(selectedX, yValue,true)

});

function CanvasSet(x, y,validation) {
    document.getElementById('main-form:clickX').value = x;
    document.getElementById('main-form:clickY').value = y;
    document.getElementById('main-form:setValidation').value = validation;
    document.getElementById('main-form:canvasClickBtn').click();



}

function CanvasSend()
{
    document.getElementById('main-form:send').click();
}



//Переделать слушателей на новые поля 

// document.querySelector('input[name=r]').addEventListener('input', function () { const rVal = this.value; drawFigure(rVal); localStorage.setItem('r', rVal); drawDots() }); // слушаем ввод R и рисуем график и сохраняем R
// document.querySelector('select[name=x]').addEventListener('change', function () { const xVal = this.value; localStorage.setItem('x', xVal) }); // слушаем ввод X и сохраняем
// document.querySelector('input[name=y]').addEventListener('input', function () { const yVal = this.value; localStorage.setItem('y', yVal) }); // слушаем ввод Y и сохраняем

//достаём сохранённые значения
// function restoreParams() {
//   document.querySelector('input[name=r]').value = localStorage.getItem('r');
//   drawFigure(localStorage.getItem('r'));
//   document.querySelector('select[name=x]').value = localStorage.getItem('x');
//   document.querySelector('input[name=y]').value = localStorage.getItem('y');
// }



// if (document.getElementById('closeBtn') != null) {
//   document.getElementById('closeBtn').onclick = function () { // крестик у ошибки
//     document.getElementById('popup').style.display = 'none';
//     window.location.replace(window.location.href.replace("/controller", ""));
//   }
// }





// document.getElementById('clear').addEventListener('click', () => {
//   clearTable();

// });

function getPoint(res) {
  points.push(res);  //берём наши точки из jsp

}


  window.drawDot = function(x,y,hit)//рисуем точки
  {
    console.log("js drawdot is running")
    const canvas = document.getElementById('myCanvas');
    const upscale = 30;
    const ctx = canvas.getContext('2d');
    const cx = canvas.width / 2;
    const cy = canvas.height / 2;
    ctx.save();
    ctx.translate(cx, cy);
    let px = upscale * x;
    let py = -upscale * y;
    ctx.beginPath();
    ctx.arc(px, py, 4, 0, 2 * Math.PI);
    ctx.fillStyle = hit ? "green" : "red";
    ctx.fill();
    ctx.strokeStyle = "black";
    ctx.stroke();
    ctx.restore();



  }
// window.drawDot = function(x,y,hit)//рисуем точки
// {
//   console.log("js drawdot is running")
//   const canvas = document.getElementById('myCanvas');
//   const upscale = 30;
//   const ctx = canvas.getContext('2d');
//   const cx = canvas.width / 2;
//   const cy = canvas.height / 2;
//       ctx.save();
//       ctx.translate(cx, cy);
//       let px = upscale * x;
//       let py = -upscale * y;
//       ctx.beginPath();
//       ctx.arc(px, py, 4, 0, 2 * Math.PI);
//       ctx.fillStyle = hit ? "green" : "red";
//       ctx.fill();
//       ctx.strokeStyle = "black";
//       ctx.stroke();
//       ctx.restore();
    

  
// }

function clearTable() {
  // тут надо будет написать запрос к серверу на очистку таблицы (ну или не надо, я сделал через форму)
}



var pickedX  = [0,0,0,0,0,0,0,0,0]
document.querySelectorAll('.x-btn').forEach(btn => {
    btn.addEventListener('click', function(event) {
        // Ваш код: например, менять стиль кнопки
        if (pickedX[parseInt(this.value)+4] == 0)
        {
          btn.classList.add('active');
          pickedX[parseInt(this.value)+4] = 1
          document.querySelectorAll('.x-btn').forEach(other_btn => {if (other_btn.value != btn.value){pickedX[parseInt(other_btn.value)+4] = 0; other_btn.classList.remove('active')}})
        }
        // else{
        //   pickedX[parseInt(this.value)+4] = 0
        //   btn.classList.remove('active');
        // }
        
        // Или сохранить значение, отменить отправку формы и пр.
        event.preventDefault(); // если надо отменить отправку
        console.log('Выбрана кнопка X:', btn.value);
    });
});
window.drawDots = function(data)
{
  if(data.status === 'success')
  {
    drawPointsFromBean();
  }
}
// document.addEventListener('DOMContentLoaded', function() {
//     var rSelect = document.getElementById('main-form:r-select'); // обратите внимание на полный id
    
//     if (rSelect) {
//         rSelect.addEventListener('change', function() {
//             drawFigure(this.value)
//             // Ваш код здесь
//         });
//     }
// });

