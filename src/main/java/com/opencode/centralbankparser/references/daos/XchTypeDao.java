package com.opencode.centralbankparser.references.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.references.entities.XchTypeEntity;
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

public class XchTypeDao implements DaoReferencesInterface<XchTypeEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(XchTypeDao.class);

    @Override
    public List<XchTypeEntity> getAll() {
        return (List<XchTypeEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM XchTypeEntity").list();
    }

    @Override
    public void save(XchTypeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("XchType with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("XchType wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(XchTypeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("XchType with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("XchType wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            XchTypeEntity xchType = session.get(XchTypeEntity.class, id);
            session.delete(xchType);
            tx.commit();
            LOGGER.info("XchType with code={} was deleted", xchType.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("XchType wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<XchTypeEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM XchTypeEntity WHERE code = ?0";
            Query query = session.createQuery(hql).setParameter(0, code);
            XchTypeEntity xchType = (XchTypeEntity) query.getSingleResult();
            LOGGER.info("Found XchType with code={} and id={}", code, xchType.getIdXchType());
            return Optional.of(xchType);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding XchType with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("XchType with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public XchTypeEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            XchTypeEntity xchType = session.get(XchTypeEntity.class, id);
            if (xchType != null)
                LOGGER.info("XchType with id={} was found", id);
            else
                LOGGER.warn("XchType with id={} wasn't found", id);
            return xchType;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding XchType with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

