package com.opencode.centralbankparser.data.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.data.entities.SwbicsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class SwbicsDao implements DaoDataInterface<SwbicsEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SwbicsDao.class);

    @Override
    public List<SwbicsEntity> getAll() {
        return (List<SwbicsEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM SwbicsEntity").list();
    }

    @Override
    public void save(SwbicsEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("Swbics with id={} was saved", entity.getIdSwbics());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Swbics wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(SwbicsEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("Swbics with id={} was updated", entity.getIdSwbics());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Swbics wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            SwbicsEntity swbics = session.get(SwbicsEntity.class, id);
            session.delete(swbics);
            tx.commit();
            LOGGER.info("Swbics with id={} was deleted", swbics.getIdSwbics());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Swbics wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public SwbicsEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            SwbicsEntity swbics = session.get(SwbicsEntity.class, id);
            if (swbics != null)
                LOGGER.info("Swbics with id={} was found", id);
            else
                LOGGER.warn("Swbics with id={} wasn't found", id);
            return swbics;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding Swbics with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (SwbicsEntity entity : getAll())
                session.delete(entity);
            tx.commit();
            LOGGER.info("All Swbics was deleted");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't delete all Swbics");
            LOGGER.error(e.getMessage());
        }
    }
}

