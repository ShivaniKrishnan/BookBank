/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Shivani Krishnan
 */
@WebServlet(urlPatterns = {"/details"})
public class details extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String author,bookname,edition,publisher;
        int d;
        author=request.getParameter("d1");
        bookname=request.getParameter("d2");
        edition=request.getParameter("d3");
        publisher=request.getParameter("d4");
        d=Integer.parseInt(edition);
        /* TODO output your page here. You may use following sample code. */
        try
        {
                  
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","shivani");
        PreparedStatement pst = con.prepareStatement("select * from booktable2 where author=?");
        pst.setString(1,author);
        ResultSet rs=pst.executeQuery();
        if(rs.next())
        {
            out.println(" The required book is available.");
             response.sendRedirect("payment.html");
        }
        else
        {
            out.println("Sorry, the required book is temporarily unavailable!");
        }
        Cookie c=new Cookie("author",author);
        response.addCookie(c);
        out.println(" the cookie is added");
       
        
        
        
            
            
            
        }catch(Exception e)
        {
            out.println(e.getMessage());
        }
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

