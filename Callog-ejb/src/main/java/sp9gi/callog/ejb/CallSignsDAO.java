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


    private static Logger LOGGER = Logger.getLogger("CallSignsDAO");
    public void addData1() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        LOGGER.info("tworzy się nowy obiekt client");
        CallSignsDB ham = new CallSignsDB();
        ham.setCall_sign("EA2DT");
        ham.setOperator_name("Manuel");
        ham.setBand("2m");
        ham.setRaport_received("59");
        ham.setRaport_send("59");
        ham.setContact_date("2019-10-10");
        ham.setMail("onet@interia.pl");
        ham.setPassword("none");

        LOGGER.info("rozpoczęto transakcję");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(ham);
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }

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


    // @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<CallSignsDB> getCallSigns() {
        LOGGER.info("rozpoczęto transakcję");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM CallSignsDB c");
        List<CallSignsDB> callSignsList = query.getResultList();
        return callSignsList;
    }

}
