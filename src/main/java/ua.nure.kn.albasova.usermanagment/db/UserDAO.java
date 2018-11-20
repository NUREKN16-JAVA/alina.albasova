package ua.nure.kn.albasova.usermanagment.db;

import ua.nure.kn.albasova.usermanagment.User;

import java.util.Collection;
import java.util.List;

public interface UserDAO {
    /**
     * Add user to DB table USER
     * @param user with null id field
     * @return user modified record exemplar with DB auto-generated id field
     */
    public User create(final User user) throws DatabaseException;

    /**
     *
     * @param id
     * @return
     */
    public User find(final Long id);

    /**
     *
     * @return
     */
    public Collection<User> findAll() throws DatabaseException;

    /**
     *
     * @param user
     */
    public void update(final User user);

    /**
     *
     * @param user
     */
    public void delete(final User user);

    void setConnectionFactory(ConnectionFactory connectionFactory);
}
