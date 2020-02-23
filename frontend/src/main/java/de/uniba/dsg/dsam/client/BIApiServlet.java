package de.uniba.dsg.dsam.client;

import com.google.gson.Gson;
import de.uniba.dsg.dsam.model.BIDTO;
import de.uniba.dsg.dsam.persistence.OrderManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/bi-api")
public class BIApiServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(BIApiServlet.class.getName());

    @EJB
    OrderManagement orderManagement;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        logger.severe("WORKING....");
        BIDTO bidto = new BIDTO();
        bidto.setRevenueByIncentiveType(orderManagement.revenueByIncentive());

        bidto.setRevenueByProduct(orderManagement.revenueByProduct());

        Gson gson = new Gson();
        String jsonString = gson.toJson(bidto);

        res.setContentType("application/json");

        res.getWriter().write(jsonString);
    }
}
