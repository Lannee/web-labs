const canvas = document.getElementById("areas_canvas");
const ctx = canvas.getContext("2d");

const HIT_COLOR = "#22bb33"
const MISS_COLOR = "#d7191c"
const INVALID_RADIUS_COLOR = "#000000"

const POINT_RADIUS  = 4;

function drawPointXYCoords(xValue, yValue, r, hit) {
    drawPointCornerCoords(canvas.width / 2 + canvas.width / 3 * (xValue / getR()),
    canvas.height / 2 - canvas.height / 2.95 * (yValue / getR()), r,
    hit);
}

function drawPointCornerCoords(xValue, yValue, r, hit) {
    ctx.save();
    ctx.beginPath();
    ctx.fillStyle = r == getR() ? (hit ? HIT_COLOR : MISS_COLOR) : INVALID_RADIUS_COLOR;
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

    const r = getR();
    const x = parseFloat(convertXCoordinate(mouseX, r)).toFixed(4);
    const y = parseFloat(convertYCoordinate(mouseY, r)).toFixed(4);

    document.getElementById("canvas_form:canvas_x").value = x;
    document.getElementById("canvas_form:canvas_y").value = y;

    canvas_shot();
});