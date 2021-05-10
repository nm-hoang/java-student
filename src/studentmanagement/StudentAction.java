/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
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
public class StudentAction {
    
    
    private static List<Student> students = new ArrayList<>();
    
    public static List<Student> InitialListStudent(){
         Student st1 = new Student("1", "Hoang", 5, "file.jpg", "Dalat1", "ghi chu");
         Student st2 = new Student("2", "Minh", 6, "file.jpg", "Dalat2", "ghi chu");
         Student st3 = new Student("10", "Minh", 9, "file.jpg", "Dalat3", "ghi chu");
         Student st4 = new Student("4", "Minh", 8, "file.jpg", "Dalat4", "ghi chu");
        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        
        StudentDB db = new StudentDB();
        try {
            db.ReadDB(students);
        } catch (SQLException ex) {
            Logger.getLogger(StudentAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return students;
    }
    public StudentAction(){
    }
    public  List<Student> AddStudent(Student student){
        students.add(student);
        StudentDB db = new StudentDB();
        db.AddStudent(student);

        return students;
    }
    
    public List<Student> UpdateStudent(String ID, String Name, float Score, String Image, String Location, String Note){
        students.get(GetIndexById(ID)).updateStudent(Name, Score, Image, Location, Note);
        StudentDB db = new StudentDB();
        Student student = new Student(ID, Name, Score, Image, Location, Note);
        db.UpdateStudent(student);
        return students;
    }
    
    public List<Student> DeleteStudent(String ID){
        students.remove(GetIndexById(ID));
        StudentDB db = new StudentDB();
        db.Delete(ID);
        return students;
    }
    
    public List<Student> IncreaseID(){
        for (int i = 0; i < students.size(); i++) {
        for (int j = 0; j < students.size() - 1 - i; j++) { // refer to note below
            if(Float.parseFloat(students.get(j).getID()) > Float.parseFloat(students.get(j + 1).getID()) ){
                Student temp = new Student();
                temp.setStudent(students.get(j).getStudent());
                students.get(j).setStudent(students.get(j+1).getStudent()); 
                students.get(j+1).setStudent(temp.getStudent());
                
            }
        }
    }
        return students;
    }
     
    public List<Student> DecreaseID(){
       for (int i = 0; i < students.size(); i++) {
        for (int j = 0; j < students.size() - 1 - i; j++) { // refer to note below
            if(Float.parseFloat(students.get(j).getID()) < Float.parseFloat(students.get(j + 1).getID()) ){
                Student temp = new Student();
                temp.setStudent(students.get(j).getStudent());
                students.get(j).setStudent(students.get(j+1).getStudent()); 
                students.get(j+1).setStudent(temp.getStudent());
                
            }
        }
    }
        return students;
        
    }
    
    public List<Student> IncreaseScore(){
        for (int i = 0; i < students.size(); i++) {
        for (int j = i+1; j < students.size(); j++) { // refer to note below
            if(students.get(j).getScore() > students.get(j + 1).getScore() ){
                Student temp = new Student();
                temp.setStudent(students.get(i).getStudent());
                students.get(i).setStudent(students.get(j).getStudent()); 
                students.get(j).setStudent(temp.getStudent());
                
            }
        }
    }
        return students;
    }
    
    public List<Student> DecreaseScore(){
        for (int i = 0; i < students.size(); i++) {
        for (int j = 0; j < students.size() - 1 - i; j++) { // refer to note below
            if(students.get(j).getScore() < students.get(j + 1).getScore() ){
                Student temp = new Student();
                temp.setStudent(students.get(j).getStudent());
                students.get(j).setStudent(students.get(j+1).getStudent()); 
                students.get(j+1).setStudent(temp.getStudent());
                
            }
        }
    }
        return students;
    }
    
    public int GetIndexById(String id) {
		for(int i = 0; i < students.size(); i++) {
			if(students.get(i).getID().equals(id)) {
				return i;
			}
		}
	
	  return -1;
    }
    
    public void ExportCSV(List<Student> students, String fileName){
         try (PrintWriter writer = new PrintWriter(new File(fileName))) {
	      StringBuilder sb = new StringBuilder();
	     for(Student st : students) {
	    	 sb.append(st.getID()+","+st.getName()+","+st.getScore()+","+st.getImage()+","+st.getLocation()+","+st.getNote()+"\n");
	     }

	      writer.write(sb.toString());
	      System.out.println("Export done!");
	    } catch (FileNotFoundException e) {
	      System.out.println(e.getMessage());
	    }
    }
    
    public List<Student> ImportCSV(String fileName){
        List<Student> listStudent = new ArrayList<>();
       try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Student st = new Student(values[0],values[1], Float.parseFloat(values[2]), values[3], values[4], values[5]);
                listStudent.add(st);
    //		        records.add(Arrays.asList(values));
            }
            System.out.println("Import done!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentAction.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return listStudent;
    }
    
    public  void ConsoleListStudent(List<Student> students){
      for(Student student : students)  {
          System.out.println(student.getID());
          System.out.println(student.getName());
          System.out.println(student.getLocation());
      }
    }     
    public static List<Student> GetAll() throws SQLException {
        List<Student> Students = new ArrayList<>();
        
        java.sql.Connection connection = null;
        Statement statement = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Students;user=sa;password=Songdu1999#");
            //Query
            String sql = "Select * From Student";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {              
                Student st = new Student(
                        resultSet.getString("ID"), 
                        resultSet.getString("Name"), 
                        resultSet.getFloat("Score"), 
                        resultSet.getString("Image"), 
                        resultSet.getString("Location"), 
                        resultSet.getString("Note"));
                Students.add(st);
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            if (connection != null) {
                connection.close();
            }
        }
        return Students;
    }
}
