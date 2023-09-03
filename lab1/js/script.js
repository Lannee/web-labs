function setInputLableValue(value) {
    document.getElementById("input_lable").innerHTML = value;
}


let form = document.getElementById('input_form');
let x_lable = document.getElementById('input_lable');
let y_input = document.getElementById('y_coordinate');
let r_select = document.getElementById('radius');
let tip = document.getElementById('tip');

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
    tip.style.removeProperty('display');
    tip.innerText = text;
}

function hideTip() {
    tip.style.display = "none";
}

form.addEventListener('submit', (event) => {
    event.preventDefault();

    hideTip();
    
    console.log("X: " + x_lable.innerHTML + validateX(x_lable.innerHTML));
    console.log("Y: " + y_input.value + validateY(y_input.value));
    console.log("R: " + r_select.value + validateR(r_select.value));

    if(!validateX(x_lable.innerHTML)) {setTip("Invalid value for X"); return}
    if(!validateY(y_input.value)) {setTip("Invalid value for Y"); return}
    if(!validateR(r_select.value)) {setTip("Invalid value for R"); return}

});