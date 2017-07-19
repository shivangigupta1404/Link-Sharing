package dao;

import entity.Subscription;
import entity.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class SubscriptionDao {
    private Session session;
    private Transaction transaction;
    private SessionFactory factory;

    public SubscriptionDao(){
        Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
        factory=cfg.buildSessionFactory();
    }


    public void openCurrentSessionwithTransaction() {
        session=factory.openSession();
        transaction=session.beginTransaction();
    }
    public void closeCurrentSessionwithTransaction() {
        transaction.commit();
        session.close();
    }

    public void save(Subscription entity) {
        openCurrentSessionwithTransaction();
        getSession().persist(entity);
        closeCurrentSessionwithTransaction();
    }

    public void update(Subscription entity) {
        openCurrentSessionwithTransaction();
        getSession().update(entity);
        closeCurrentSessionwithTransaction();
    }

    public void delete(Subscription entity) {
        openCurrentSessionwithTransaction();
        getSession().delete(entity);
        closeCurrentSessionwithTransaction();
    }


    //GETTER AND SETTER
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}







