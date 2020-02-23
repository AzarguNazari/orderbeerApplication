package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.persistence.BeverageManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BeverageListServlet extends HttpServlet {
    @EJB
    BeverageManagement beverageManagement;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        req.setAttribute("beverageList", beverageManagement.getBeverages());
        req.getRequestDispatcher("/allBeverages.jsp").forward(req, res);
    }
}
