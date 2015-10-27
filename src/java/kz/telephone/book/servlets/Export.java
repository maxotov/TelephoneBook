package kz.telephone.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Export extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        if(type.equals("word")){
        response.setContentType("application/vnd.ms-word; charset=UTF-8");
        response.setHeader("Content-type", "application/doc");
        response.setHeader("Content-disposition", "inline; filename=report.doc");    
        }
        PrintWriter op = response.getWriter();
        String text = request.getParameter("tableHTML");
        if (text == null) {
            text = "NO DATA";
        }
        op.write(text);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
