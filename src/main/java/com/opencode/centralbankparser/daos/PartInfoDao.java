package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.PartInfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class PartInfoDao implements DaoInfoInterface<PartInfoEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartInfoDao.class);

    @Override
    public List<PartInfoEntity> getAll() {
        return (List<PartInfoEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM PartInfoEntity").list();
    }

    @Override
    public void save(PartInfoEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("PartInfo with id={} was saved", entity.getIdPartInfo());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("PartInfo wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(PartInfoEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("PartInfo with id={} was updated", entity.getIdPartInfo());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("PartInfo wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            PartInfoEntity partInfo = session.get(PartInfoEntity.class, id);
            session.delete(partInfo);
            tx.commit();
            LOGGER.info("PartInfo with id={} was deleted", partInfo.getIdPartInfo());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("PartInfo wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public PartInfoEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            PartInfoEntity partInfo = session.get(PartInfoEntity.class, id);
            if (partInfo != null)
                LOGGER.info("PartInfo with id={} was found", id);
            else
                LOGGER.warn("PartInfo with id={} wasn't found", id);
            return partInfo;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding PartInfo with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (PartInfoEntity partInfo : getAll())
                session.delete(partInfo);
            tx.commit();
            LOGGER.info("All PartInfo was deleted");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't delete all PartInfo");
            LOGGER.error(e.getMessage());
        }
    }
}

