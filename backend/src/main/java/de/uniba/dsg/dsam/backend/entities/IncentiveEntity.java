package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "incentive")
@NamedQueries({
        @NamedQuery(
                name = "Incentive.findAll",
                query = "SELECT i FROM IncentiveEntity i"
        )
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class IncentiveEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "name")
    private String name;
    @Version
    private long version;
    @OneToMany(mappedBy = "incentive", fetch = FetchType.LAZY)
    private List<BeverageEntity> beverages;

    public IncentiveEntity() {
    }

    public List<BeverageEntity> getBeverages() {
        if (this.beverages == null) {
            return new ArrayList<BeverageEntity>();
        } else {
            return new ArrayList<BeverageEntity>(this.beverages);
        }
    }

    public void setBeverages(List<BeverageEntity> beverages) {
        this.beverages = beverages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }
}
