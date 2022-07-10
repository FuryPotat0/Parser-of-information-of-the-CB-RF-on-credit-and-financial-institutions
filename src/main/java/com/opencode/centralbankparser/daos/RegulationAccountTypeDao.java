package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.RegulationAccountTypeEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class RegulationAccountTypeDao implements DaoReferencesInterface<RegulationAccountTypeEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegulationAccountTypeDao.class);

    @Override
    public List<RegulationAccountTypeEntity> getAll() {
        return (List<RegulationAccountTypeEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM RegulationAccountTypeEntity").list();
    }

    @Override
    public void save(RegulationAccountTypeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("RegulationAccountType with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("RegulationAccountType wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(RegulationAccountTypeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("RegulationAccountType with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("RegulationAccountType wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            RegulationAccountTypeEntity regulationAccountType = session.get(RegulationAccountTypeEntity.class, id);
            session.delete(regulationAccountType);
            tx.commit();
            LOGGER.info("RegulationAccountType with code={} was deleted", regulationAccountType.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("RegulationAccountType wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<RegulationAccountTypeEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM RegulationAccountTypeEntity WHERE code = ?0";
            Query query = session.createQuery(hql).setParameter(0, code);
            RegulationAccountTypeEntity regulationAccountType = (RegulationAccountTypeEntity) query.getSingleResult();
            LOGGER.info("Found RegulationAccountType with code={} and id={}", code,
                    regulationAccountType.getIdRegulationAccountType());
            return Optional.of(regulationAccountType);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding RegulationAccountType with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("RegulationAccountType with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public RegulationAccountTypeEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            RegulationAccountTypeEntity regulationAccountType = session.get(RegulationAccountTypeEntity.class, id);
            if (regulationAccountType != null)
                LOGGER.info("RegulationAccountType with id={} was found", id);
            else
                LOGGER.warn("RegulationAccountType with id={} wasn't found", id);
            return regulationAccountType;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding RegulationAccountType with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

