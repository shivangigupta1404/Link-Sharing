package dao;

import dto.UserDto;
import dto.UserDtoMapper;
import entity.Subscription;
import entity.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SubscriptionDao {
    private Session session;
    private Transaction transaction;
    private SessionFactory factory;

    @Autowired
    JdbcTemplate jdbcTemplate;
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

    @SuppressWarnings("unchecked")
    public List<UserDto> subscribersOfTopic(Topic topic){
        String sql = "select *,(select count(*) from subscription S where S.user_id=user.id) as subscriptionCount,"+
                "(select count(*) from Topic T where T.createdBy_id=user.id) as topicCount from user "+
                "WHERE user.id IN (select S.user_id from subscription S where S.topic_id="+topic.getId()+")";

        try {
            List<UserDto> users=jdbcTemplate.query(sql,new UserDtoMapper());
            return users;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
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







