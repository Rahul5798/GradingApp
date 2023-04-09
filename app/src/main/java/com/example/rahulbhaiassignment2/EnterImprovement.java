package com.example.rahulbhaiassignment2;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterImprovement extends Fragment implements AdapterView.OnItemSelectedListener {
    View v;
    Spinner spinner;
    //Dropdown list
    String[] courses = {"Course1","Course2","Course3","Course4"};
    EditText studentID;
    EditText improvementMarks;
    Button submit;
    DBHelper dbh;
    Student student;
    int newMarks;
    String selectedCourse; //Course selected from dropdown menu
    Boolean insertStatus; //result of insert query
    Boolean updateStatus; // result of update query
    Boolean createStatus; // checks if student exist or not as well as marks won't be >100 after adding improvement

    public EnterImprovement() {
        // Required empty public constructor
    }
    //function for checking student's marks are not going greater than 100
    public boolean Validate(Student student, String selectedCourse){
        int marks = Integer.parseInt(improvementMarks.getText().toString());
        if(selectedCourse.equals("Course1")){
            newMarks = student.getCourse1Marks() + marks;
            if(newMarks > 100){
                return false;
            }
        }
        else if(selectedCourse.equals("Course2")){
            newMarks = student.getCourse2Marks() + marks;
            if(newMarks > 100){
                return false;
            }
        }
        else if(selectedCourse.equals("Course3")){
            newMarks = student.getCourse3Marks() + marks;
            if(newMarks > 100){
                return false;
            }
        }
        else if(selectedCourse.equals("Course4")){
            newMarks = student.getCourse4Marks() + marks;
            if(newMarks > 100){
                return false;
            }
        }
        return  true;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_enter_improvement, container, false);
        spinner = v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);


        //adapter for the spinner
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(getActivity(), R.layout.spinner_item,courses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        studentID = v.findViewById(R.id.tbStudentID2);
        improvementMarks = v.findViewById(R.id.tbImprovementMarks);
        submit = v.findViewById(R.id.submitImprovementButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh = new DBHelper(getActivity());
                student = new Student();
                int id = Integer.parseInt(studentID.getText().toString().trim());
                Cursor cursor = dbh.GetStudentGrades(id);

                if(cursor.getCount() == 1) {
                    student.setStudentID(cursor.getInt(0));
                    student.setStudentName(cursor.getString(1));
                    student.setProgram(cursor.getString(2));
                    student.setCourse1Marks(cursor.getInt(3));
                    student.setCourse2Marks(cursor.getInt(4));
                    student.setCourse3Marks(cursor.getInt(5));
                    student.setCourse4Marks(cursor.getInt(6));

                    createStatus = Validate(student,selectedCourse);
                }
                else {
                    Toast.makeText(getActivity(), "Student Not Found", Toast.LENGTH_SHORT).show();
                    createStatus = false;
                    return;
                }
                cursor.close();

                if(!createStatus){
                    Toast.makeText(getActivity(),"Marks cannot be greater than 100 in selected course",Toast.LENGTH_LONG).show();
                }
                else {
                    updateStatus = dbh.UpdateStudent(student,selectedCourse,newMarks);
                    insertStatus =dbh.InsertImprovement(student,selectedCourse, Integer.parseInt(improvementMarks.getText().toString()));
                }
                if(updateStatus){

                    if (insertStatus){
                        Toast.makeText(getActivity(),"Student's Grade Improvement Done",Toast.LENGTH_SHORT).show();
                    }
                }

                dbh.close();
            }

        });
        return v;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCourse = courses[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}