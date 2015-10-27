package kz.telephone.book.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.telephone.book.dao.ContactDAO;
import kz.telephone.book.entity.Street;

public class ContactAddPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page");
        ContactDAO dao = new ContactDAO();
        List<Street> streets = dao.findAll();
       request.setAttribute("streets", streets);
        if(page.equals("add")){
            RequestDispatcher dis = getServletContext().getRequestDispatcher("/add.jsp");
            dis.forward(request, response);
        } else if(page.equals("report")){
             RequestDispatcher dis = getServletContext().getRequestDispatcher("/report.jsp");
            dis.forward(request, response);
        }
       
        
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
}
