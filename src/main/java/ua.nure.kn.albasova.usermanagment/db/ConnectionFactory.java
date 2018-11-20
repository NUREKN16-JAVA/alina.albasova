package ua.nure.kn.albasova.usermanagment.db;

import java.sql.Connection;

public interface ConnectionFactory {
    /**
     *
     * @return Connection to database
     * @throws DatabaseException
     */
    Connection createConnection() throws DatabaseException;
}
