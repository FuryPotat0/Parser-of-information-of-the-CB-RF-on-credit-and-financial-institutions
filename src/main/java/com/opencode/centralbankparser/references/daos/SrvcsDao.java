package com.opencode.centralbankparser.references.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.references.entities.SrvcsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class SrvcsDao implements DaoReferencesInterface<SrvcsEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrvcsDao.class);

    @Override
    public List<SrvcsEntity> getAll() {
        return (List<SrvcsEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM SrvcsEntity").list();
    }

    @Override
    public void save(SrvcsEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("Srvcs with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Srvcs wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(SrvcsEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("Srvcs with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Srvcs wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            SrvcsEntity srvcs = session.get(SrvcsEntity.class, id);
            session.delete(srvcs);
            tx.commit();
            LOGGER.info("Srvcs with code={} was deleted", srvcs.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Srvcs wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<SrvcsEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM SrvcsEntity WHERE CODE = ?0";
            Query query = session.createQuery(hql).setParameter(0, code);
            SrvcsEntity srvcs = (SrvcsEntity) query.getSingleResult();
            LOGGER.info("Found Srvcs with code={} and id={}", code, srvcs.getIdSrvcs());
            return Optional.of(srvcs);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding Srvcs with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("Srvcs with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public SrvcsEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            SrvcsEntity infoTypeCode = session.get(SrvcsEntity.class, id);
            if (infoTypeCode != null)
                LOGGER.info("Srvcs with id={} was found", id);
            else
                LOGGER.warn("Srvcs with id={} wasn't found", id);
            return infoTypeCode;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding Srvcs with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

