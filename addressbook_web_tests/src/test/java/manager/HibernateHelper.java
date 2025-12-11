package manager;

import manager.hbm.ClientRecord;
import manager.hbm.GroupRecord;
import model.ClientData;
import org.hibernate.cfg.Configuration;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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
       return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
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
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static ClientData convert(ClientRecord record) {
        return new ClientData().withId("" + record.id)
                .withFirstname(record.firstname)
                .withLastname( record.lastname)
                .withAddress(record.address)
                .withEmail(record.email)
                .withHome(record.home)
                .withWork(record.work)
                .withMobile(record.mobile)
                .withSecondary(record.phone2)
                .withEmail2(record.email2)
                .withEmail3(record.email3)
                .withAddress2(record.address2);
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
    public ClientData createClient(ClientData clientData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(clientData));
            session.getTransaction().commit();
        });
        return clientData;
    }

    public List<ClientData> getClientsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
           return convertClientList(session.get(GroupRecord.class, group.id()).clients);
        });
    }

    public Optional<Map.Entry<ClientData, GroupData>> findClientNotInAnyGroup() {
        for (ClientData contact : getClientList()) {
            for (GroupData group : getGroupList()) {
                if (!getClientsInGroup(group).contains(contact)) {
                    return Optional.of(Map.entry(contact, group));
                }
            }
        }
        return Optional.empty();
    }

}
