package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderBeverageKey implements Serializable {
    @Column(name = "order_id")
    long orderId;
    @Column(name = "beverage_id")
    long beverageId;

    public OrderBeverageKey() {
    }

    public OrderBeverageKey(long orderId, long beverageId) {
        this.orderId = orderId;
        this.beverageId = beverageId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(long beverageId) {
        this.beverageId = beverageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBeverageKey that = (OrderBeverageKey) o;
        return orderId == that.orderId &&
                beverageId == that.beverageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, beverageId);
    }
}
