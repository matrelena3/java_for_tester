package manager;

import manager.hbm.ClientRecord;
import manager.hbm.GroupRecord;
import model.ClientData;
import org.hibernate.cfg.Configuration;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManadger manager) {
    super(manager);
      sessionFactory = new Configuration()
                        .addAnnotatedClass(ClientRecord.class)
                        .addAnnotatedClass(GroupRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
       return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", long.class).getSingleResult();
        });
    }

    static List<ClientData> convertClientList(List<ClientRecord> records) {
        List<ClientData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static ClientData convert(ClientRecord record) {
        return new ClientData().withId("" + record.id)
                               .withFirstname(record.firstname)
                               .withLastname( record.lastname)
                               .withAddress(record.address)
                               .withEmail(record.email);
    }

    private static ClientRecord convert(ClientData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ClientRecord(Integer.parseInt(id), data.firstname(), data.lastname(), data.address(), data.email());
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public List<ClientData> getClientList() {
        return convertClientList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ClientRecord", ClientRecord.class).list();
        }));
    }

    public long getClientCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ClientRecord", long.class).getSingleResult();
        });
    }
    public void createClient(ClientData clientData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(clientData));
            session.getTransaction().commit();
        });
    }

    public List<ClientData> getClientsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
           return convertClientList(session.get(GroupRecord.class, group.id()).clients);
        });
    }
}
