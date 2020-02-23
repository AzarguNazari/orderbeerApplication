package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderQuantity> quantities;

    @Column(name = "issue_date")
    private Date issueDate = new Date();

    public OrderEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderEntity(List<OrderQuantity> quantities) {
        this.quantities = quantities;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public List<OrderQuantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<OrderQuantity> quantities) {
        this.quantities = quantities;
    }
}
