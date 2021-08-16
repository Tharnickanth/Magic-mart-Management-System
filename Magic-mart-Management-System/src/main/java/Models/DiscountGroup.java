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
public class DiscountGroup {
    
    private ArrayList<String> group;
    private String value;
    private int index;
    private String groupID;
    private String ProjectIDs="";
    
    public DiscountGroup(ArrayList group, String value) {
        this.group = group;
        this.value = value;
    }
      public DiscountGroup(ArrayList group, String value,String groupID) {
        this.group = group;
        this.groupID = groupID;
    }

    public void addToGroup(){
        group.add(value);
    }
    public boolean isAcceptable(){
        for(int x=0;x<group.size();x++){
            if(group.get(x).equals(value)){
                index=x;
                return false;
            }
        }
        return true;
    }
    public void removeToGroup(){
        if(!isAcceptable()){
            group.remove(index);
        }
    }

    public ArrayList<String> getGroup() {
        return group;
    }

    public String getProjectIDs() {
        for(int y=0;y<group.size();y++){
            ProjectIDs += group.get(y)+"\n";
        }
        return ProjectIDs;
    }
    
    public void pushToDB(){
                try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=magicMart;user=sa;password=tharni12";
            Connection con = DriverManager.getConnection(url);          
            String query = "insert into discountGroup(GrpID,proIDs) values(?,?)";
            PreparedStatement pst = con.prepareStatement(query);  
            pst.setString(1, groupID);
            pst.setString(2, getProjectIDs());
            pst.executeUpdate();
        } catch (Exception e) {
        }
    } 
    public void pullFromDB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=magicMart;user=sa;password=tharni12";
            Connection con = DriverManager.getConnection(url);
            String query = "select proIDs from discountGroup where GrpID=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, groupID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                ProjectIDs=rs.getString("proIDs");
            }
        } catch (Exception e) {
        }
    }
    public void deleteFromDB(){
                try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=magicMart;user=sa;password=tharni12";
            Connection con = DriverManager.getConnection(url);          
            String query = "delete discountGroup where GrpID=?";
            PreparedStatement pst = con.prepareStatement(query);  
            pst.setString(1, groupID);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public String getPIDs() {
         
        return ProjectIDs;
    }
    
}
    
    
    
    
