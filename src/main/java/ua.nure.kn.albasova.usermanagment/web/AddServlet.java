package ua.nure.kn.albasova.usermanagment.web;


import ua.nure.kn.albasova.usermanagment.User;
import ua.nure.kn.albasova.usermanagment.db.DaoFactory;
import ua.nure.kn.albasova.usermanagment.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends EditServlet {
    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDAO().create(user);
    }
}
