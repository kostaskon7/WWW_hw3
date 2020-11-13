/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import db.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Product_obj;

/**
 *
 * @author Thang
 */
public class DataAccess {
    public void addNew(Product_obj n){
        try {
            PreparedStatement ps = DBUtils.getPreparedStatement("insert into data values(?,?,?,?)");
            ps.setInt(1, n.Barcode);
            ps.setString(2, n.pname);
            ps.setString(3, n.Color);
            ps.setString(4, n.Description);

            ps.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Product_obj> getAll(){
        List<Product_obj> ls = new LinkedList<>();
        
        try {
            ResultSet rs = DBUtils.getPreparedStatement("select * from data").executeQuery();
            while(rs.next()){
                Product_obj n = new Product_obj(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                ls.add(n);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return ls;
    }
    
    public boolean getNewById(int id){
        List<Product_obj> ls = new LinkedList<>();
        String sql = "select * from data where Barcode = " +id;
        try {
            ResultSet rs = DBUtils.getPreparedStatement(sql).executeQuery();
            if(rs.next()){
                return(true);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return (false);
    }

}
