package dao;

import entity.DocumentResource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentResourceDao {
    private Session session;
    private Transaction transaction;
    private SessionFactory factory;

    public DocumentResourceDao() {
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

    public void save(DocumentResource documentResource){
        openCurrentSessionwithTransaction();
        getSession().save(documentResource);
        closeCurrentSessionwithTransaction();
    }

    public void update(DocumentResource documentResource){
        openCurrentSessionwithTransaction();
        getSession().update(documentResource);
        closeCurrentSessionwithTransaction();
    }

    public void delete(DocumentResource documentResource){
        openCurrentSessionwithTransaction();
        getSession().delete(documentResource);
        closeCurrentSessionwithTransaction();
    }
    //Getter and setter

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
