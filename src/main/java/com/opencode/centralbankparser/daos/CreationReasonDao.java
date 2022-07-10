package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.CreationReasonEntity;
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

public class CreationReasonDao implements DaoReferencesInterface<CreationReasonEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreationReasonDao.class);


    @Override
    public List<CreationReasonEntity> getAll() {
        return (List<CreationReasonEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM CreationReasonEntity").list();
    }

    @Override
    public void save(CreationReasonEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("CreationReason with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("CreationReason wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(CreationReasonEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("CreationReason with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("CreationReason wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            CreationReasonEntity creationReason = session.get(CreationReasonEntity.class, id);
            session.delete(creationReason);
            tx.commit();
            LOGGER.info("CreationReason with code={} was deleted", creationReason.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("CreationReason wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<CreationReasonEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM CreationReasonEntity WHERE code = ?0";
            Query query = session.createQuery(hql).setParameter(0, code);
            CreationReasonEntity creationReason = (CreationReasonEntity) query.getSingleResult();
            LOGGER.info("Found CreationReason with code={} and id={}", code, creationReason.getIdCreationReason());
            return Optional.of(creationReason);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding CreationReason with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("CreationReason with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public CreationReasonEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CreationReasonEntity creationReason = session.get(CreationReasonEntity.class, id);
            if (creationReason != null)
                LOGGER.info("CreationReason with id={} was found", id);
            else
                LOGGER.warn("CreationReason with id={} wasn't found", id);
            return creationReason;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding CreationReason with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

