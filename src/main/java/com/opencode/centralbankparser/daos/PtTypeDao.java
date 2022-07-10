package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.PtTypeEntity;
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

public class PtTypeDao implements DaoReferencesInterface<PtTypeEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PtTypeDao.class);

    @Override
    public List<PtTypeEntity> getAll() {
        return (List<PtTypeEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM PtTypeEntity").list();
    }

    @Override
    public void save(PtTypeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("PtType with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("PtType wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(PtTypeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("PtType with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("PtType wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            PtTypeEntity ptType = session.get(PtTypeEntity.class, id);
            session.delete(ptType);
            tx.commit();
            LOGGER.info("PtType with code={} was deleted", ptType.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("PtType wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<PtTypeEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM PtTypeEntity WHERE code = ?0";
            Query query = session.createQuery(hql).setParameter(0, code);
            PtTypeEntity ptType = (PtTypeEntity) query.getSingleResult();
            LOGGER.info("Found PtType with code={} and id={}", code, ptType.getIdPtType());
            return Optional.of(ptType);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding PtType with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("PtType with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public PtTypeEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            PtTypeEntity ptType = session.get(PtTypeEntity.class, id);
            if (ptType != null)
                LOGGER.info("PtType with id={} was found", id);
            else
                LOGGER.warn("PtType with id={} wasn't found", id);
            return ptType;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding PtType with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

