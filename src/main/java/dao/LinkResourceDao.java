package dao;

import entity.LinkResource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class LinkResourceDao {
    private Session session;
    private Transaction transaction;
    private SessionFactory factory;

    public LinkResourceDao() {
        Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
        factory=cfg.buildSessionFactory();
    }

    public LinkResourceDao(Session session, Transaction transaction, SessionFactory factory) {
        this.session = session;
        this.transaction = transaction;
        this.factory = factory;
    }

    public void openCurrentSessionwithTransaction() {
        session=factory.openSession();
        transaction=session.beginTransaction();
    }
    public void closeCurrentSessionwithTransaction() {
        transaction.commit();
        session.close();
    }

    public void save(LinkResource linkResource){
        openCurrentSessionwithTransaction();
        getSession().save(linkResource);
        closeCurrentSessionwithTransaction();
    }

    public void update(LinkResource linkResource){
        openCurrentSessionwithTransaction();
        getSession().update(linkResource);
        closeCurrentSessionwithTransaction();
    }

    public void delete(LinkResource linkResource){
        openCurrentSessionwithTransaction();
        getSession().delete(linkResource);
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
