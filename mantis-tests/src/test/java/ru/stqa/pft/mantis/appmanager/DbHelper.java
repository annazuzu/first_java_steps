package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.MantisUserData;
import ru.stqa.pft.mantis.model.MantisUsers;

import java.util.List;

public class DbHelper extends HelperBase {

    private final SessionFactory sessionFactory;

    public DbHelper(ApplicationManager app) {
        super(app);
        //копируем кусок кода из метода setUp()
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

    }

    public MantisUsers users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<MantisUserData> result = session.createQuery( "from MantisUserData" ).list();
        session.getTransaction().commit();
        session.close();
        return new MantisUsers(result);
    }

}
