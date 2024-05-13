package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    public static final String FIND_BY_MODEL_AND_SERIES = "FROM User u  WHERE u.car.model = :model AND u.car.series = :series";
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public Optional<User> findByModelAndSeries(String model, int series) {
        return sessionFactory.getCurrentSession()
                .createQuery(FIND_BY_MODEL_AND_SERIES, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findAny();
    }

}
