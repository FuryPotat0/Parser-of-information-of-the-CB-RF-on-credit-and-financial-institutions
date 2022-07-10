package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.AccountsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountsDao implements DaoInfoInterface<AccountsEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsDao.class);

    @Override
    public List<AccountsEntity> getAll() {
        return (List<AccountsEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM AccountsEntity").list();
    }

    @Override
    public void save(AccountsEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOGGER.info("Account with id={} was saved", entity.getIdAccounts());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Account wasn't saved");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(AccountsEntity entity) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            LOGGER.info("Account with id={} was updated", entity.getIdAccounts());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Account wasn't updated");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DataIntegrityViolationException {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            AccountsEntity account = session.get(AccountsEntity.class, id);
            session.delete(account);
            tx.commit();
            LOGGER.info("Account with id={} was deleted", account.getIdAccounts());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Account wasn't deleted");
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public AccountsEntity findById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            AccountsEntity account = session.get(AccountsEntity.class, id);
            if (account != null)
                LOGGER.info("Account with id={} was found", id);
            else
                LOGGER.warn("Account with id={} wasn't found", id);
            return account;
        } catch (HibernateException e) {
            LOGGER.error("Exception while finding Account with id={}", id);
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (AccountsEntity account : getAll())
                session.delete(account);
            tx.commit();
            LOGGER.info("All Accounts was deleted");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            LOGGER.error("Can't delete all Accounts");
            LOGGER.error(e.getMessage());
        }
    }
}

