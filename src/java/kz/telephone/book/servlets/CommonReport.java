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
import kz.telephone.book.entity.Street;

public class CommonReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ContactDAO dao = new ContactDAO();
        List<Street> streets = dao.findAll();
        for(int i=0; i<streets.size(); i++){
            streets.get(i).setContacts(dao.getInfoByStreet(streets.get(i).getId()));
         }
        
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        JsonElement contactInfo = gson.toJsonTree(streets);        
        
        if(streets.isEmpty()){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
        myObj.add("contactInfo", contactInfo);
        out.println(myObj.toString());
 
        out.close();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
