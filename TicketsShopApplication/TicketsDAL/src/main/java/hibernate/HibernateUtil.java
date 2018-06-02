package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entities.FestivalObj;
import entities.FestivalToLocationObj;
import entities.LocationObj;
import entities.OwnerToFestivalObj;
import entities.TicketObj;
import entities.UserObj;

public class HibernateUtil
{

    private static final String CONFIG_FILE_NAME = "hibernate.cfg.xml";
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory()
    {
        try
        {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure(CONFIG_FILE_NAME).addAnnotatedClass(FestivalObj.class);
            configuration.configure(CONFIG_FILE_NAME).addAnnotatedClass(TicketObj.class);
            configuration.configure(CONFIG_FILE_NAME).addAnnotatedClass(UserObj.class);
            configuration.configure(CONFIG_FILE_NAME).addAnnotatedClass(LocationObj.class);
            configuration.configure(CONFIG_FILE_NAME).addAnnotatedClass(FestivalToLocationObj.class);
            configuration.configure(CONFIG_FILE_NAME).addAnnotatedClass(OwnerToFestivalObj.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}

