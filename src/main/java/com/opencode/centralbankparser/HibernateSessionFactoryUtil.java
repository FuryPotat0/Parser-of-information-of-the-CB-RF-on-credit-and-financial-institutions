package com.opencode.centralbankparser;

import com.opencode.centralbankparser.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();

                configuration.addAnnotatedClass(AccountsEntity.class);
                configuration.addAnnotatedClass(AccountStatusEntity.class);
                configuration.addAnnotatedClass(AccRstrEntity.class);
                configuration.addAnnotatedClass(AccRstrListEntity.class);
                configuration.addAnnotatedClass(BicDirectoryEntryEntity.class);
                configuration.addAnnotatedClass(ChangeTypeEntity.class);
                configuration.addAnnotatedClass(CreationReasonEntity.class);
                configuration.addAnnotatedClass(Ed807Entity.class);
                configuration.addAnnotatedClass(InfoTypeCodeEntity.class);
                configuration.addAnnotatedClass(InitialEdEntity.class);
                configuration.addAnnotatedClass(ParticipantInfoEntity.class);
                configuration.addAnnotatedClass(ParticipantStatusEntity.class);
                configuration.addAnnotatedClass(PartInfoEntity.class);
                configuration.addAnnotatedClass(PtTypeEntity.class);
                configuration.addAnnotatedClass(RegulationAccountTypeEntity.class);
                configuration.addAnnotatedClass(RstrEntity.class);
                configuration.addAnnotatedClass(RstrListEntity.class);
                configuration.addAnnotatedClass(SrvcsEntity.class);
                configuration.addAnnotatedClass(SwbicsEntity.class);
                configuration.addAnnotatedClass(XchTypeEntity.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
//                LOGGER.error(e.toString());
//                LOGGER.error(e.getMessage());
            }
        }
        return sessionFactory;
    }
}

