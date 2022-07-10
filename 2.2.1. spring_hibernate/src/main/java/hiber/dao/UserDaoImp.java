package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getUser(String model, int series) {
      User user = sessionFactory.getCurrentSession().createQuery(
              "SELECT User AS u from User user WHERE user.car.series = :s AND user.car.model = :m", User.class)
              .setParameter("s", series)
              .setParameter("m", model)
              .getSingleResult();
      System.out.println(user);
      return user;
   }

}
