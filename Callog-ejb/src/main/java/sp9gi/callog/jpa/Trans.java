package sp9gi.callog.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Trans {

    public void addData1() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");

        Client client = new Client();
        //  client.setId(2);
        client.setName("sp9gi");

        Bank bank = new Bank();
        bank.setName("QRZ");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.persist(bank);
        entityManager.getTransaction().commit();

        entityManagerFactory.close();
    }
}
