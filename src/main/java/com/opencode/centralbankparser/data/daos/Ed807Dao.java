package com.opencode.centralbankparser.data.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.data.entities.Ed807Entity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Ed807Dao implements DaoDataInterface<Ed807Entity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Ed807Dao.class);

    @Override
    public List<Ed807Entity> getAll() {
        return (List<Ed807Entity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Ed807Entity").list();
    }

    @Override
    public void save(Ed807Entity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("Ed807 with id={} was saved", entity.getIdEd());
        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
            LOGGER.error("Ed807 wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Ed807Entity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("Ed807 with id={} was updated", entity.getIdEd());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Ed807 wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Ed807Entity ed807 = session.get(Ed807Entity.class, id);
            session.delete(ed807);
            tx.commit();
            LOGGER.info("Ed807 with id={} was deleted", ed807.getIdEd());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Ed807 wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Ed807Entity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Ed807Entity ed807 = session.get(Ed807Entity.class, id);
            if (ed807 != null)
                LOGGER.info("Ed807 with id={} was found", id);
            else
                LOGGER.warn("Ed807 with id={} wasn't found", id);
            return ed807;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding Ed807 with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (Ed807Entity ed807 : getAll())
                session.delete(ed807);
            tx.commit();
            LOGGER.info("All Ed807 was deleted");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't delete all Ed807");
            LOGGER.error(e.getMessage());
        }
    }
}

