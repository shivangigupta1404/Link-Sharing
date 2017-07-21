package dao;

import dto.TopicDto;
import dto.TopicDtoMapper;
import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class TopicDao {
    private Session session;
    private Transaction transaction;
    private SessionFactory factory;

    @Autowired
    private UserDao userDao;
    @Autowired
    private SubscriptionDao subscriptionDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public TopicDao(){
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

    public void save(Topic topic,User user) {
        openCurrentSessionwithTransaction();
        getSession().persist(topic);
        Subscription subscription=new Subscription(topic,user, Seriousness.VerySerious,new Date());
        subscriptionDao.save(subscription);
        closeCurrentSessionwithTransaction();
    }

    public void update(Topic entity) {
        openCurrentSessionwithTransaction();
        getSession().update(entity);
        closeCurrentSessionwithTransaction();
    }

    public void delete(Topic entity) {
        openCurrentSessionwithTransaction();
        getSession().delete(entity);
        closeCurrentSessionwithTransaction();
    }

    public Topic getById(int id){
        openCurrentSessionwithTransaction();
        Topic topic=getSession().get(Topic.class,id);
        closeCurrentSessionwithTransaction();
        return topic;
    }

    @SuppressWarnings("unchecked")
    public Topic findByUserAndTopicName(User creator,String topicName) {
        openCurrentSessionwithTransaction();
        Query query = getSession().createQuery("from Topic T WHERE name=? and T.createdBy =?");
        query.setParameter(0,topicName);
        query.setParameter(1,creator);
        List<Topic> result = (List<Topic>) query.list();
        closeCurrentSessionwithTransaction();
        if(result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Topic> topicsSubscribedByUsername(String username) {
        openCurrentSessionwithTransaction();
        User user=userDao.findByUsername(username);
        String hql = "select S.topic From Subscription S where S.user=?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0,user);
        List<Topic> topics = query.list();
        closeCurrentSessionwithTransaction();
        return topics;
    }

    @SuppressWarnings("unchecked")
    public int postCountOfTopic(Topic topic){
        openCurrentSessionwithTransaction();
        String hql = "SELECT count(*) FROM Resource R where R.topic=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,topic);
        Long count = (Long)query.uniqueResult();
        closeCurrentSessionwithTransaction();
        return Math.toIntExact(count);
    }

    @SuppressWarnings("unchecked")
    public List<Resource> postsOfTopic(Topic topic){
        openCurrentSessionwithTransaction();
        String hql = "FROM Resource R WHERE R.topic=?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0,topic);
        List<Resource> posts = (List<Resource>)query.list();
        closeCurrentSessionwithTransaction();
        return posts;
    }

    @SuppressWarnings("unchecked")
    public int subscriptionCountOfTopic(Topic topic){
        openCurrentSessionwithTransaction();
        String hql = "SELECT count(*) FROM Subscription S where S.topic=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,topic);
        int count = query.list().size();
        closeCurrentSessionwithTransaction();
        return count;
    }

    @SuppressWarnings("unchecked")
    public List<TopicDto> topicsOfUser(User user) {
        int id=user.getId();
        String sql = "select *,(select count(*) from subscription S where S.topic_id=T.id) as subscriptionCount,"+
                "(select count(*) from resource R where R.topic_id=T.id) as topicCount from topic T "+
                "WHERE T.id=6";

        try {
            List<TopicDto> users=jdbcTemplate.query(sql,new TopicDtoMapper());
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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public SubscriptionDao getSubscriptionDao() {
        return subscriptionDao;
    }

    public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}




