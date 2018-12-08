package ua.nure.kn.albasova.usermanagment.db;

import com.mockobjects.dynamic.Mock;
import ua.nure.kn.albasova.usermanagment.User;

public class MockDaoFactory extends DaoFactory {

    private Mock mockUserDao;

    public MockDaoFactory() {
        mockUserDao = new Mock(UserDAO.class);
    }


    public Mock getMockUserDAO() {
        return mockUserDao;
    }

    @Override
    public UserDAO getUserDAO() {
        return (UserDAO) mockUserDao.proxy();
    }
}
