package com.opencode.centralbankparser.data.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.data.entities.RstrListEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RstrListDao implements DaoDataInterface<RstrListEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RstrListDao.class);

    @Override
    public List<RstrListEntity> getAll() {
        return (List<RstrListEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM RstrListEntity").list();
    }

    @Override
    public void save(RstrListEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("RstrList with id={} was saved", entity.getIdRstrList());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("RstrList wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(RstrListEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("RstrList with id={} was updated", entity.getIdRstrList());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("RstrList wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            RstrListEntity rstrList = session.get(RstrListEntity.class, id);
            session.delete(rstrList);
            tx.commit();
            LOGGER.info("RstrList with id={} was deleted", rstrList.getIdRstrList());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("RstrList wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public RstrListEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            RstrListEntity rstrList = session.get(RstrListEntity.class, id);
            if (rstrList != null)
                LOGGER.info("RstrList with id={} was found", id);
            else
                LOGGER.warn("RstrList with id={} wasn't found", id);
            return rstrList;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding RstrList with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (RstrListEntity rstrList : getAll())
                session.delete(rstrList);
            tx.commit();
            LOGGER.info("All RstrList was deleted");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't delete all RstrList");
            LOGGER.error(e.getMessage());
        }
    }
}

