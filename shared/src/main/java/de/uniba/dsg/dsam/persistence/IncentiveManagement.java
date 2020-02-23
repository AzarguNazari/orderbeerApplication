package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.Incentive;

import java.util.List;

public interface IncentiveManagement {

    public void insertIncentive(Incentive incentive);

    public List<Incentive> getIncentives();

    public void removeIncentive(long id) throws Exception;

    public Incentive findById(long id);

    public void edit(long id, String name, long version) throws Exception;
}
