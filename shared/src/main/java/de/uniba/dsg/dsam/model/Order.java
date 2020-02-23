package de.uniba.dsg.dsam.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Order model consists of:
 * - private long id
 * - private Date issueDate
 */

public class Order implements Serializable {
    private long id;
    private Date issueDate;

    public Order() {
    }

    public Order(long id, Date issueDate) {
        this.id = id;
        this.issueDate = issueDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
