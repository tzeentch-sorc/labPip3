const step = 100;
const width = 500;
function draw(symbol) {
    let ctx = document.getElementById('canvas').getContext("2d");

    drawBG(ctx, symbol);
    resolvePoints(symbol, ctx);
}

function resolvePoints(radius, ctx) {
    let all = true;
    let values = $("#pointTable td").toArray();
    if(values.length > 3)
        for(let i = 0; i < values.length/4; ++i){
            all &= drawPoint(ctx,
                values[i*4].innerText,
                values[i*4 + 1].innerText,
                values[i*4 + 2].innerText,
                values[i*4 + 3].innerText
                );
        }

    if(!all) {
        ctx.fillStyle="black";
        ctx.font = "14px Times New Roman";
        ctx.fillText('Некоторые точки за пределами графика', 60, 480);
    }
}

function check(x, y, r) {
    if (    ( x >= 0 && x <= (r/2)) && (y >= 0 && y <= r) //прямоугольник
        || (x >= 0 && y < 0) && (y >= x - r/2) //треугольник
        || (x <= 0 && y >= 0) && (x * x + y * y <= r * r))//окружность
        return true;
    else return false;
}

function drawPoint(ctx, x, y, r, res) {
    let radius = parseFloat(document.getElementById('pointForm:r').innerHTML);

        if(check(x, y, radius)) ctx.fillStyle = "#00FF00";
        else ctx.fillStyle = "#FF5000";
        if(r/radius > 1.25) return false;
        ctx.beginPath();
        ctx.arc(250 + ((x / radius) * 200), 250 - ((y / radius) * 200), 3, 0, Math.PI * 2);
        ctx.stroke();
        ctx.fill();
        return true;
}

function interactiveCanvas(event){
    document.getElementById('pointForm:xMsg').innerHTML = "";
    let canvas = document.getElementById('canvas');
    let mouseCoord = getMouseCoord(canvas, event);
    let radiusVal = parseFloat(document.getElementById('pointForm:r').innerHTML);
        let x = (((mouseCoord.x - 250) * radiusVal) / 200);
        let y = (((-mouseCoord.y + 250) * radiusVal) / 200 );
        if(Math.abs(Math.round(x) - x) > 0) {
            document.getElementById('pointForm:xMsg').innerHTML = "X is {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5}";
            return false;
        }
        if (x < -6)
            x = -6; //magic value for validation failed
        if(x > 5)
            x = 6; //magic value for validation failed
        x = (Math.round(x*2)*1.)/2;
        $("input[name*='pointForm:x_hidden']").val(x);
        $("input[name$='y']").val(y);
        document.getElementById('pointForm:sb_button').click();
        return true;
}

function getMouseCoord(canvas, event) {
    let rect = canvas.getBoundingClientRect();
    return {
        x: event.clientX - rect.left,
        y: event.clientY - rect.top
    }
}

function drawBG(ctx, symbol) {
    ctx.clearRect(0,0, 500, 500);
    ctx.fillStyle = "rgba(0, 0, 42, 0.5)";
    ctx.fillRect(0, 0 , 500, 500);

    //triangle
    ctx.beginPath();
    ctx.strokeStyle = "#9a0000";
    ctx.fillStyle = "rgba(154, 0, 0, 0.7)";
    ctx.moveTo(350, 250);
    ctx.lineTo(250, 350);
    ctx.lineTo(250, 250);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

    //прямоугольник
    ctx.beginPath();
    ctx.moveTo(350, 250);
    ctx.lineTo(350, 50);
    ctx.lineTo(250, 50);
    ctx.lineTo(250, 250);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

    //сектор
    ctx.beginPath();
    ctx.arc(250, 250, 200, Math.PI, 3*Math.PI/2,false);
    ctx.lineTo(250, 250);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

    //оси
    ctx.beginPath();
    ctx.strokeStyle = "black";
    ctx.lineWidth = "2";
    ctx.moveTo(0, 250);
    ctx.lineTo(500, 250);
    ctx.moveTo(250, 0);
    ctx.lineTo(250, 500);
    ctx.stroke();

    //отметки
    ctx.beginPath();
    ctx.moveTo(245, 50);
    ctx.lineTo(255, 50);
    ctx.moveTo(245, 150);
    ctx.lineTo(255, 150);
    ctx.moveTo(245, 350);
    ctx.lineTo(255, 350);
    ctx.moveTo(245, 450);
    ctx.lineTo(255, 450);

    ctx.moveTo(50, 245);
    ctx.lineTo(50, 255);
    ctx.moveTo(150, 245);
    ctx.lineTo(150, 255);
    ctx.moveTo(350, 245);
    ctx.lineTo(350, 255);
    ctx.moveTo(450, 245);
    ctx.lineTo(450, 255);
    ctx.closePath();
    ctx.stroke();

    //подписи
    ctx.fillStyle="black";
    ctx.font = "20px Times New Roman bold";
    if((!isNaN(parseFloat(symbol)))&&(parseFloat(symbol)!=0)){
        ctx.fillText("-"+symbol, 40, 275);
        ctx.fillText("-"+ (symbol/2), 130, 275);
        ctx.fillText(symbol/2, 340, 275);
        ctx.fillText(symbol, 445, 275);

        ctx.fillText(symbol, 265, 53);
        ctx.fillText(symbol/2, 265, 153);
        ctx.fillText("-"+(symbol/2), 265, 353);
        ctx.fillText("-"+symbol, 265, 453);
        ctx.stroke();
    }
    else{
        ctx.fillText("-R", 40, 275);
        ctx.fillText("-R/2", 130, 275);
        ctx.fillText("R/2", 340, 275);
        ctx.fillText("R", 445, 275);

        ctx.fillText("R", 265, 53);
        ctx.fillText("R/2", 265, 153);
        ctx.fillText("-R/2", 265, 353);
        ctx.fillText("-R", 265, 453);
        ctx.stroke();
    }

}