package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDao {
    private Session session;
    private Transaction transaction;
    private SessionFactory factory;

    public UserDao(){
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

    //CRUD

    public void save(User entity) {
        openCurrentSessionwithTransaction();
        getSession().persist(entity);
        closeCurrentSessionwithTransaction();
    }

    @SuppressWarnings("unchecked")
    public Boolean checkUsernameAvailability(String username){
        openCurrentSessionwithTransaction();
        String hql="select count(*) from User U where U.username=:usernameParam";
        Query query=getSession().createQuery(hql);
        query.setParameter("usernameParam",username);
        boolean exists = (Long) query.uniqueResult() > 0;
        closeCurrentSessionwithTransaction();
        return exists;
    }

    @SuppressWarnings("unchecked")
    public Boolean checkEmailAvailability(String email){
        openCurrentSessionwithTransaction();
        /*String hql="select count(*) from User U where U.email=:emailParam";
        Query query=getSession().createQuery(hql);*/
        Query q = getSession().createQuery("SELECT COUNT(*) FROM User WHERE email = ?");
        q.setParameter(0,email);
        long count= (long) q.uniqueResult();
        closeCurrentSessionwithTransaction();
        System.out.println(count);
        if(count==1)
            return true;
        else
            return false;


    }

    @SuppressWarnings("unchecked")
    public User findByUsernamePassword(String username,String password) {
        openCurrentSessionwithTransaction();
        String hql = "FROM User U WHERE (U.username=:usernameParam or U.email=:usernameParam) and U.password=:passwordParam";
        Query query = getSession().createQuery(hql);
        query.setParameter("usernameParam", username);
        query.setParameter("passwordParam", password);
        List<User> result = (List<User>) query.list();
        closeCurrentSessionwithTransaction();
        if(result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public User findByUsername(String username) {
        openCurrentSessionwithTransaction();
        String hql = "FROM User U WHERE U.username=:usernameParam";
        Query query = getSession().createQuery(hql);
        query.setParameter("usernameParam", username);
        List<User> result = (List<User>) query.list();
        closeCurrentSessionwithTransaction();
        if(result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
    }
    public void update(User entity) {
        openCurrentSessionwithTransaction();
        getSession().update(entity);
        closeCurrentSessionwithTransaction();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        openCurrentSessionwithTransaction();
        List<User> users = (List<User>)getSession().createQuery("from User").list();
        closeCurrentSessionwithTransaction();
        return users;
    }

    
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



