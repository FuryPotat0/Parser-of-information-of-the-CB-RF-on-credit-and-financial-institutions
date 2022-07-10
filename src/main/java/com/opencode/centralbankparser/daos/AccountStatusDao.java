package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.AccountStatusEntity;
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

public class AccountStatusDao implements DaoReferencesInterface<AccountStatusEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountStatusDao.class);

    @Override
    public List<AccountStatusEntity> getAll() {
        return (List<AccountStatusEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM InfoTypeCodeEntity").list();
    }

    @Override
    public void save(AccountStatusEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("AccountStatus with code={} was saved", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("AccountStatus wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(AccountStatusEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("AccountStatus with code={} was updated", entity.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("AccountStatus wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            AccountStatusEntity accountStatus = session.get(AccountStatusEntity.class, id);
            session.delete(accountStatus);
            tx.commit();
            LOGGER.info("AccountStatus with code={} was deleted", accountStatus.getCode());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("AccountStatus wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Optional<AccountStatusEntity> findByCode(String code) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "FROM AccountStatusEntity WHERE code = ?0";

            Query query = session.createQuery(hql).setParameter(0, code);
            AccountStatusEntity accountStatus = (AccountStatusEntity) query.getSingleResult();
            LOGGER.info("Found AccountStatus with code={} and id={}", code, accountStatus.getIdAccountStatus());
            return Optional.of(accountStatus);
        } catch (HibernateException e){
            LOGGER.error("Exception while finding AccountStatus with code={}", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        } catch (NoResultException e){
            LOGGER.error("AccountStatus with code={} wasn't found", code);
            LOGGER.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public AccountStatusEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            AccountStatusEntity accountStatus = session.get(AccountStatusEntity.class, id);
            if (accountStatus != null)
                LOGGER.info("AccountStatus with id={} was found", id);
            else
                LOGGER.warn("AccountStatus with id={} wasn't found", id);
            return accountStatus;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding AccountStatus with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

