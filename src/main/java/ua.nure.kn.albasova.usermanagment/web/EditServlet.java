package ua.nure.kn.albasova.usermanagment.web;

import ua.nure.kn.albasova.usermanagment.User;
import ua.nure.kn.albasova.usermanagment.db.DaoFactory;
import ua.nure.kn.albasova.usermanagment.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("okButton") != null){
            doOk(req, resp);
        } else if(req.getParameter("cancelButton") != null){
            doCancel(req, resp);
        } else{
            showPage(req, resp);
        }
    }

    private void showPage(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
    }

    private void doCancel(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
    }

    private void doOk(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        User user = getUser(req);
        try {
            processUser(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
            new ServletException();
        }
        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    private User getUser(HttpServletRequest req) {
        User user = new User();
        String idStr = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateStr = req.getParameter("date");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        if(idStr != null){
            user.setId(new Long(idStr));
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try {
            //user.setDateOfBirth(DateFormat.getDateInstance().parse(dateStr));
            user.setDateOfBirth(format.parse(dateStr));
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        return user;
    }

    private void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDAO().update(user);
    }
}
