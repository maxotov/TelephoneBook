package kz.telephone.book.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.telephone.book.dao.ContactDAO;
import kz.telephone.book.entity.Contact;
import kz.telephone.book.entity.Street;

public class ShowContact extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        ContactDAO dao = new ContactDAO();
        Contact c = dao.getContactById(Integer.parseInt(id));
        List<Street> streets = dao.findAll();
        request.setAttribute("streets", streets);
        request.setAttribute("c", c);
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/edit.jsp");
        dis.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
