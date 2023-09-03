function setInputLableValue(value) {
    document.getElementById("input_lable").innerHTML = value;
}

function sendRequestTableData(method, url, data) {
    $.ajax({
        type: method,
        url: url,
        data: data,
        dataType: "html",
        success: (response) => {
            console.log(response);
            document.getElementById('results_body').innerHTML = response;
        },
        error: (error) => {
            console.log(error);
            setTip("Server response error");
        }
    });
}

function clearSession() {
    sendRequestTableData("GET", "php/clearSession.php", "");
    hideElement(clearButton);
}


let form = document.getElementById('input_form');
let x_lable = document.getElementById('input_lable');
let y_input = document.getElementById('y_coordinate');
let r_select = document.getElementById('radius');
let tip = document.getElementById('tip');
let clearButton = document.getElementById('clear');

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function validateX(value) {
    return ["-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3"].includes(value);
}

function validateY(value) {
    return isNumeric(value) && value <= 5 && value >=-5;
}

function validateR(value) {
    return ["1", "1.5", "2", "2.5", "3"].includes(value);
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

document.addEventListener("DOMContentLoaded", (event) => {
    sendRequestTableData("GET", "php/loadSession.php", "");
});


form.addEventListener('submit', (event) => {
    event.preventDefault();
    hideElement(tip);
    
    console.log("X: " + x_lable.innerHTML + validateX(x_lable.innerHTML));
    console.log("Y: " + y_input.value + validateY(y_input.value));
    console.log("R: " + r_select.value + validateR(r_select.value));

    if(!validateX(x_lable.innerHTML)) {setTip("Invalid value for X"); return}
    if(!validateY(y_input.value)) {setTip("Invalid value for Y"); return}
    if(!validateR(r_select.value)) {setTip("Invalid value for R"); return}

    sendRequestTableData("GET", "php/formHandler.php", {
        x: x_lable.innerHTML,
        y: y_input.value,
        r: r_select.value,
        timezone: new Date().getTimezoneOffset()
    });
    showElement(clearButton);
});