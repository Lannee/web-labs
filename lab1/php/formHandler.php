<?php

function validateX($value) {
    return isset($value) && is_numeric($value) && in_array($value, ["-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3"]);
}

function validateY($value) {
    return isset($value) && is_numeric($value) && $value >= -5 && $value <= 5;
}

function validateR($value) {
    return isset($value) && is_numeric($value) && in_array($value, ["1", "1.5", "2", "2.5", "3"]);
}

function checkFirstArea($x, $y, $r){
	return $y >= 0 && $x <= 0 && $y - $x <= $r/2; // triangle
}

function checkSecondArea($x, $y, $r){
	return $y >= 0 && $x >= 0 && $y <= $r/2 && $x <= $r;
}

function checkThirdArea($x, $y, $r){
	return $y <= 0 && $x >= 0 && $x**2 + $y**2 <= ($r**2)/2;
}

session_start();
if(!isset($_SESSION['data'])) $_SESSION['data'] = [];

$x = $_GET['x'];
$y = $_GET['y'];
$r = $_GET['r'];
$timezone = $_GET['timezone'];

if(validateX($x) && validateY($y) && validateR($r) && isset($timezone)) {
    $hitFact = checkFirstArea($x, $y, $r) || 
               checkSecondArea($x, $y, $r) || 
               checkThirdArea($x, $y, $r) ? "Hit" : "Miss";
    $currTime = date('j M o : G i s', time() - $timezone * 60);
    $executionTime = round(microtime(true) - $_SERVER['REQUEST_TIME_FLOAT'], 8);

    array_push(
        $_SESSION['data'], 
        array(
            'x'=>floatval($x), 
            'y'=>floatval($y), 
            'r'=>floatval($r), 
            'hitFact'=>$hitFact, 
            'currTime'=>$currTime, 
            'executionTime'=>$executionTime
        ));
}

foreach($_SESSION['data'] as $line) {
    echo "<tr>
        <td>" . $line['x'] . "</td>
        <td>" . $line['y'] . "</td>
        <td>" . $line['r'] . "</td>
        <td>" . $line['hitFact'] . "</td>
        <td>" . $line['currTime'] . "</td>
        <td>" . $line['executionTime'] . "</td>
    </tr>";
}

?>