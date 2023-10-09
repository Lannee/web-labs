package servlets;

import java.io.IOException;

import data.UserData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.managers.PointManager;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        UserData data = (UserData)(req.getSession().getAttribute("data"));

        if(PointManager.getRequestParam(req, "x") != null && 
           PointManager.getRequestParam(req, "y") != null && 
           PointManager.getRequestParam(req, "r") != null) {
                getServletContext().getRequestDispatcher("/areaCheck").forward(req, res);
                return;
        } else  {
            if(Boolean.parseBoolean(req.getParameter("clear"))) {
                if(data == null) {
                    data = new UserData();
                    req.getSession().setAttribute("data", data);
                } else {
                    data.getShots().clear();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doPost(req, res);
    }
}
