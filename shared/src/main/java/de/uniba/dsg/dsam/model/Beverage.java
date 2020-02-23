package de.uniba.dsg.dsam.model;

/**
 * This class defines the model for "Beverage" entity
 * properties of this entity include:
 * - long id;
 * - String name;
 * - String manufacturer;
 * - int quantity;
 * - double price;
 * - Incentive incentive;
 */

public class Beverage {
    private long id;
    private String name;
    private String manufacturer;
    private int quantity;
    private double price;
    private Incentive incentive;

    public Beverage() {
    }

    public Beverage(String name, String manufacturer, int quantity, double price, Incentive incentive) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
        this.incentive = incentive;
    }

    public Beverage(long id, String name, String manufacturer, int quantity, double price, Incentive incentive) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
        this.incentive = incentive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Incentive getIncentive() {
        return incentive;
    }

    public void setIncentive(Incentive incentive) {
        this.incentive = incentive;
    }
}
