package com.opencode.centralbankparser.references.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.references.entities.RstrEntity;
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

public class RstrDao implements DaoReferencesInterface<RstrEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RstrDao.class);

    @Override
    public List<RstrEntity> getAll() {
        return (List<RstrEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM RstrEntity").list();
    }

    @Override
    public void save(RstrEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("Rstr with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Rstr wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(RstrEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("Rstr with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Rstr wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            RstrEntity rstr = session.get(RstrEntity.class, id);
            session.delete(rstr);
            tx.commit();
            LOGGER.info("Rstr with code={} was deleted", rstr.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Rstr wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<RstrEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM RstrEntity WHERE code = ?0";
            Query query = session.createQuery(hql).setParameter(0, code);
            RstrEntity rstr = (RstrEntity) query.getSingleResult();
            LOGGER.info("Found Rstr with code={} and id={}", code, rstr.getIdRstr());
            return Optional.of(rstr);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding Rstr with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("Rstr with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public RstrEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            RstrEntity rstr = session.get(RstrEntity.class, id);
            if (rstr != null)
                LOGGER.info("Rstr with id={} was found", id);
            else
                LOGGER.warn("Rstr with id={} wasn't found", id);
            return rstr;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding Rstr with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

