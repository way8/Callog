package sp9gi.callog.ejb;

import sp9gi.callog.jpa.Bank;
import sp9gi.callog.jpa.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Logger;

public class Trans {
    private static Logger LOGGER = Logger.getLogger("Trans");
    public void addData1() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        LOGGER.info("tworzy się nowy obiekt client");
        Client client = new Client();
        //  client.setId(2);
        client.setName("sp9gi");

        Bank bank = new Bank();
        bank.setName("QRZ");
        LOGGER.info("rozpoczęto transakcję");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.persist(bank);
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }
}
