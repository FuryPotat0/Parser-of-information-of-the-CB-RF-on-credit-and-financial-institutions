package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.AccRstrListEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class AccRstrListDao implements DaoInfoInterface<AccRstrListEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccRstrListDao.class);

    @Override
    public List<AccRstrListEntity> getAll() {
        return (List<AccRstrListEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM AccRstrListEntity").list();
    }

    @Override
    public void save(AccRstrListEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("AccRstrList with id={} was saved", entity.getIdAccRstrList());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("AccRstrList wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(AccRstrListEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("AccRstrList with id={} was updated", entity.getIdAccRstrList());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("AccRstrList wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            AccRstrListEntity accRstrList = session.get(AccRstrListEntity.class, id);
            session.delete(accRstrList);
            tx.commit();
            LOGGER.info("AccRstrList with id={} was deleted", accRstrList.getIdAccRstrList());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("AccRstrList wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public AccRstrListEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            AccRstrListEntity accRstrList = session.get(AccRstrListEntity.class, id);
            if (accRstrList != null)
                LOGGER.info("AccRstrList with id={} was found", id);
            else
                LOGGER.warn("AccRstrList with id={} wasn't found", id);
            return accRstrList;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding AccRstrList with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (AccRstrListEntity accRstrList : getAll())
                session.delete(accRstrList);
            tx.commit();
            LOGGER.info("All AccRstrList was deleted");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't delete all AccRstrList");
            LOGGER.error(e.getMessage());
        }
    }
}

