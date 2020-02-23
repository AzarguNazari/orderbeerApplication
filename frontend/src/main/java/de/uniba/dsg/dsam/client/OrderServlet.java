package de.uniba.dsg.dsam.client;

import com.google.gson.Gson;
import de.uniba.dsg.dsam.model.Order;
import de.uniba.dsg.dsam.model.OrderBeverageDTO;
import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.persistence.OrderManagement;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

public class OrderServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(OrderServlet.class.getName());

    @EJB
    BeverageManagement beverageManagement;

    @EJB
    QueueSender sender;

    @EJB
    OrderManagement orderManagement;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("beverageList", beverageManagement.getBeverages());
        req.getRequestDispatcher("/addOrder.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            logger.severe("Invalid JSON Object");
        }

        try {
            JSONObject jsonObject = HTTP.toJSONObject(jb.toString());
            logger.severe(jsonObject.getString("Method").toString());
            OrderBeverageDTO dto = new Gson().fromJson(jsonObject.getString("Method").toString(), OrderBeverageDTO.class);
            dto.setOrder(new Order());

            logger.severe(String.valueOf(dto.data.get(0).quantity));
            //orderManagement.saveOrder(dto);
            req.getRequestDispatcher("/main.jsp").forward(req, res);
            sender.sendMessage(dto);
        } catch (JSONException e) {
            throw new IOException("Error parsing JSON request string");
        }
    }
}
