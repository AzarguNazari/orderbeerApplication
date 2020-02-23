package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beverages")
@NamedQueries({
        @NamedQuery(
                name = "Beverage.findAll",
                query = "SELECT b FROM BeverageEntity b"
        ),
        @NamedQuery(
                name = "Beverage.isThisIncentiveAssigned",
                query = "SELECT COUNT (b) FROM BeverageEntity b WHERE b.incentive.id= :id"
        )
})
public class BeverageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String manufacturer;
    private int quantity;
    private double price;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "incentive_id", nullable = true)
    private IncentiveEntity incentive;

    /*

        {
            "id" : 12213,
            "name": "some name",
            "manufacturer": "some name",
            "quantity": 12,
            "price": 124123
        }

        http://www.cheat-sheets.org/saved-copy/javaEE6ReferenceSheet.pdf


     */

    @OneToMany(mappedBy = "beverageEntity")
    private List<OrderQuantity> quantities;

    public BeverageEntity() {
    }

    public BeverageEntity(String name, String manufacturer, int quantity, double price, IncentiveEntity incentive) {
        this();
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
        this.incentive = incentive;
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public IncentiveEntity getIncentive() {
        return incentive;
    }

    public void setIncentive(IncentiveEntity incentive) {
        this.incentive = incentive;
    }

    public List<OrderQuantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<OrderQuantity> quantities) {
        this.quantities = quantities;
    }
}
