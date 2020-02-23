package de.uniba.dsg.dsam.model;

/**
 * Incentive model consists of:
 * - long id
 * - String name
 * - long version
 */

public abstract class Incentive {
    private long id;
    private String name;
    private long version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
