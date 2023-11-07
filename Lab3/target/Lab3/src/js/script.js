function setInputLabelValue(value) {
    document.getElementById("input_label").innerHTML = value;
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawAllPoints();
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
}

// const xInput = document.getElementById("input_form\:x");
// const yInput = document.getElementById("input_form\:y");
// const rInput = document.getElementById(rInputID);
// let count = 0

// document.getElementById('input_form\:submit_button').addEventListener('mousedown', (event) => {
//     count++;
//     console.log(count);
//
//     console.log("fuck you");
//
//     hideElement(tip);
//
//     // if(!validateX(parseFloat(x_select.value).toFixed(4))) {setTip("Invalid value for X"); return}
//     if(!validateY(document.getElementById("input_form\:y").value)) {setTip("Invalid value for Y");return}
//     // if(!validateR(parseFloat(r_label.innerText).toFixed(4))) {setTip("Invalid value for R"); return}
// });
//
// document.getElementById('input_form\:submit_button').addEventListener('mousedown', (event) => {
//     location.reload();
// });


// <p:inputText id="y" className="concave" required="true" value="${yBean.y}" validator="${yBean.validateY}" size="5"
//              a:placeholder="-3 ... 5">
//     <f:validateLongRange minimum="-3" maximum="5"/>
//     <f:ajax execute="y-select" render="y"/>
// </p:inputText>

// setTimeout(() => location.reload(), 200)

// <h:selectBooleanCheckbox id="r_b1" value="${rBean.b1}" validator="${rBean.validateR}" onclick="setInputLabelValue(1)">
//     <f:ajax render="r_b1 r_b15 r_b2 r_b25 r_b3 draw_script" />
// </h:selectBooleanCheckbox> 1
// <h:selectBooleanCheckbox id="r_b15" value="${rBean.b15}" validator="${rBean.validateR}" onclick="setInputLabelValue(1.5)">
//     <f:ajax render="r_b1 r_b15 r_b2 r_b25 r_b3 draw_script" />
//     </h:selectBooleanCheckbox> 1.5
// <h:selectBooleanCheckbox id="r_b2" value="${rBean.b2}" validator="${rBean.validateR}" onclick="setInputLabelValue(2)">
//     <f:ajax render="r_b1 r_b15 r_b2 r_b25 r_b3 draw_script" />
// </h:selectBooleanCheckbox> 2
// <h:selectBooleanCheckbox id="r_b25" value="${rBean.b25}" validator="${rBean.validateR}" onclick="setInputLabelValue(2.5)">
//     <f:ajax render="r_b1 r_b15 r_b2 r_b25 r_b3 draw_script" />
//     </h:selectBooleanCheckbox> 2.5
// <h:selectBooleanCheckbox id="r_b3" value="${rBean.b3}" validator="${rBean.validateR}" onclick="setInputLabelValue(3)">
//     <f:ajax render="r_b1 r_b15 r_b2 r_b25 r_b3 draw_script" />
// </h:selectBooleanCheckbox> 3