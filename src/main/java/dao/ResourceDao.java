package dao;

import com.sun.org.apache.regexp.internal.RE;
import entity.Resource;
import entity.Topic;
import entity.User;
import entity.Visibility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceDao {
    private Session session;
    private Transaction transaction;
    private SessionFactory factory;

    public ResourceDao() {
        Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
        factory=cfg.buildSessionFactory();
    }

    public ResourceDao(Session session, Transaction transaction, SessionFactory factory) {
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

    public Resource getByid(int id){
        openCurrentSessionwithTransaction();
        Resource resource=getSession().get(Resource.class,id);
        closeCurrentSessionwithTransaction();
        return resource;
    }

    @SuppressWarnings("unchecked")
    public List<Resource> nrecentPublicShares(int n) {
        openCurrentSessionwithTransaction();
        String hql= "From Resource R WHERE R.topic.visibility=0 ORDER BY R.dateCreated DESC";
        Query query = getSession().createQuery(hql);
        List<Resource> resources = (List<Resource>) query.setMaxResults(n).list();
        closeCurrentSessionwithTransaction();
        return resources;
    }

    @SuppressWarnings("unchecked")
    public List<Resource> ResourcesofUser(User creator) {
        openCurrentSessionwithTransaction();
        Query query = getSession().createQuery("from Resource R WHERE R.createdBy =?");
        query.setParameter(0,creator);
        List<Resource> result = (List<Resource>) query.list();
        closeCurrentSessionwithTransaction();
        if(result.isEmpty()){
            return null;
        } else {
            return result;
        }
    }

    //Getter and Setter
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
