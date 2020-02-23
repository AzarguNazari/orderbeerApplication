package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.OrderEntity;
import de.uniba.dsg.dsam.backend.entities.OrderQuantity;
import de.uniba.dsg.dsam.model.OrderBeverageDTO;
import de.uniba.dsg.dsam.persistence.OrderCreateManagement;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.logging.Logger;

@Stateful
@Local(OrderCreateManagement.class)
public class OrderCreateBean implements OrderCreateManagement {

    private static final Logger logger = Logger.getLogger(OrderManagementBean.class.getName());

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @Override
    public long saveOrder(OrderBeverageDTO orderBeverageDTO) {
        OrderEntity order = new OrderEntity();
        em.persist(order);
        em.flush();
        orderBeverageDTO
                .data
                .forEach(
                        (OrderBeverageDTO.SingleOrder s)
                                -> em.persist(
                                new OrderQuantity(
                                        order,
                                        em.find(BeverageEntity.class, s.id),
                                        s.quantity
                                )
                        )
                );
        updateBeverageQuantity(orderBeverageDTO);
        return order.getId();
    }


    public void updateBeverageQuantity(OrderBeverageDTO orderBeverageDTO) {
        orderBeverageDTO.data.forEach(
                (OrderBeverageDTO.SingleOrder s)
                        -> {
                    BeverageEntity beverageEntity = em.find(BeverageEntity.class, s.id);
                    int updatedQuantity = beverageEntity.getQuantity() - s.quantity;
                    beverageEntity.setQuantity(updatedQuantity);
                    em.flush();
                }
        );
    }
}
