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
import kz.telephone.book.entity.Street;

public class ContactAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
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
            request.setAttribute("streets", streets);
            request.setAttribute("add", "Ошибка: поля пустой!");
            
        } else if(!matchArr.matches()){
            request.setAttribute("streets", streets);
            request.setAttribute("add", "Ошибка: номер телефона некорректно!");
        } else if(!matchFlat.matches()){
            request.setAttribute("streets", streets);
            request.setAttribute("add", "Ошибка: номер дома некорректно!");
        } else if(!dao.insertContact(firstname, lastname, fathername, phone, Integer.parseInt(street_id), Integer.parseInt(flat))){
            request.setAttribute("streets", streets);
            request.setAttribute("add", "Ошибка: не добавлена!");
        } else {
            request.setAttribute("streets", streets);
            request.setAttribute("add", "Успешно добавлено");
        }
        
        
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/add.jsp");
        dis.forward(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
}
