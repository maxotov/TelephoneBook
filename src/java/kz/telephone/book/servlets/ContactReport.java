package kz.telephone.book.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.telephone.book.dao.ContactDAO;
import kz.telephone.book.entity.Contact;

public class ContactReport extends HttpServlet {

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
        ContactDAO dao = new ContactDAO();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        List<Contact> contactInfo = dao.getInfoByStreet(Integer.parseInt(id));
        JsonElement contactObj = gson.toJsonTree(contactInfo);
        JsonElement contactCount = gson.toJsonTree(contactInfo.size());        
        if(contactInfo.isEmpty()){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("contactInfo", contactObj);
        myObj.add("count", contactCount);
        out.println(myObj.toString());
 
        out.close(); 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
