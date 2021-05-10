/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
public class StudentDB {
    private static String dbName = "StudentDB";
    private static String table = "Student";
    private static String user =  "sa";
    private static String password = "1234";
    private static String path = "jdbc:sqlserver://localhost:1433;databaseName=" +dbName+ ";user="+user+";password="+password;
    public void ReadDB(List<Student> students) throws SQLException{
        java.sql.Connection connection = null;
        Statement statement = null;
        
       try {

            connection = DriverManager.getConnection(path);

            
            //Query
            String sql = "Select *From Student";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("run here");
            while (resultSet.next()) {              
               Student student = new Student(
                        resultSet.getString("ID"),
                        resultSet.getString("Name"),
                        resultSet.getFloat("Score"),
                        resultSet.getString("Image"),
                        resultSet.getString("Location"),
                        resultSet.getString("Note"));
                students.add(student);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
        }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public void AddStudent(Student student){
        java.sql.Connection connection = null;
        PreparedStatement statement = null;
       
        try {
            connection = DriverManager.getConnection(path);
             //Query
            String sql = "Insert into Student(ID, Name, Scores, Picture, Location, Note) values(?, ?, ?, ?, ?, ?)";
            statement = connection.prepareCall(sql);
            statement.setString(1, student.getID());
            statement.setString(2, student.getName());
            statement.setFloat(3, student.getScore());
            statement.setString(4, student.getImage());
            statement.setString(5, student.getLocation());
            statement.setString(6, student.getNote());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
           
      
    }
    public void UpdateStudent(Student student){
        java.sql.Connection connection = null;
        PreparedStatement statement = null;
       
        try {
            connection = DriverManager.getConnection(path);
             //Query
            String sql = "Update Student set Name = ?, Score = ?, Image = ?, Location = ?, Note = ? where ID = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, student.getID());
            statement.setString(2, student.getName());
            statement.setFloat(3, student.getScore());
            statement.setString(4, student.getImage());
            statement.setString(5, student.getLocation());
            statement.setString(6, student.getNote());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Delete(String ID){
          java.sql.Connection connection = null;
        PreparedStatement statement = null;
       
        try {
            connection = DriverManager.getConnection(path);
             //Query
            String sql = "DELETE  FROM Student WHERE ID = ?";
            statement = connection.prepareCall(sql);
            
            statement.setString(1, ID);
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("hello");
      
    }
}
