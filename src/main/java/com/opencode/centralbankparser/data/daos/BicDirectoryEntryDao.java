package com.opencode.centralbankparser.data.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.data.entities.BicDirectoryEntryEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BicDirectoryEntryDao implements DaoDataInterface<BicDirectoryEntryEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BicDirectoryEntryDao.class);

    @Override
    public List<BicDirectoryEntryEntity> getAll() {
        return (List<BicDirectoryEntryEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM BicDirectoryEntryEntity").list();
    }

    @Override
    public void save(BicDirectoryEntryEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("BicDirectoryEntry with id={} was saved", entity.getIdBicDirectoryEntry());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("BicDirectoryEntry wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(BicDirectoryEntryEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("BicDirectoryEntry with id={} was updated", entity.getIdBicDirectoryEntry());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("BicDirectoryEntry wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            BicDirectoryEntryEntity bicDirectoryEntry = session.get(BicDirectoryEntryEntity.class, id);
            session.delete(bicDirectoryEntry);
            tx.commit();
            LOGGER.info("BicDirectoryEntry with id={} was deleted", bicDirectoryEntry.getIdBicDirectoryEntry());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("BicDirectoryEntry wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public BicDirectoryEntryEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            BicDirectoryEntryEntity bicDirectoryEntry = session.get(BicDirectoryEntryEntity.class, id);
            if (bicDirectoryEntry != null)
                LOGGER.info("BicDirectoryEntry with id={} was found", id);
            else
                LOGGER.warn("BicDirectoryEntry with id={} wasn't found", id);
            return bicDirectoryEntry;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding BicDirectoryEntry with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (BicDirectoryEntryEntity bicDirectoryEntry : getAll())
                session.delete(bicDirectoryEntry);
            tx.commit();
            LOGGER.info("All BicDirectoryEntry was deleted");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't delete all BicDirectoryEntry");
            LOGGER.error(e.getMessage());
        }
    }
}

