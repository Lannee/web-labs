<?php
session_start();

if(isset($_SESSION['data'])) {
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
}

?>