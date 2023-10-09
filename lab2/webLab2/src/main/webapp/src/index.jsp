<jsp:useBean id="data" class="data.UserData" scope="session"></jsp:useBean>
<%@ page import="java.util.LinkedList" %>
<%@ page import="data.Shot"%>
<%@ page import="java.util.concurrent.TimeUnit"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<%! DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm:ss"); %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab1 Bulko Egor</title>
    <link rel="stylesheet" href="src/css/style.css"> 

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Amaranth:ital@0;1&family=Montserrat:wght@600&display=swap" rel="stylesheet">
</head>
<body>
    <table class="header convex">
        <tr>
            <td class="header_name text_convex">Bulko Egor</td>
            <td class="header_group text_convex">P3206</td>
            <td class="header_variant text_convex">Variant: 1700</td>
        </tr>
    </table>

    <% Shot lastShot = (data.getShots().size() != 0) ? data.getShots().get(data.getShots().size() - 1) : null; %>
    <table class="content convex">
        <tr>
            <td class="areas">
                <canvas id="areas_canvas" height="300" width="300"></canvas>
            </td>
            <td class="inputs">
                <form id="input_form" method="post" action="./control">
                    <div class="input convex">
                        <div class="input_text" style="margin-bottom: 10px;">Select X:</div>
                        <select selected='<%= lastShot != null ? lastShot.xCoord().longValue() : 0%>' id="x_coordinate" name="x_coordinate" class="convex">
                            <% for(int i = -3; i <= 5; i++) {%>
                            <option value="<%= i%>" <%= (lastShot != null && lastShot.xCoord().longValue() == i) ? "selected" : ""%>><%= i%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="input convex">
                        <div class="input_text" style="margin-bottom: 10px;">Enter Y:</div>
                        <input id="y_coordinate" class="concave" name="y_coordinate" type="text" value="<%= lastShot != null ? lastShot.yCoord() : ""%>" placeholder="-3 ... 5">
                    </div>
                    <div class="input convex">
                        <div class="row" style="margin-bottom: 20px;">
                            <div class="input_text">Choose R:</div>
                            <label class="concave" style="margin-left: 10px;" id="input_lable"><%= (data.getShots().size() == 0) ? 1 : data.getShots().get(data.getShots().size() - 1).radius().longValue() %></label>
                        </div>
                        <div class=".buttons">
                            <button onclick="setInputLableValue(1)" class="input_button convex" type="button" name="x_coordinate">1</button>
                            <button onclick="setInputLableValue(2)" class="input_button convex" type="button" name="x_coordinate">2</button>
                            <button onclick="setInputLableValue(3)" class="input_button convex" type="button" name="x_coordinate">3</button>
                            <button onclick="setInputLableValue(4)" class="input_button convex" type="button" name="x_coordinate">4</button>
                            <button onclick="setInputLableValue(5)" class="input_button convex" type="button" name="x_coordinate">5</button>
                        </div>
                    </div>
                    <div class="row">
                        <input id="submit_button" class="convex" type="submit" placeholder="Submit">
                        <div style="display: none;" id="tip"></div>
                    </div>
                </form>
            </td>
        </tr>
    </table>
    <table class="convex">
        <tr><td>
            <table class="results">
                <thead>
                    <th class="resultTitle" style="width:10%">X</th>
                    <th class="resultTitle" style="width:10%">Y</th>
                    <th class="resultTitle" style="width:10%">R</th>
                    <th class="resultTitle" style="width:10%">Hit fact</th>
                    <th class="resultTitle" style="width:25%">Current time</th>
                    <th class="resultTitle" style="width:30%">Script running time, mks</th>
                    <th class="resultTitle" style="width:5%">Color</th>
                </thead>
                <tbody id="results_body">
                    <% if(data.getShots() != null) {
                        for(Shot shot : data.getShots()) { %>
                            <tr>
                                <td><%= shot.xCoord()%></td>
                                <td><%= shot.yCoord()%></td>
                                <td><%= shot.radius()%></td>
                                <td><%= shot.hitFact() ? "Hit" : "Miss"%></td>
                                <td><%= shot.currTime().format(formatter) %></td>
                                <td><%= shot.execTime().toNanos() / 1000 %></td>
                                <td><div class="colorPoint" style="margin: auto; background-color: #<%= String.format("%1$06X", shot.color()) %>;"></div></td>
                            </tr>
                        <% }
                    } %>
                </tbody>
            </table>
        </tr></td>
        <tr><td>
            <button <%= (data.getShots() == null || data.getShots().size() == 0) ? "style='display:none;'" : "" %> onclick="clearSession();" id="clear" class="convex">Clear</button>
        </tr></td>
    </table>
</body>
<script src="src/js/jquery.js"></script>
<script src="src/js/script.js"></script>
<script src="src/js/points.js"></script>
<script>
function drawAllPoints() {
    console.log("points");
<% if (data.getShots() != null) {
    for(Shot shot : data.getShots()) { %>    drawPointXYCoords(<%= shot.xCoord()%>, <%= shot.yCoord()%>, '#<%= String.format("%1$06X", shot.color()) %>'); <% }
} %>
}
drawAllPoints();
</script>
</html>