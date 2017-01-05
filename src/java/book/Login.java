/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import java.io.*;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shivani Krishnan
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String name;
        
        
           name=request.getParameter("uname");
          // yos=request.getParameter("n2");
            //y=Integer.parseInt(yos);
        try
        { Class.forName("com.mysql.jdbc.Driver");
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","shivani");
            PreparedStatement pst = con.prepareStatement("select * from studenttable where name = ? ");
            pst.setString(1,name);
ResultSet rs = pst.executeQuery();
if(rs.next())
{   out.println("<table border=20>");

    out.println("<tr>");
    out.println("<td>Name:"+rs.getString(1)+"</td></tr>");
    out.println("<tr>");
    out.println(" <td>The year of study is:"+rs.getInt(2)+"</td></tr>");
     out.println("<tr>");
    out.println("<td> the field of study is:"+rs.getString(3)+"</td></tr>");
    response.sendRedirect("details.html");
}
else
{
    
    out.println("Let's create an account first!");
    response.sendRedirect("signup.html");

        }
        }
        catch(Exception e)
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
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException
{
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