export function isXNotvalid(x) {
    return !isNumeric(x) || x < -5 || x > 3
}
export function isYNotvalid(y) {
    return !isNumeric(y) || y < -3 || y > 3
}

export function isRNotvalid(r) {
    return !isNumeric(r) && r != 1 && 
    r != 2 && r != 3 && 
    r != 4 && r != 5
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}