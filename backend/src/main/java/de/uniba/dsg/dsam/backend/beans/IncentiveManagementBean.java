package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 */

@Stateless
@Local(IncentiveManagement.class)
public class IncentiveManagementBean implements IncentiveManagement {

    private static final Logger logger = Logger.getLogger(IncentiveManagementBean.class.getName());

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @EJB
    BeverageManagement beverageManagement;

    public IncentiveManagementBean() {
    }


    @Override
    public void insertIncentive(Incentive incentiveDTO) {
        IncentiveEntity incentiveEntity;
        if (incentiveDTO instanceof TrialPackage) {
            incentiveEntity = new TrialPackageEntity(incentiveDTO.getName());
        } else {
            incentiveEntity = new PromotionalGiftEntity(incentiveDTO.getName());
        }
        em.persist(incentiveEntity);
    }

    @Override
    public List<Incentive> getIncentives() {
        List<IncentiveEntity> incentiveEntities = em.createNamedQuery("Incentive.findAll").getResultList();

        return convert(incentiveEntities);
    }

    @Override
    public void removeIncentive(long id) throws Exception {
        if (!beverageManagement.isThisIncentiveAlreadyAssigned(id)) {
            IncentiveEntity entity = em.find(IncentiveEntity.class, id);
            em.remove(entity);
        }

        throw new Exception("Incentive Already Assigned");

    }

    @Override
    public Incentive findById(long id) {
        return convert(em.find(IncentiveEntity.class, id));
    }

    @Override
    public void edit(long id, String name, long version) throws Exception {
        IncentiveEntity incentiveEntity = em.find(IncentiveEntity.class, id);
        if (incentiveEntity == null) {
            throw new IllegalArgumentException("Invalid Id");
        }
        if (incentiveEntity.getVersion() != version) {
            throw new Exception("Optimistic lock error");
        }
        incentiveEntity.setName(name);
        em.flush();
    }

    private List<Incentive> convert(List<IncentiveEntity> entities) {
        List<Incentive> ret = new ArrayList<>();

        if (entities != null) {
            for (IncentiveEntity incentiveEntity : entities) {
                ret.add(convert(incentiveEntity));
            }
        }

        return ret;
    }

    private Incentive convert(IncentiveEntity incentiveEntity) {
        if (incentiveEntity instanceof PromotionalGiftEntity) {
            return new PromotionalGift(incentiveEntity.getId(), incentiveEntity.getName(), incentiveEntity.getVersion());
        } else {
            return new TrialPackage(incentiveEntity.getId(), incentiveEntity.getName(), incentiveEntity.getVersion());
        }
    }


}
