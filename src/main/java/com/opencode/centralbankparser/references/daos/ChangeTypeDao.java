package com.opencode.centralbankparser.references.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.references.entities.ChangeTypeEntity;
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

public class ChangeTypeDao implements DaoReferencesInterface<ChangeTypeEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeTypeDao.class);

    @Override
    public List<ChangeTypeEntity> getAll() {
        return (List<ChangeTypeEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM ChangeTypeEntity").list();
    }

    @Override
    public void save(ChangeTypeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("ChangeType with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("ChangeType wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(ChangeTypeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("ChangeType with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("ChangeType wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ChangeTypeEntity changeType = session.get(ChangeTypeEntity.class, id);
            session.delete(changeType);
            tx.commit();
            LOGGER.info("ChangeType with code={} was deleted", changeType.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("ChangeType wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<ChangeTypeEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM ChangeTypeEntity WHERE code = ?0";

            Query query = session.createQuery(hql).setParameter(0, code);
            ChangeTypeEntity changeType = (ChangeTypeEntity) query.getSingleResult();
            LOGGER.info("Found ChangeType with code={} and id={}", code, changeType.getIdChangeType());
            return Optional.of(changeType);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding ChangeType with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("ChangeType with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public ChangeTypeEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            ChangeTypeEntity changeType = session.get(ChangeTypeEntity.class, id);
            if (changeType != null)
                LOGGER.info("ChangeType with id={} was found", id);
            else
                LOGGER.warn("ChangeType with id={} wasn't found", id);
            return changeType;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding ChangeType with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

