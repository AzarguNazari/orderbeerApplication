package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.backend.entities.OrderQuantity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;
import de.uniba.dsg.dsam.model.IncentiveType;
import de.uniba.dsg.dsam.persistence.OrderManagement;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Stateless
@Local(OrderManagement.class)
public class OrderManagementBean implements OrderManagement {
    private static final Logger logger = Logger.getLogger(OrderManagementBean.class.getName());

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @Override
    public double revenue() {
        List<OrderQuantity> list = em.createNamedQuery("OrderQuantity.allOrder", OrderQuantity.class)
                .getResultList();
        double revenue = 0;
        for (OrderQuantity q : list) {
            revenue += q.getBeverageEntity().getPrice() * q.getQuantity();
        }
        return revenue;
    }

    @Override
    public Map<String, Double> revenueByProduct() {
        Map<String, Double> revenueByProduct = new HashMap<>();
        List<OrderQuantity> list = em.createNamedQuery("OrderQuantity.allOrder", OrderQuantity.class)
                .getResultList();
        list.forEach(o -> {

            if (revenueByProduct.containsKey(o.getBeverageEntity().getId())) {
                double revenue = revenueByProduct.get(o.getBeverageEntity().getName());
                revenue += o.getBeverageEntity().getPrice() * o.getQuantity();
                revenueByProduct.put(o.getBeverageEntity().getName(), revenue);

            } else {
                revenueByProduct.put(o.getBeverageEntity().getName(), o.getBeverageEntity().getPrice() * o.getQuantity());
            }
        });
        return revenueByProduct;
    }

    @Override
    public Map<String, Double> revenueByIncentive() {
        List<OrderQuantity> list = em.createNamedQuery("OrderQuantity.allOrder", OrderQuantity.class)
                .getResultList();
        Map<String, Double> revenueByIncentive = new HashMap<>();
        revenueByIncentive.put(IncentiveType.NO_INCENTIVE, 0.0);
        revenueByIncentive.put(IncentiveType.TRIAL, 0.0);
        revenueByIncentive.put(IncentiveType.PROMO, 0.0);
        list.forEach(o -> {
            if (o.getBeverageEntity().getIncentive() == null) {
                double sum = revenueByIncentive.get(IncentiveType.NO_INCENTIVE);
                sum += o.getBeverageEntity().getPrice() * o.getQuantity();
                revenueByIncentive.put(IncentiveType.NO_INCENTIVE, sum);
            } else if (o.getBeverageEntity().getIncentive() instanceof TrialPackageEntity) {
                double sum = revenueByIncentive.get(IncentiveType.TRIAL);
                sum += o.getBeverageEntity().getPrice() * o.getQuantity();
                revenueByIncentive.put(IncentiveType.TRIAL, sum);
            } else {
                double sum = revenueByIncentive.get(IncentiveType.PROMO);
                sum += o.getBeverageEntity().getPrice() * o.getQuantity();
                revenueByIncentive.put(IncentiveType.PROMO, sum);
            }
        });
        return revenueByIncentive;
    }
}
