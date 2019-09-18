package sp9gi.callog.ejb;

import sp9gi.callog.jpa.CallSignsDB;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class CallSignsDAO {

    private static Logger LOGGER = Logger.getLogger("CallSignsDAO");

    //create entity manager to access the DB
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    public void addData1() {

        CallSignsDB ham = new CallSignsDB();

        LOGGER.info("rozpoczęto transakcję");

        entityManager.getTransaction().begin();
        entityManager.persist(ham);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }


    //dodaje wpis do bazy
    public void addData2(String call_sign, String operator_name, String band, String raport_send, String raport_received, String mail, String password, Date date, String mode, String confirmation, String note) {
        LOGGER.info("tworzy się nowy obiekt client");
        CallSignsDB ham2 = new CallSignsDB(call_sign, operator_name, band, raport_send, raport_received, mail, password, date, mode, confirmation, note);
        LOGGER.info("rozpoczęto transakcję");
        entityManager.getTransaction().begin();
        entityManager.persist(ham2);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }


    //pobiera listę łączności
    public List<CallSignsDB> getCallSigns() {
        LOGGER.info("rozpoczęto transakcję");
        Query query = entityManager.createQuery("SELECT c FROM CallSignsDB c");
        List<CallSignsDB> callSignsList = query.getResultList();
        return callSignsList;
    }


    public List<CallSignsDB> getSingleCallSign(int id) {
        LOGGER.info("rozpoczęto transakcję");
        Query query = entityManager.createQuery("SELECT c FROM CallSignsDB c WHERE c.id = :Id");
        query.setParameter("Id", id);
        List<CallSignsDB> callSignsList = query.getResultList();
        return callSignsList;
    }

    public List<CallSignsDB> getUserCallSigns(String callSign) {
        LOGGER.info("rozpoczęto transakcję");
        Query query = entityManager.createQuery("SELECT c FROM CallSignsDB c WHERE c.call_sign = :CS");
        query.setParameter("CS", callSign);
        List<CallSignsDB> callSignsList = query.getResultList();
        return callSignsList;
    }


    public void updateDB(int id, String call_sign, String operator_name, String band, String raport_send, String raport_received, String mail, Date date, String mode, String confirmation, String note) {
        LOGGER.info("rozpoczęto transakcję update");
        Query query = entityManager.createQuery("UPDATE CallSignsDB  c SET c.call_sign = ?2, c.operator_name = ?3, c.band = ?4, c.raport_send = ?5, c.raport_received = ?6, c.mail = ?7, c.date = ?8, c.mode = ?9, c.confirmation = ?10, c.note = ?11 WHERE c.id = ?1");
        entityManager.getTransaction().begin();
        query.setParameter(1, id).setParameter(2, call_sign).setParameter(3, operator_name).setParameter(4, band).setParameter(5, raport_send).setParameter(6, raport_received).setParameter(7, mail).setParameter(8, date).setParameter(9, mode).setParameter(10, confirmation).setParameter(11, note);
        query.executeUpdate();
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }

    public void deleteCall(int id) {
        Query query = entityManager.createQuery("DELETE FROM CallSignsDB  c WHERE c.id = ?1");
        entityManager.getTransaction().begin();
        query.setParameter(1, id);
        query.executeUpdate();
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }


}
