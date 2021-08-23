/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Tharnickanth
 */
public class Discount {
    private String productID;
    private float discount=0;

    public Discount(String productID,float discount) {
        this.productID = productID;
        this.discount=discount;
    }
    public Discount(String productID) {
        this.productID = productID;
    }
    public void pullFromDB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=magicMart;user=sa;password=tharni12";
            Connection con = DriverManager.getConnection(url);
            String query = "select discount from product where proID=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, productID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                discount=Float.parseFloat(rs.getString("discount"));
            }
        } catch (Exception e) {
        }
    }
    
    public void pushToDB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=magicMart;user=sa;password=tharni12";
            Connection con = DriverManager.getConnection(url);          
            String query = "update product set discount=? where proID=?";
            PreparedStatement pst = con.prepareStatement(query);
           
            pst.setString(1, String.valueOf(discount));
            pst.setString(2, productID);
            
            pst.executeUpdate();
        } catch (Exception e) {
        }
    } 

    public float getDiscount() {
        return discount;
    }
    
}
