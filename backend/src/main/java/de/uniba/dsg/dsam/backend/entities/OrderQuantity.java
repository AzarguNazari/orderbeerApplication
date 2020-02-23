package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.*;

@Entity(name = "OrderQuantity")
@Table(name = "order_beverage")
@NamedQuery(
        name = "OrderQuantity.allOrder",
        query = "SELECT oq FROM OrderQuantity oq"
)
public class OrderQuantity {
    @EmbeddedId
    private OrderBeverageKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    OrderEntity orderEntity;


    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("beverageId")
    @JoinColumn(name = "beverage_id")
    BeverageEntity beverageEntity;

    int quantity;

    public OrderQuantity() {
    }

    public OrderQuantity(OrderEntity orderEntity, BeverageEntity beverageEntity, int quantity) {
        this.orderEntity = orderEntity;
        this.beverageEntity = beverageEntity;
        this.quantity = quantity;
    }

    public OrderBeverageKey getId() {
        return id;
    }

    public void setId(OrderBeverageKey id) {
        this.id = id;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public BeverageEntity getBeverageEntity() {
        return beverageEntity;
    }

    public void setBeverageEntity(BeverageEntity beverageEntity) {
        this.beverageEntity = beverageEntity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
