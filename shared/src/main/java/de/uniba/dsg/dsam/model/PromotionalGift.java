package de.uniba.dsg.dsam.model;

public class PromotionalGift extends Incentive {

    public PromotionalGift(Long id, String name) {
        this.setName(name);
        this.setId(id);
    }

    public PromotionalGift(Long id, String name, Long version) {
        this.setName(name);
        this.setId(id);
        this.setVersion(version);
    }

    public PromotionalGift(String name) {
        this.setName(name);
    }
}
