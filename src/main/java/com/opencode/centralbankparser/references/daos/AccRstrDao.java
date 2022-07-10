package com.opencode.centralbankparser.references.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.references.entities.AccRstrEntity;
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

public class AccRstrDao implements DaoReferencesInterface<AccRstrEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccRstrDao.class);

    @Override
    public List<AccRstrEntity> getAll() {
        return (List<AccRstrEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM AccRstrEntity").list();
    }

    @Override
    public void save(AccRstrEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("AccRstr with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("AccRstr wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(AccRstrEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("AccRstr with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("AccRstr wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            AccRstrEntity accRstr = session.get(AccRstrEntity.class, id);
            session.delete(accRstr);
            tx.commit();
            LOGGER.info("AccRstr with code={} was deleted", accRstr.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("AccRstr wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<AccRstrEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM AccRstrEntity WHERE code = ?0";

            Query query = session.createQuery(hql).setParameter(0, code);
            AccRstrEntity accRstr = (AccRstrEntity) query.getSingleResult();
            LOGGER.info("Found AccRstr with code={} and id={}", code, accRstr.getIdAccRstr());
            return Optional.of(accRstr);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding AccRstr with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("AccRstr with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public AccRstrEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            AccRstrEntity accRstr = session.get(AccRstrEntity.class, id);
            if (accRstr != null)
                LOGGER.info("AccRstr with id={} was found", id);
            else
                LOGGER.warn("AccRstr with id={} wasn't found", id);
            return accRstr;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding AccRstr with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

