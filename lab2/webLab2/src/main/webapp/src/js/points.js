let POINTS;

const canvas = document.getElementById("areas_canvas");
const ctx = canvas.getContext("2d");

const POINT_RADIUS  = 4;

function drawPointXYCoords(xValue, yValue, color) {
    drawPointCornerCoords(canvas.width / 2 + canvas.width / 3 * (xValue / getR()),
    canvas.height / 2 - canvas.height / 2.95 * (yValue / getR()),
    color);
}

function drawPointCornerCoords(xValue, yValue, color) {
    ctx.save();
    ctx.beginPath();
    ctx.fillStyle = color;
    ctx.arc(xValue, yValue, POINT_RADIUS, 0, 2 * Math.PI);
    ctx.fill();
    ctx.restore();
}

function clearCanvas() {
    ctx.clearRect(0, 0, canvas.width, canvas.width)
}

function drawPoints() {
    getR();
}

function convertXCoordinate(xOnCorner, radius) {
    return (xOnCorner - canvas.width / 2) / (canvas.width / 3) * radius
}

function convertYCoordinate(yOnCorner, radius) {
    return -(yOnCorner - canvas.height / 2) / (canvas.height / 3) * radius
}



// canvas.addEventListener("mousemove", event => drawPoint(event));
canvas.addEventListener("mousedown", event => {
    const rect = canvas.getBoundingClientRect();
    const mouseX = event.clientX - rect.left;
    const mouseY = event.clientY - rect.top;

    const r = r_label.innerText;
    const x = parseFloat(convertXCoordinate(mouseX, r)).toFixed(4);
    const y = parseFloat(convertYCoordinate(mouseY, r)).toFixed(4);

    validateAndSendRequest(x, y, r);
});