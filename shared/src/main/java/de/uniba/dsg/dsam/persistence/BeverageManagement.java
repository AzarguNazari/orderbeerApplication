package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.Beverage;

import java.util.List;

/**
 * This is basically the BeverageManagement interface
 * which is then implemented by the BeverageManagementBean class
 */

public interface BeverageManagement {

    /**
     * @param beverage
     * this method would be used to add the Beverage entity to the database
     */

    public void create(Beverage beverage);

    /**
     * @return <Beverage>
     * this method returns the list of Beverage objects
     */

    public List<Beverage> getBeverages();

    /**
     * this method is supposed to take the id of the beverage and return the
     * corresponding beverage from the database
     * @param id
     * @return Beverage entity
          */
    public Beverage findById(long id);

    /**
     * This method checks if the incentive is already assigned, it takes the incentive id
     * as the parameter
     * @param id
     * @return boolean
     */
    public boolean isThisIncentiveAlreadyAssigned(long id);
}
