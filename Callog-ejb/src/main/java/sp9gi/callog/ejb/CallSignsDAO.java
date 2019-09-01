package sp9gi.callog.ejb;

import sp9gi.callog.jpa.CallSignsDB;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class CallSignsDAO {


    //TODO zestandaryzować wszystko, by kod się nie powtarzał

    private static Logger LOGGER = Logger.getLogger("CallSignsDAO");
    public void addData1() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        LOGGER.info("tworzy się nowy obiekt client");
        CallSignsDB ham = new CallSignsDB();

        LOGGER.info("rozpoczęto transakcję");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(ham);
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }

    //dodaje wpis do bazy
    public void addData2(String call_sign, String operator_name, String contact_date, String band, String raport_send, String raport_received, String mail, String password) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        LOGGER.info("tworzy się nowy obiekt client");
        CallSignsDB ham2 = new CallSignsDB(call_sign, operator_name, contact_date, band, raport_send, raport_received, mail, password);
        LOGGER.info("rozpoczęto transakcję");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(ham2);
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }


    //pobiera listę łączności
    public List<CallSignsDB> getCallSigns() {
        LOGGER.info("rozpoczęto transakcję");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM CallSignsDB c");
        List<CallSignsDB> callSignsList = query.getResultList();
        return callSignsList;
    }


    public List<CallSignsDB> getSingleCallSign(int id) {
        LOGGER.info("rozpoczęto transakcję");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM CallSignsDB c WHERE c.id = :Id");
        query.setParameter("Id", id);
        List<CallSignsDB> callSignsList = query.getResultList();
        return callSignsList;
    }

    public List<CallSignsDB> getUserCallSigns(String callSign) {
        LOGGER.info("rozpoczęto transakcję");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM CallSignsDB c WHERE c.call_sign = :CS");
        query.setParameter("CS", callSign);
        List<CallSignsDB> callSignsList = query.getResultList();
        return callSignsList;
    }


    public void updateDB(int id, String call_sign, String operator_name, String contact_date, String band, String raport_send, String raport_received, String mail) {
        LOGGER.info("rozpoczęto transakcję update");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("UPDATE CallSignsDB  c SET c.call_sign = ?2, c.operator_name = ?3, c.contact_date = ?4, c.band = ?5, c.raport_send = ?6, c.raport_received = ?7, c.mail = ?8 WHERE c.id = ?1");
        entityManager.getTransaction().begin();
        query.setParameter(1, id).setParameter(2, call_sign).setParameter(3, operator_name).setParameter(4, contact_date).setParameter(5, band).setParameter(6, raport_send).setParameter(7, raport_received).setParameter(8, mail);
        query.executeUpdate();
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }

    public void deleteCall(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("DELETE FROM CallSignsDB  c WHERE c.id = ?1");
        entityManager.getTransaction().begin();
        query.setParameter(1, id);
        query.executeUpdate();
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }


}
