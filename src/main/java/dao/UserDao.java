package dao;

import com.sun.org.apache.regexp.internal.RE;
import dto.UserDto;
import dto.UserDtoMapper;
import entity.Resource;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class UserDao {
    private Session session;
    private Transaction transaction;
    private SessionFactory factory;

    @Autowired
    JdbcTemplate jdbcTemplate;

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

    public User findById(int id){
        openCurrentSessionwithTransaction();
        User user=getSession().get(User.class,id);
        closeCurrentSessionwithTransaction();
        return user;
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

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        openCurrentSessionwithTransaction();
        List<User> users = (List<User>)getSession().createQuery("from User").list();
        closeCurrentSessionwithTransaction();
        return users;
    }

    @SuppressWarnings("unchecked")
    public List<User> findLimited(int skip,int numOfRecords,String type){
        openCurrentSessionwithTransaction();
        Query query;
        if(type.equals("active")) {
            query = session.createQuery("FROM User where active=true ");
        }
        else if(type.equals("inactive")){
            query = session.createQuery("FROM User where active=false ");
        }
        else{
            query = session.createQuery("FROM User");
        }
        query.setFirstResult(skip);
        query.setMaxResults(numOfRecords);
        List<User> users = (List<User>) query.list();
        closeCurrentSessionwithTransaction();
        return users;
    }

    public UserDto getUserDetails(int id){
        String sql = "select *,(select count(*) from subscription S where S.user_id=U.id) as subscriptionCount,"+
                     "(select count(*) from topic T where T.createdBy_id=U.id) as topicCount from user U "+
                     "where U.id="+id;

        try {
            List<UserDto> user=(List<UserDto>)jdbcTemplate.query(sql,new UserDtoMapper());
            return user.get(0);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public int getCount(){
        openCurrentSessionwithTransaction();
        Query query=getSession().createQuery("select count(*) from User");
        long count= (long) query.uniqueResult();
        closeCurrentSessionwithTransaction();
        return (int)count;
    }

    public int getParticularUserCount(String type) {
        openCurrentSessionwithTransaction();
        Query query = null;
        if(type.equals("active")){
            query = getSession().createQuery("select count(*) from User where active=true");
        }
        else if(type.equals("inactive")){
            query = getSession().createQuery("select count(*) from User where active=false");
        }
        else {
            query = getSession().createQuery("select count(*) from User");
        }
        long count= (long) query.uniqueResult();
        closeCurrentSessionwithTransaction();
        return (int)count;
    }

    @SuppressWarnings("unchecked")
    public Boolean checkUsernameAvailability(String username){
        openCurrentSessionwithTransaction();
        Query query=getSession().createQuery("select count(*) from User U where U.username=?");
        query.setParameter(0,username);
        boolean exists = (Long) query.uniqueResult() > 0;
        closeCurrentSessionwithTransaction();
        return exists;
    }

    @SuppressWarnings("unchecked")
    public Boolean checkEmailAvailability(String email){
        openCurrentSessionwithTransaction();
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

    public void update(User entity) {
        openCurrentSessionwithTransaction();
        getSession().update(entity);
        closeCurrentSessionwithTransaction();
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

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}



