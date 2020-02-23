package de.uniba.dsg.dsam.persistence;

import java.util.Map;

public interface OrderManagement {

    public double revenue();

    public Map<String, Double> revenueByProduct();

    public Map<String, Double> revenueByIncentive();
}
