package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="TRIAL")
public class TrialPackageEntity extends IncentiveEntity {
    public TrialPackageEntity() {
    }

    public TrialPackageEntity(String name) {
        this.setName(name);
    }
    public TrialPackageEntity(long id, String name) {
        this.setId(id);
        this.setName(name);
    }
}
