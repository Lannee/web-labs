function setInputLabelValue(value) {
    document.getElementById("input_label").innerHTML = value;
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawAllPoints();
}

function sendAsyncRequest(data, onSuccess) {
    $.ajax({
        type: "POST",
        url: new URL("./control", window.location.href),
        data: {"clear" : true},
        success: onSuccess
    });
}

function sendRequest(data) {
    const searchParams = new URLSearchParams(data);
    window.location = "control?" + searchParams.toString();
}

function validateAndSendRequest(x, y, r) {
    hideElement(tip);

    if(!validateX(x)) {setTip("Invalid value for X"); return}
    if(!validateY(y)) {setTip("Invalid value for Y"); return}
    if(!validateR(r)) {setTip("Invalid value for R"); return}

    sendRequest({"x" : x, "y" : y, "r" : r});
}

function clearSession() {
    sendAsyncRequest({"clear" : true});
    location.reload();
}

function validateAll(x, y, r) {
    return validateX(x) && validateY(y) && validateR(r);
}

function validateX(value) {
    return ["-3", "-2", "-1", "0", "1", "2", "3", "4", "5"].includes(value);
    // return isNumeric(value) && value <= 5 && value >= -3;
}

function validateY(value) {
    return isNumeric(value) && value <= 5 && value >=-3;
}

function validateR(value) {
    return ["1", "1.5", "2", "2.5", "3"].includes(value);
}

let form = document.getElementById('input_form');
let x_select = document.getElementById('x_coordinate');
let y_input = document.getElementById('y_coordinate');
let r_label = document.getElementById('input_label');
let tip = document.getElementById('tip');
let clearButton = document.getElementById('clear');

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function setTip(text) {
    showElement(tip);
    tip.innerText = text;
}

function showElement(element) {
    element.style.removeProperty('display');
}

function hideElement(element) {
    element.style.display = "none";
}

function getR() {
    return document.getElementById('input_label').innerText;
    // return r_label.innerText;
}

// const xInput = document.getElementById("input_form\:x");
const yInput = document.getElementById("input_form\:y");
// const rInput = document.getElementById(rInputID);


document.getElementById('input_form\:submit_button').addEventListener('click', (event) => {
    // event.preventDefault();
    console.log("click")

    hideElement(tip);
    console.log(yInput.toString() + yInput.value)

    // if(!validateX(parseFloat(x_select.value).toFixed(4))) {setTip("Invalid value for X"); return}
    if(!validateY(parseFloat(yInput.value).toFixed(4))) {setTip("Invalid value for Y"); return}
    // if(!validateR(parseFloat(r_label.innerText).toFixed(4))) {setTip("Invalid value for R"); return}
});