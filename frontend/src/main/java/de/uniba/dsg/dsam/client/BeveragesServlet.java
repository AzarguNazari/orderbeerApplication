package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


public class BeveragesServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(BeveragesServlet.class.getName());

    @EJB
    IncentiveManagement incentiveManagement;

    @EJB
    BeverageManagement beverageManagement;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("incentiveList", incentiveManagement.getIncentives());
        req.getRequestDispatcher("/addBeverage.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String name = req.getParameter("productName");
            String manufacturer = req.getParameter("manufacturer");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double price = Double.parseDouble(req.getParameter("price"));
            Beverage beverage;
            if (req.getParameter("incentive").equals("")) {
                beverage = new Beverage(
                        name,
                        manufacturer,
                        quantity,
                        price,
                        null
                );

            } else {
                beverage = new Beverage(
                        name,
                        manufacturer,
                        quantity,
                        price,
                        incentiveManagement
                                .findById(Long.parseLong(req.getParameter("incentive")))
                );
            }

            beverageManagement.create(beverage);
            req.getRequestDispatcher("/main.jsp").forward(req, res);
        } catch (Exception ex) {
            logger.severe("Error saving beverage");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {

    }
}
