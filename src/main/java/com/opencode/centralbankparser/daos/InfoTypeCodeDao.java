package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.InfoTypeCodeEntity;
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
public class InfoTypeCodeDao implements DaoReferencesInterface<InfoTypeCodeEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoTypeCodeDao.class);
    @Override
    public List<InfoTypeCodeEntity> getAll(){
        return (List<InfoTypeCodeEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM InfoTypeCodeEntity").list();
    }

    @Override
    public void save(InfoTypeCodeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("InfoTypeCode with id={} was saved", entity.getIdInfoTypeCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("InfoTypeCode wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(InfoTypeCodeEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("InfoTypeCode with id={} was updated", entity.getIdInfoTypeCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("InfoTypeCode wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            InfoTypeCodeEntity infoTypeCode = session.get(InfoTypeCodeEntity.class, id);
            session.delete(infoTypeCode);
            tx.commit();
            LOGGER.info("InfoTypeCode with id={} was deleted", infoTypeCode.getIdInfoTypeCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("InfoTypeCode wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<InfoTypeCodeEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM InfoTypeCodeEntity WHERE code = ?0";

            Query query = session.createQuery(hql).setParameter(0, code);
            InfoTypeCodeEntity infoTypeCode = (InfoTypeCodeEntity) query.getSingleResult();
            LOGGER.info("Found InfoTypeCode with code={} and id={}", code, infoTypeCode.getIdInfoTypeCode());
            return Optional.of(infoTypeCode);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding InfoTypeCode with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("InfoTypeCode with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public InfoTypeCodeEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            InfoTypeCodeEntity infoTypeCode = session.get(InfoTypeCodeEntity.class, id);
            if (infoTypeCode != null)
                LOGGER.info("InfoTypeCode with id={} was found", id);
            else
                LOGGER.warn("InfoTypeCode with id={} wasn't found", id);
            return infoTypeCode;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding InfoTypeCode with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

