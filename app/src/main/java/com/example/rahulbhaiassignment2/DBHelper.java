package com.example.rahulbhaiassignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //Database name and version
    static final String DBNAME = "School.db";
    static final int VERSION = 1;

    //Declaring and Initializing the database schema
    //Table 1
    static final String TABLE1_NAME = "Grades";
    static final String T1_COL1 = "StudentId";
    static final String T1_COL2 = "StudentName";
    static final String T1_COL3 = "Program";
    static final String T1_COL4 = "Course1";
    static final String T1_COL5 = "Course2";
    static final String T1_COL6 = "Course3";
    static final String T1_COL7 = "Course4";
    static final String CREATE_TABLE1 = "CREATE TABLE " + TABLE1_NAME + "("
            + T1_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + T1_COL2 + " TEXT NOT NULL,"
            + T1_COL3 + " TEXT NOT NULL,"
            + T1_COL4 + " INTEGER NOT NULL,"
            + T1_COL5 + " INTEGER NOT NULL,"
            + T1_COL6 + " INTEGER NOT NULL,"
            + T1_COL7 + " INTEGER NOT NULL);";
    static final String DROP_TABLE1 = "DROP TABLE IF EXISTS " + TABLE1_NAME;

    //Table 2
    static final String TABLE2_NAME = "Improvement";
    static final String T2_COL1 = "ImprovementId";
    static final String T2_COL2 = "StudentId";
    static final String T2_COL3 = "Course";
    static final String T2_COL4 = "Marks";
    static final String CREATE_TABLE2 = "create table  " + TABLE2_NAME + "("
            + T2_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + T2_COL2 + " INTEGER NOT NULL,"
            + T2_COL3 + " TEXT NOT NULL,"
            + T2_COL4 + " INTEGER NOT NULL);";
    static final String DROP_TABLE2 = "DROP TABLE IF EXISTS " + TABLE2_NAME;


    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating the database table
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE1);
        db.execSQL(DROP_TABLE2);
        onCreate(db);
    }

    //method for inserting data into grades table
    public boolean InsertGrades(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(T1_COL2, student.getStudentName());
        cv.put(T1_COL3, student.getProgram());
        cv.put(T1_COL4, student.getCourse1Marks());
        cv.put(T1_COL5, student.getCourse2Marks());
        cv.put(T1_COL6, student.getCourse3Marks());
        cv.put(T1_COL7, student.getCourse4Marks());

        long result = db.insert(TABLE1_NAME, null, cv);

        return ((result == -1) ? false : true);
    }

    //method for listing student details
    public Cursor GetStudentList() {
        Cursor cursor;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + TABLE1_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }


        return cursor;
    }

    //method for getting particular student's grades
    public Cursor GetStudentGrades(int id) {
        Student student = new Student();
        student.setStudentID(id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE1_NAME + " WHERE " + T1_COL1 + " = '" + id + "'", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Method for updating grades table after improvement in particular course
    public boolean UpdateStudent(Student student, String selectedCourse, int newMarks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(selectedCourse, newMarks);
        long result = db.update(TABLE1_NAME,cv,"StudentId = " +student.getStudentID(),null);
        return ((result == -1) ? false : true);
    }

    //Method for inserting data into improvement table
    public Boolean InsertImprovement(Student student, String selectedCourse, int improvement) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(T2_COL2, student.getStudentID());
        cv.put(T2_COL3, selectedCourse);
        cv.put(T2_COL4, improvement);


        long result = db.insert(TABLE2_NAME, null, cv);

        return ((result == -1) ? false : true);
    }
}
