package com.opencode.centralbankparser.daos;

import com.opencode.centralbankparser.HibernateSessionFactoryUtil;
import com.opencode.centralbankparser.entities.Ed807Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Ed807Dao {
    private static final Logger LOGGER = LoggerFactory.getLogger(Ed807Dao.class);

    public List<Ed807Entity> getAll(){
        return (List<Ed807Entity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Ed807Entity").list();
    }
}

