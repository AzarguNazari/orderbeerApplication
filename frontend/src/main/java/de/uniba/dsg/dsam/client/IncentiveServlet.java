package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class IncentiveServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(IncentiveServlet.class.getName());
    @EJB
    IncentiveManagement incentiveManagement;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("incentiveList", incentiveManagement.getIncentives());
        req.getRequestDispatcher("/incentivesList.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            long id = Long.valueOf(req.getParameter("incentiveId"));
            String name = req.getParameter("incentiveName");
            Long version = Long.valueOf(req.getParameter("version"));
            incentiveManagement.edit(id, name, version);
        } catch (NumberFormatException e) {
            logger.severe("Invalid data for incentive id: Must be long : " + e);
        } catch (IllegalArgumentException e) {
            logger.severe("Invalid Id: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
        } finally {
            res.sendRedirect("/frontend/incentives");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            long id = Long.valueOf(req.getParameter("id"));
            incentiveManagement.removeIncentive(id);
        } catch (NumberFormatException e) {
            logger.severe("Invalid data for incentive id: Must be long" + e);
        } catch (Exception e) {
            logger.severe("Error " + e.getMessage());
        }
    }

}
