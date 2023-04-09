package com.example.rahulbhaiassignment2;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchGrade extends Fragment {
    View v;
    EditText studentID;
    TextView studentName;
    TextView program;
    TextView course1;
    TextView course2;
    TextView course3;
    TextView course4;
    TextView totalMarks;
    Student student;
    DBHelper dbh;
    Button searchBtn;




    public SearchGrade() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_search_grade, container, false);
        studentID = v.findViewById(R.id.tbStudentID3);
        studentName = v.findViewById(R.id.txtStudentName1);
        program = v.findViewById(R.id.txtProgram);
        course1 = v.findViewById(R.id.txtCourse1);
        course2 = v.findViewById(R.id.txtCourse2);
        course3 = v.findViewById(R.id.txtCourse3);
        course4 = v.findViewById(R.id.txtCourse4);
        totalMarks = v.findViewById(R.id.txtTotalMarks);
        searchBtn = v.findViewById(R.id.SearchStudentButton);
        dbh = new DBHelper(getActivity());



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(studentID.getText().toString().trim());
                Cursor cursor = dbh.GetStudentGrades(id);
                if(cursor.getCount() == 1){

                    student = new Student();
                    student.setStudentID(cursor.getInt(0));
                    student.setStudentName(cursor.getString(1));
                    student.setProgram(cursor.getString(2));
                    student.setCourse1Marks(cursor.getInt(3));
                    student.setCourse2Marks(cursor.getInt(4));
                    student.setCourse3Marks(cursor.getInt(5));
                    student.setCourse4Marks(cursor.getInt(6));


                    studentName.setText("Name : " + student.getStudentName());
                    program.setText("Program : " + student.getProgram());
                    course1.setText("Course1 Marks : " + student.getCourse1Marks());
                    course2.setText("Course1 Marks : " + student.getCourse2Marks());
                    course3.setText("Course1 Marks : " + student.getCourse3Marks());
                    course4.setText("Course1 Marks : " + student.getCourse4Marks());
                    totalMarks.setText("Course1 Marks : " + student.getTotalMarks());


                }
                else {
                    Toast.makeText(getActivity(), "Student Not Found", Toast.LENGTH_LONG).show();
                }
                cursor.close();
                dbh.close();
            }
        });


        return v;
    }
}