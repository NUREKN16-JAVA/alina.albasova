package ua.nure.kn.albasova.usermanagment.web;


import ua.nure.kn.albasova.usermanagment.db.DaoFactory;
import ua.nure.kn.albasova.usermanagment.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class BrowseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("addButton") != null){
            add(req, resp);
        }else if (req.getParameter("editButton") != null){
            edit(req, resp);
        }else if(req.getParameter("detailsButton") != null){
            details(req, resp);
        }else if (req.getParameter("deleteButton") != null){
            delete(req, resp);
        } else {
            browse(req, resp);
        }

    }

    private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection users;
        try {
            users = DaoFactory.getInstance().getUserDAO().findAll();
            req.getSession().setAttribute("users", users);
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
        } catch (DatabaseException e) {
            throw new ServletException();
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
