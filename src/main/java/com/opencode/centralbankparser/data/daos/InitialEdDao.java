package com.opencode.centralbankparser.data.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.data.entities.InitialEdEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class InitialEdDao implements DaoDataInterface<InitialEdEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialEdDao.class);

    @Override
    public List<InitialEdEntity> getAll() {
        return (List<InitialEdEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM InitialEdEntity").list();
    }

    @Override
    public void save(InitialEdEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("InitialEd with id={} was saved", entity.getIdInitialEd());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("InitialEd wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(InitialEdEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("InitialEd with id={} was updated", entity.getIdInitialEd());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("InitialEd wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            InitialEdEntity initialEd = session.get(InitialEdEntity.class, id);
            session.delete(initialEd);
            tx.commit();
            LOGGER.info("InitialEd with id={} was deleted", initialEd.getIdInitialEd());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("InitialEd wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public InitialEdEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            InitialEdEntity initialEd = session.get(InitialEdEntity.class, id);
            if (initialEd != null)
                LOGGER.info("InitialEd with id={} was found", id);
            else
                LOGGER.warn("InitialEd with id={} wasn't found", id);
            return initialEd;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding InitialEd with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (InitialEdEntity initialEd : getAll())
                session.delete(initialEd);
            tx.commit();
            LOGGER.info("All InitialEd was deleted");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't delete all InitialEd");
            LOGGER.error(e.getMessage());
        }
    }
}

