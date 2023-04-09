package com.example.rahulbhaiassignment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterGrades extends Fragment {

    View v;
    EditText tbStudentName;
    EditText tbStudentID;
    EditText tbProgram;
    EditText tbCourse1Marks;
    EditText tbCourse2Marks;
    EditText tbCourse3Marks;
    EditText tbCourse4Marks;
    Button submit;
    DBHelper dbh;
    Boolean insertStatus;
    public EnterGrades() {
        // Required empty public constructor
    }
    public Student CreateStudent(){
        Student student = new Student();
        int sId = 0;
        student.setStudentID(sId);
        student.setStudentName(tbStudentName.getText().toString().trim());
        student.setProgram(tbProgram.getText().toString().trim());

        student.setCourse1Marks(Integer.parseInt(String.valueOf(tbCourse1Marks.getText())));
        student.setCourse2Marks(Integer.parseInt(String.valueOf(tbCourse2Marks.getText())));
        student.setCourse3Marks(Integer.parseInt(String.valueOf(tbCourse3Marks.getText())));
        student.setCourse4Marks(Integer.parseInt(String.valueOf(tbCourse4Marks.getText())));

        return student;
    }
    //function for checking grades are not greater than 100 as well as checking if any field is empty
    public boolean Validate(){
        if((tbStudentName.getText().toString().equals(""))){
            return false;
        }else if((tbProgram.getText().toString().equals(""))){
            return false;
        }else if((tbCourse1Marks.getText().toString().equals("")) ||
                (Integer.parseInt(String.valueOf(tbCourse1Marks.getText())) > 100
                        || (Integer.parseInt(String.valueOf(tbCourse1Marks.getText())) < 0))){
            return false;
        }else if((tbCourse2Marks.getText().toString().equals("")) ||
                (Integer.parseInt(String.valueOf(tbCourse2Marks.getText())) > 100
                        || (Integer.parseInt(String.valueOf(tbCourse1Marks.getText())) < 0))){
            return false;
        }else if((tbCourse3Marks.getText().toString().equals("")) ||
                (Integer.parseInt(String.valueOf(tbCourse3Marks.getText())) > 100
                        || (Integer.parseInt(String.valueOf(tbCourse1Marks.getText())) < 0))){
            return false;
        }else if((tbCourse4Marks.getText().toString().equals("")) ||
                (Integer.parseInt(String.valueOf(tbCourse4Marks.getText())) > 100
                        || (Integer.parseInt(String.valueOf(tbCourse1Marks.getText())) < 0))){
            return false;
        }
    return  true;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_enter_grades, container, false);
        tbStudentName = v.findViewById(R.id.tbStudentName);
        tbProgram = v.findViewById(R.id.tbStudentProgram);
        tbCourse1Marks = v.findViewById(R.id.tbCourse1Marks);
        tbCourse2Marks = v.findViewById(R.id.tbCourse2Marks);
        tbCourse3Marks = v.findViewById(R.id.tbCourse3Marks);
        tbCourse4Marks = v.findViewById(R.id.tbCourse4Marks);
        submit = v.findViewById(R.id.submitMarksButton);
        dbh = new DBHelper(getActivity());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean createStatus = Validate();
                if(createStatus){
                     Student student = CreateStudent();
                     insertStatus = dbh.InsertGrades(student);
                     if (insertStatus){
                     Toast.makeText(getActivity(),"Record Added Successfully", Toast.LENGTH_LONG).show();
                     }
                     else {
                     Toast.makeText(getActivity(),"Record Insertion Failed",Toast.LENGTH_LONG).show();
                     }
                }
                else {
                    Toast.makeText(getActivity(),"Records are not filled properly ",Toast.LENGTH_LONG).show();

                }

            }
        });


        return v;

    }

}