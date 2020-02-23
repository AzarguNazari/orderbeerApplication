package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/incentives/edit")
public class IncentiveEditServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(IncentiveEditServlet.class.getName());
    @EJB
    IncentiveManagement incentiveManagement;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Incentive incentive = incentiveManagement.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("incentive", incentive);
        req.getRequestDispatcher("/incentiveEdit.jsp").forward(req, resp);
    }
}
