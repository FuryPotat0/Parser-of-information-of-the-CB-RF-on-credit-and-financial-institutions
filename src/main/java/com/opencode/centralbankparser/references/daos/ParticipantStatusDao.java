package com.opencode.centralbankparser.references.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.references.entities.ParticipantStatusEntity;
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

public class ParticipantStatusDao implements DaoReferencesInterface<ParticipantStatusEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantStatusDao.class);

    @Override
    public List<ParticipantStatusEntity> getAll() {
        return (List<ParticipantStatusEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM ParticipantStatusEntity").list();
    }

    @Override
    public void save(ParticipantStatusEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("ParticipantStatus with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("ParticipantStatus wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(ParticipantStatusEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("ParticipantStatus with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("ParticipantStatus wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ParticipantStatusEntity participantStatus = session.get(ParticipantStatusEntity.class, id);
            session.delete(participantStatus);
            tx.commit();
            LOGGER.info("ParticipantStatus with code={} was deleted", participantStatus.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("ParticipantStatus wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<ParticipantStatusEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM ParticipantStatusEntity WHERE code = ?0";
            Query query = session.createQuery(hql).setParameter(0, code);
            ParticipantStatusEntity participantStatus = (ParticipantStatusEntity) query.getSingleResult();
            LOGGER.info("Found ParticipantStatus with code={} and id={}", code, participantStatus.getIdParticipantStatus());
            return Optional.of(participantStatus);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding ParticipantStatus with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("ParticipantStatus with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public ParticipantStatusEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            ParticipantStatusEntity participantStatus = session.get(ParticipantStatusEntity.class, id);
            if (participantStatus != null)
                LOGGER.info("ParticipantStatus with id={} was found", id);
            else
                LOGGER.warn("ParticipantStatus with id={} wasn't found", id);
            return participantStatus;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding ParticipantStatus with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

