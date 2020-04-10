package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass

    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test

    public void testHbConnection () {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        for ( GroupData groups : result ) {
                System.out.println(groups);
            }
        session.getTransaction().commit();
        session.close();

//
//        Session session2 = sessionFactory.openSession();
//        session2.beginTransaction();
//        List<ContactData> result2 = session2.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
//        for ( ContactData contact : result2 ) {
//            System.out.println(contact);
//            System.out.println(contact.getGroups()); // выводится инфа о группах, в которые входит этот контакт
//        }
//        session2.getTransaction().commit();
//        session2.close();
//
    }

}
