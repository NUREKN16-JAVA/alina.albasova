package ua.nure.kn.albasova.usermanagment.webapp;

import ua.nure.kn.albasova.usermanagment.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }
    public void testBrowse(){
        User user = new User(new Long(1000), "John", "Doe", new Date());
        List list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", list);
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull("Couldn't find list of users in session", collection);
        assertSame(list, collection);
    }
}
