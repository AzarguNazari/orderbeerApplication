package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.OrderBeverageDTO;

public interface OrderCreateManagement {
    public long saveOrder(OrderBeverageDTO orderBeverageDTO);
}
