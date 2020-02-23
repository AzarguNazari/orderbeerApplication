package de.uniba.dsg.dsam.backend.beans;

/**
 * This is the Beverage Management bean
 *
 */

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.BeverageManagement;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Beverage management bean class implements BeverageManagement interface
 * This is a stateless EJB
 */


/*

            Annotations

            - heavily used in Java EE (optional) replacement of deployment descriptor
            - The purpose of it is to have a declarative programming style where the programmer says what should be done and tools emit the code to do it


            Where to insert annotations?
            - wherever modifiers are eligible (public, abstract, ...)
            - per convention before actual modifiers are inserted
            - according to @Target


            When are annotation contents available?
            - SOURCE, CLASS, RUNTIME
            - @Retention


            Rules for declaring annotations
            - @interface
            - methods definitions
                - no parameters
                - no throws
                - Admissible return values
                    - primitive types (int, double, long, byte, ..)
                    - String
                    - Class
                    - Enum
                    - Annotations
                    - Arrays of the above types
                - Definition of default values

 */

@Stateless
@Local(BeverageManagement.class)
public class BeverageManagementBean implements BeverageManagement {
    private static final Logger logger = Logger.getLogger(IncentiveManagementBean.class.getName());

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    public BeverageManagementBean() {
    }

    @Override
    public void create(Beverage beverage) {
        IncentiveEntity incentiveEntity = null;
        if (beverage.getIncentive() != null) {
            incentiveEntity = incentiveModelToEntity(beverage.getIncentive());
        }
        BeverageEntity entity = new BeverageEntity(
                beverage.getName(),
                beverage.getManufacturer(),
                beverage.getQuantity(),
                beverage.getPrice(),
                incentiveEntity
        );
        em.persist(entity);
    }

    @Override
    public List<Beverage> getBeverages() {
        List<BeverageEntity> beverageEntities = em.
                createNamedQuery("Beverage.findAll")
                .getResultList();
        return convert(beverageEntities);
    }

    @Override
    public Beverage findById(long id) {
        BeverageEntity entity = em.find(BeverageEntity.class, id);
        return convert(entity);
    }

    @Override
    public boolean isThisIncentiveAlreadyAssigned(long id) {
        return ((Number) em
                .createNamedQuery("Beverage.isThisIncentiveAssigned")
                .setParameter("id", id)
                .getSingleResult())
                .intValue() > 0;
    }

    private List<Beverage> convert(List<BeverageEntity> beverageEntities) {
        List<Beverage> ret = new ArrayList<>();

        if (beverageEntities != null) {
            for (BeverageEntity beverageEntity : beverageEntities) {
                ret.add(convert(beverageEntity));
            }
        }

        return ret;
    }

    private Beverage convert(BeverageEntity beverageEntity) {
        Incentive incentive = incentiveEntityToModel(beverageEntity.getIncentive());
        return new Beverage(
                beverageEntity.getId(),
                beverageEntity.getName(),
                beverageEntity.getManufacturer(),
                beverageEntity.getQuantity(),
                beverageEntity.getPrice(),
                incentive
        );
    }

    private IncentiveEntity incentiveModelToEntity(Incentive incentive) {
        IncentiveEntity incentiveEntity;
        if (incentive instanceof TrialPackage) {
            incentiveEntity = new TrialPackageEntity(
                    incentive.getId(),
                    incentive.getName()
            );
        } else {
            incentiveEntity = new PromotionalGiftEntity(
                    incentive.getId(),
                    incentive.getName()
            );
        }
        return incentiveEntity;
    }

    private Incentive incentiveEntityToModel(IncentiveEntity incentiveEntity) {
        Incentive incentive;
        if (incentiveEntity == null) {
            return null;
        }

        if (incentiveEntity instanceof TrialPackageEntity) {
            incentive = new TrialPackage(incentiveEntity.getId(), incentiveEntity.getName());
        } else {
            incentive = new PromotionalGift(incentiveEntity.getId(), incentiveEntity.getName());
        }

        return incentive;
    }
}
