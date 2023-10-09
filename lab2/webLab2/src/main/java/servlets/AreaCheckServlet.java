package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import data.Point;
import data.Shot;
import data.UserData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.managers.PointManager;

public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        UserData data = (UserData)(req.getSession().getAttribute("data"));
        // (data.getShots().size() != null) ? data.getShots().get(data.getShots().size() - 1) : null;
        try(PrintWriter out = res.getWriter()) {
            Double xVal = PointManager.getRequestParam(req, "x");
            Double yVal = PointManager.getRequestParam(req, "y");
            Double rVal = PointManager.getRequestParam(req, "r");

            if(xVal == null || yVal == null || rVal == null) {
                res.sendError(400, "Invalid request params");
                return;
            }

            if(data == null) {
                data = new UserData();
                req.getSession().setAttribute("data", data);
            }
            
            Shot shot = PointManager.manage(new Point(xVal, yVal, rVal));

            if(shot != null) {
                data.getShots().add(shot);

                res.setContentType("text/html;charset=UTF-8");

                String color = String.format("%1$06X", shot.color());

                out.println("""
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="stylesheet" href="src/css/style.css">
                        <title>Result</title>
                        <link href="https://fonts.googleapis.com/css2?family=Amaranth:ital@0;1&family=Montserrat:wght@600&display=swap"
                            rel="stylesheet">
                    </head>
                    <body>
                        <table class="results convex" style="width: 45%;">
                            <thead>
                                <th class="resultTitle" style="width:33%">X Coordinate</th>
                                <th class="resultTitle" style="width:33%">Y Coordinate</th>
                                <th class="resultTitle" style="width:33%">Radius</th>
                            </thead>
                            <tbody id="results_body">
                                <tr>
                                    <td>
                                    """ + shot.xCoord() + """
                                    </td>
                                    <td>
                                    """ + shot.yCoord() + """
                                    </td>
                                    <td>
                                    """ + shot.radius() + """
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="results convex glowing" style="width: 20%; border: 1px solid #""" + color + """
                            ">
                            <tr>
                                <td>
                                    <div class="glowingBack"></div>
                                </td>
                            </tr>
                            <thead>
                                <th class="resultTitle" style="width:100%">Result</th>
                            </thead>
                            <tbody id="results_body">
                                <tr>
                                    <td>
                                        <div class="row centered">
                                            <div class="colorPoint" style="background-color: #""" + color + """
                                                ;"></div>
                                            <p>""" + (shot.hitFact() ? "Hit" : "Miss") + """
                                                </p>
                                            <div class="icon" style="background-image: url(
                                                """ + (shot.hitFact() ? "src/icons/done.png" : "src/icons/close.png") + """
                                                    );"></div>
                                        </div>
                                    </td>
                                    <style>
                                        @keyframes glow {
                                            0% {background-color: white;} 50% {background-color: #""" + color + """
                                                    ;} 100% {background-color: rgb(255, 255, 255);}
                                        }
                                    </style>
                                </tr>
                            </tbody>
                        </table>
                        <div class="row iconLabel" id="homeLinkContainer">
                            <div class="icon" style="background-image: url(src/icons/leftArrow.png);"></div>
                            <a href='""" + req.getContextPath() + """
                                ' id="homeLink">Here we go again</a>
                        </div>
                    </body>
                    </html>
                        """);
            } else {
                res.sendError(406, "Some value is out of bound");
                return;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doPost(req, res);
    }
}

