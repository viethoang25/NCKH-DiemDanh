function clearCanvas(canvas) {
	var context = canvas.getContext('2d');
	context.clearRect(0, 0, canvas.width, canvas.height);
}


function getOffset( el ) {
    var _x = 0;
    var _y = 0;
    while( el && !isNaN( el.offsetLeft ) && !isNaN( el.offsetTop ) ) {
        _x += el.offsetLeft - el.scrollLeft;
        _y += el.offsetTop - el.scrollTop;
        el = el.offsetParent;
    }
    return { top: _y, left: _x };
}

function initDraw(canvas, ex1, ey1, ex2, ey2) {
    var mouse = {
        x: 0,
        y: 0,
        startX: 0,
        startY: 0
    };
    
    function setMousePosition(e) {
        var ev = e || window.event; //Moz || IE
        if (ev.pageX) { //Moz
            mouse.x = ev.pageX + window.pageXOffset;
            mouse.y = ev.pageY + window.pageYOffset;
        } else if (ev.clientX) { //IE
            mouse.x = ev.clientX + document.body.scrollLeft;
            mouse.y = ev.clientY + document.body.scrollTop;
        }
    };
	function drawRect(x1, y1, x2, y2){
		var context = canvas.getContext('2d');
		context.clearRect(0, 0, canvas.width, canvas.height);
        var left = getOffset(canvas).left;
        left = (left < 0) ? -left : left;
        var top =  getOffset(canvas).top;
        top = (top < 0) ? -top : top;
        console.log("canvas : " + left + ", " + top);
		context.beginPath();
		context.rect(mouse.startX -left, mouse.startY - top, mouse.x - mouse.startX, mouse.y - mouse.startY);
		context.lineWidth = 2;
		context.strokeStyle = 'black';
		context.stroke();
	}    

    var element = null;    
    canvas.onmousemove = function (e) {
        setMousePosition(e);
        if (element !== null) {
            element.style.width = Math.abs(mouse.x - mouse.startX) + 'px';
            element.style.height = Math.abs(mouse.y - mouse.startY) + 'px';
            element.style.left = (mouse.x - mouse.startX < 0) ? mouse.x + 'px' : mouse.startX + 'px';
            element.style.top = (mouse.y - mouse.startY < 0) ? mouse.y + 'px' : mouse.startY + 'px';
            drawRect(mouse.startX, mouse.startY, mouse.x, mouse.y);
        }
    }

    canvas.onclick = function (e) {
        var canvasOffset = getOffset(canvas);
        if (element !== null) {
            element = null;
            canvas.style.cursor = "default";
            ex2.value = e.clientX - canvasOffset.left;
            ey2.value = e.clientY - canvasOffset.top;
            console.log("finsihed." + mouse.x + ", " + mouse.y);
        } else {
            console.log("begun."+ mouse.x + ", " + mouse.y);
            mouse.startX = mouse.x;
            mouse.startY = mouse.y;
            console.log("CANVAS : " + canvasOffset.left + ", " + canvasOffset.top);
            console.log("CLIENT : " + e.clientX + ", " + e.clientY);
            ex1.value = e.clientX - canvasOffset.left;
            ey1.value = e.clientY - canvasOffset.top;
            element = document.createElement('div');
            element.className = 'rectangle'
            element.style.left = mouse.x + 'px';
            element.style.top = mouse.y + 'px';
            canvas.appendChild(element)
            canvas.style.cursor = "crosshair";
        }
    }
}