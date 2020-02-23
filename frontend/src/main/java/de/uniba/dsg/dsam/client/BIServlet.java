package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.IncentiveType;
import de.uniba.dsg.dsam.persistence.OrderManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/*
                    EJB  (Enterprise JavaBeans)

                    Functionality
                        - SessionBean
                        - Persistence Mapping
                        - MessageDrivenBeans
                        - Entity Beans

                |--------------                                                                                 |--------------
                |             |       (Remote, Local, No-Interface, WebService)                                 |             |
                |   Component | ------------------------------------------------------------------------------->|   Component |
                |      A      |                                                                                 |      B      |
                |-------------|                                                                                 |-------------|



                |--------------                                                                                 |--------------
                |             |              (Stateless, Stateful, Singleton)                                   |             |
                |   Component | ------------------------------------------------------------------------------->|   Component |
                |      A      |                                                                                 |      B      |
                |-------------|                                                                                 |-------------|



                |--------------                                                                                 |--------------
                |             |                                                                                 |             |
                |   Component | ------------------------------------------------------------------------------->|   Component |
                |      A      |                                                                                 |      B      |
                |-------------|                                                                                 |-------------|




                |--------------                                                                                 |--------------
                |             |                                                                                 |             |
                |   Component | ------------------------------------------------------------------------------->|   Component |
                |      A      |                                                                                 |      B      |
                |-------------|                                                                                 |-------------|




                |--------------                                                                                 |--------------
                |             |                                                                                 |             |
                |   Component | ------------------------------------------------------------------------------->|   Component |
                |      A      |                                                                                 |      B      |
                |-------------|                                                                                 |-------------|




                |--------------                                                                                 |--------------
                |             |                                                                                 |             |
                |   Component | ------------------------------------------------------------------------------->|   Component |
                |      A      |                                                                                 |      B      |
                |-------------|                                                                                 |-------------|


 */

public class BIServlet extends HttpServlet {
    @EJB
    OrderManagement orderManagement;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        Map<String, Double> revenueBreakdown = orderManagement.revenueByIncentive();
        req.setAttribute("revenue", orderManagement.revenue());
        req.setAttribute("revenueWithOutIncentive", revenueBreakdown.get(IncentiveType.NO_INCENTIVE));
        req.setAttribute("revenueWithTrial", revenueBreakdown.get(IncentiveType.TRIAL));
        req.setAttribute("revenueWithPromotion", revenueBreakdown.get(IncentiveType.PROMO));
        req.getRequestDispatcher("/bi.jsp").forward(req, res);
    }
}
