package servlet;


import dao.DataAccess;
import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Product_obj;



@WebServlet("/servlet.Product")
public class Product extends HttpServlet{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("webservletPU");    
        Connection con;
        PreparedStatement pst;

        
        int row;
        @Override
        public void doPost(HttpServletRequest req,HttpServletResponse rsp)throws IOException,ServletException
        {
            rsp.setContentType("text/html");
            PrintWriter out=rsp.getWriter();
            EntityManager em = emf.createEntityManager();

            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb","root","konkon6789");
                String Barcode=req.getParameter("Barcode");
                String name=req.getParameter("Product Name");
                String color=req.getParameter("Color");
                String Description=req.getParameter("Description");
                em.getTransaction().begin();
                String sqlStr = "SELECT c FROM main.Product_obj c WHERE c.Barcode = "
               +  Barcode; 
               
               Product_obj n=new Product_obj(Integer.parseInt(Barcode),name,color,Description);

         //if(rset.next()) {
         if(em.createQuery(sqlStr,Product_obj.class).getResultList().listIterator().hasNext()){
            out.println("<font color='green'>Record Already exists</font>");
            em.close();
         }
         else{
                em.persist(n);
                em.getTransaction().commit();
                em.close();
                out.println("<font color='green'>Record Added</font>");
            }
           }
            catch (ClassNotFoundException | SQLException ex) {
                                out.println("<font color='green'>error accured</font>");
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
