package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.model.OrderBeverageDTO;
import de.uniba.dsg.dsam.persistence.OrderCreateManagement;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
        },
        mappedName = "BeverageStoreQueue")
public class OrderMessageDrivenBean implements MessageListener {

    private final static Logger logger = Logger.getLogger(OrderMessageDrivenBean.class.getName());

    @EJB
    OrderCreateManagement orderCreateManagement;

    public OrderMessageDrivenBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {

                Object receivedObject = ((ObjectMessage) message).getObject();
                OrderBeverageDTO beverage = (OrderBeverageDTO) receivedObject;
                logger.log(Level.SEVERE,
                        "MESSAGE BEAN: Message received: {0}",
                        beverage.getData().get(0).quantity);
                orderCreateManagement.saveOrder(beverage);
            } else {
                logger.severe("Message of wrong type: " + message.getClass().getName());

            }
        } catch (JMSException e) {
            logger.severe("JMSException: " + e.toString());
        }
    }

}