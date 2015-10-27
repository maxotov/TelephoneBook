package kz.telephone.book.servlets;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.telephone.book.dao.ContactDAO;
import kz.telephone.book.entity.Contact;
import kz.telephone.book.entity.Street;

public class ContactEdit extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String fathername = request.getParameter("fathername");
        String phone = request.getParameter("phone");
        String street_id = request.getParameter("street_id");
        String flat = request.getParameter("flat");
        ContactDAO dao = new ContactDAO();
        List<Street> streets = dao.findAll();
        Pattern patternNumber = Pattern.compile("87\\d{9}");
        Matcher matchArr = patternNumber.matcher(phone);
        Pattern patternFlat = Pattern.compile("\\d{1,4}");
        Matcher matchFlat = patternFlat.matcher(flat);
        if(lastname.isEmpty() || firstname.isEmpty() || fathername.isEmpty() || phone.isEmpty() || flat.isEmpty()){
            Contact c = dao.getContactById(Integer.parseInt(id));
            request.setAttribute("streets", streets);
            request.setAttribute("c", c);
            request.setAttribute("edit", "Ошибка: поля пустой!");
            
        } else if(!matchArr.matches()){
             Contact c = dao.getContactById(Integer.parseInt(id));
            request.setAttribute("streets", streets);
            request.setAttribute("c", c);
            request.setAttribute("edit", "Ошибка: номер телефона некорректно!");
        } else if(!matchFlat.matches()){
            Contact c = dao.getContactById(Integer.parseInt(id));
            request.setAttribute("streets", streets);
            request.setAttribute("c", c);
            request.setAttribute("edit", "Ошибка: номер дома некорректно!");
        } else if(!dao.updateContact(firstname, lastname, fathername, phone, Integer.parseInt(street_id), Integer.parseInt(flat), Integer.parseInt(id))){
            Contact c = dao.getContactById(Integer.parseInt(id));
            request.setAttribute("streets", streets);
            request.setAttribute("c", c);
            request.setAttribute("edit", "Ошибка: не добавлена!");
        } else {
            Contact c = dao.getContactById(Integer.parseInt(id));
            request.setAttribute("streets", streets);
            request.setAttribute("c", c);
            request.setAttribute("edit", "Успешно изменено!");
        }
        
        
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/edit.jsp");
        dis.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
