package kz.telephone.book.servlets;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.telephone.book.dao.ContactDAO;

public class ContactDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        int num = Integer.parseInt(id);
 
        PrintWriter out = response.getWriter();
        JsonObject myObj = new JsonObject();
        ContactDAO dao = new ContactDAO();
        if(dao.deleteContact(num)){
          myObj.addProperty("success", true);
        }
        else {
            myObj.addProperty("success", false);
        }
        out.println(myObj.toString());
        out.close();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
