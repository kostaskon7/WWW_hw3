package servlet;


import dao.DataAccess;
import java.io.IOException;
import java.sql.Statement;
import java.io.PrintWriter;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Product_obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kostas
 */
@WebServlet("/viewproduct")
public class viewproduct extends HttpServlet{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("webservletPU");    

    Connection con;
        PreparedStatement pst;
        
        int row;
        @Override
        public void doGet(HttpServletRequest req,HttpServletResponse rsp)throws IOException,ServletException
        {
            rsp.setContentType("text/html");
            PrintWriter out=rsp.getWriter();
            EntityManager em = emf.createEntityManager();


            out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr>");
            out.println("<td> Barcode </td>");
            out.println("<td> Name </td>");
            out.println("<td> Color </td>");
            out.println("<td> Description </td>");
            out.println("</ tr>");

            em.getTransaction().begin();
            String sqlStr = "SELECT c FROM main.Product_obj c";
            List<Product_obj> all =em.createQuery(sqlStr,Product_obj.class).setMaxResults(Integer.MAX_VALUE).getResultList();
            ListIterator<Product_obj> it=all.listIterator();
            for(int i=0;i<all.size();i++){
                Product_obj rs=it.next();
                out.println("<tr>");
                out.println("<td>" +rs.Barcode+ "</td>");
                out.println("<td>" +rs.pname+ "</td>");
                out.println("<td>" +rs.Color+ "</td>");
                out.println("<td>" +rs.Description+ "</td>");
                
                
                out.println("</ tr>");

            }
            out.println("</table>");
            em.close();
        }
}
