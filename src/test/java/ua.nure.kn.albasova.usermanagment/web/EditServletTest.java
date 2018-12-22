package ua.nure.kn.albasova.usermanagment.web;

import ua.nure.kn.albasova.usermanagment.User;

import java.text.DateFormat;
import java.util.Date;

public class EditServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    public void testEdit(){
        Date date = new Date();
        User user = new User(new Long(1000), "John", "Doe", date);
        getMockUserDao().expect("update", user);

        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", DateFormat.getInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();

    }
}
