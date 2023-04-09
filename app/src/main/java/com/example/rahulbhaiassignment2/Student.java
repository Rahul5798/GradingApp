package com.example.rahulbhaiassignment2;

//Class created for entering records in the database School.db
public class Student {
    int studentID;
    int improvementID;
    String studentName;
    String Program;
    int course1Marks;
    int course2Marks;
    int course3Marks;
    int course4Marks;
    int totalMarks;

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setImprovementID(int improvementID) {
        this.improvementID = improvementID;
    }

    public int getImprovementID() {
        return improvementID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setProgram(String program) {
        Program = program;
    }

    public String getProgram() {
        return Program;
    }

    public void setCourse1Marks(int course1Marks) {
        this.course1Marks = course1Marks;
    }

    public int getCourse1Marks() {
        return course1Marks;
    }

    public void setCourse2Marks(int course2Marks) {
        this.course2Marks = course2Marks;
    }

    public int getCourse2Marks() {
        return course2Marks;
    }

    public void setCourse3Marks(int course3Marks) {
        this.course3Marks = course3Marks;
    }

    public int getCourse3Marks() {
        return course3Marks;
    }

    public void setCourse4Marks(int course4Marks) {
        this.course4Marks = course4Marks;
    }

    public int getCourse4Marks() {
        return course4Marks;
    }

    public int getTotalMarks(){
       return course1Marks+course2Marks+course3Marks+course4Marks;
    }

}
