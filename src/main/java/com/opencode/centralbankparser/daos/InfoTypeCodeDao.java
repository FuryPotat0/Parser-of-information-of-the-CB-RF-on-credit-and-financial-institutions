package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.InfoTypeCodeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InfoTypeCodeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoTypeCodeDao.class);

    public List<InfoTypeCodeEntity> getAll(){
        return (List<InfoTypeCodeEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM InfoTypeCodeEntity").list();
    }
}

