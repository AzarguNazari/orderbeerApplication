package de.uniba.dsg.dsam.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * OrderBeverageDTO is a serializable Data Transfer Object which consists of following properties:
 * - public Order order
 * - public ArrayList<SingleOrder> data
 */

public class OrderBeverageDTO implements Serializable {

    public Order order;
    public ArrayList<SingleOrder> data;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<SingleOrder> getData() {
        return data;
    }

    public void setData(ArrayList<SingleOrder> data) {
        this.data = data;
    }


    public class SingleOrder implements Serializable {
        public long id;
        public int quantity;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
