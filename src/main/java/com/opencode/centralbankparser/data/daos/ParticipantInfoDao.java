package com.opencode.centralbankparser.data.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.data.entities.ParticipantInfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipantInfoDao implements DaoDataInterface<ParticipantInfoEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantInfoDao.class);

    @Override
    public List<ParticipantInfoEntity> getAll() {
        return (List<ParticipantInfoEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM ParticipantInfoEntity").list();
    }

    @Override
    public void save(ParticipantInfoEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("ParticipantInfo with id={} was saved", entity.getIdParticipantInfo());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("ParticipantInfo wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(ParticipantInfoEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("ParticipantInfo with id={} was updated", entity.getIdParticipantInfo());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("ParticipantInfo wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ParticipantInfoEntity participantInfo = session.get(ParticipantInfoEntity.class, id);
            session.delete(participantInfo);
            tx.commit();
            LOGGER.info("ParticipantInfo with id={} was deleted", participantInfo.getIdParticipantInfo());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("ParticipantInfo wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public ParticipantInfoEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            ParticipantInfoEntity participantInfo = session.get(ParticipantInfoEntity.class, id);
            if (participantInfo != null)
                LOGGER.info("ParticipantInfo with id={} was found", id);
            else
                LOGGER.warn("ParticipantInfo with id={} wasn't found", id);
            return participantInfo;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding ParticipantInfo with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (ParticipantInfoEntity participantInfo : getAll())
                session.delete(participantInfo);
            tx.commit();
            LOGGER.info("All ParticipantInfo was deleted");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't delete all ParticipantInfo");
            LOGGER.error(e.getMessage());
        }
    }
}

