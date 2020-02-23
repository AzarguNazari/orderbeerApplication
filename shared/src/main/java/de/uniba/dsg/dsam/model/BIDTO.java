package de.uniba.dsg.dsam.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the Business Intelligence Data Transfer Object
 * it consistes of:
 * - Map<String, Double> revenueByProduct
 * - Map<String, Double> revenueByIncentiveType
 */

public class BIDTO implements Serializable {
    //private List<Double> revenueByIncentiveType;

    private Map<String, Double> revenueByProduct;
    private Map<String, Double> revenueByIncentiveType;

    public BIDTO() {

        this.revenueByProduct = new HashMap<>();
        this.revenueByIncentiveType = new HashMap<>();
    }

    public Map<String, Double> getRevenueByIncentiveType() {
        return revenueByIncentiveType;
    }

    public void setRevenueByIncentiveType(Map<String, Double> revenueByIncentiveType) {
        this.revenueByIncentiveType = revenueByIncentiveType;
    }

    public Map<String, Double> getRevenueByProduct() {
        return revenueByProduct;
    }

    public void setRevenueByProduct(Map<String, Double> revenueByProduct) {
        this.revenueByProduct = revenueByProduct;
    }
}
