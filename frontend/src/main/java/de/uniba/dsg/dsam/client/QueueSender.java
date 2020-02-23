package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.OrderBeverageDTO;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.logging.Logger;


@Stateless
public class QueueSender {
    private static final Logger logger = Logger.getLogger(QueueSender.class.getName());

    @Resource(mappedName = "BeverageStoreCF")
    private ConnectionFactory factory;

    @Resource(mappedName = "BeverageStoreQueue")
    private Queue target;

    public void sendMessage(OrderBeverageDTO order) {
        logger.severe("sendMessage");
        try (Connection connection = factory.createConnection()) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(target);
            ObjectMessage message = session.createObjectMessage(order);
            producer.send(message);
            logger.info("Sent order to JMS queue");
        } catch (JMSException ex) {
            logger.severe("Could not send message to Queue" + ex);
        }

    }
}
