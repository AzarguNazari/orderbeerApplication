package de.uniba.dsg.dsam.model;

public class TrialPackage extends Incentive {

    public TrialPackage(Long id, String name) {
        this.setName(name);
        this.setId(id);
    }

    public TrialPackage(Long id, String name, Long version) {
        this.setName(name);
        this.setId(id);
        this.setVersion(version);
    }

    public TrialPackage(String name) {
        this.setName(name);
    }
}
