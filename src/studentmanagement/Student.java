/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

/**
 *
 * @author H
 */
public class Student {
    private String ID;

    Student() {
       
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float Score) {
        this.Score = Score;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    private String Name;
    private float Score;
    private String Image;
    private String Location;
    private String Note;
    
    public Student(String ID, String Name, float Score, String Image, String Location, String Note) {
        this.ID = ID;
        this.Name = Name;
        this.Score = Score;
        this.Image = Image;
        this.Location = Location;
        this.Note = Note;
    }
    public Student getStudent(){
        return this;
    }
    public void setStudent(Student student){
        this.ID = student.ID;
        this.Name = student.Name;
        this.Score = student.Score;
        this.Image = student.Image;
        this.Location = student.Location;
        this.Note = student.Note;
    }
    public void updateStudent(String Name, float Score, String Image, String Location, String Note){
        this.Name = Name;
        this.Score = Score;
        this.Image = Image;
        this.Location = Location;
        this.Note = Note;
    
    }

    
    
    
}
