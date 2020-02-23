package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value="PROMOTION")
public class PromotionalGiftEntity extends IncentiveEntity {
    public PromotionalGiftEntity() {
    }

    public PromotionalGiftEntity(String name) {
        this.setName(name);
    }
    public PromotionalGiftEntity(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

}
