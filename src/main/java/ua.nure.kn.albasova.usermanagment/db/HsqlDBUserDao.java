package ua.nure.kn.albasova.usermanagment.db;

import ua.nure.kn.albasova.usermanagment.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

class HsqlDBUserDao implements UserDAO{
    public static final String FIND_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
    private static final String INSERT_QUERY = "INSERT INTO users (firstname,lastname,dateofbirth) VALUES (?,?,?)";
    private ConnectionFactory connectionFactory;

    public HsqlDBUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public HsqlDBUserDao() {
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(INSERT_QUERY);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getFullName());
            statement.setDate(3,new Date(user.getDateOfBirth().getTime()));
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of inserted raws: " + number);
            }
            CallableStatement callableStatement = connection
                    .prepareCall("call IDENTITY ()");
            ResultSet keys = callableStatement.executeQuery();
            if(keys.next()){
                user.setId(new Long(keys.getLong(1)));
            }
            keys.close();
            callableStatement.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User find(Long id) {

        return null;
    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        Collection<User> result = new LinkedList<>();

        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()){
                User user = new User();
                user.setId(new Long(resultSet.getLong(1)));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
