package com.example.rahulbhaiassignment2;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ListStudents extends Fragment {
    ArrayList<Student> students = new ArrayList<>();
    RecyclerView recyclerView;
    View v;
    ListAdapter listAdapter;
    DBHelper dbh;

    public ListStudents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_list_students, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        dbh = new DBHelper(getActivity());


        Cursor cursor = dbh.GetStudentList();
        if(cursor == null){
            Toast.makeText(getActivity(), "No student records found", Toast.LENGTH_LONG).show();

        }
        else {

            cursor.moveToFirst();
            do {
                Student student = new Student();
                student.setStudentID(cursor.getInt(0));
                student.setStudentName(cursor.getString(1));
                student.setProgram(cursor.getString(2));
                student.setCourse1Marks(cursor.getInt(3));
                student.setCourse2Marks(cursor.getInt(4));
                student.setCourse3Marks(cursor.getInt(5));
                student.setCourse4Marks(cursor.getInt(6));
                students.add(student);
            }while (cursor.moveToNext());
            cursor.close();
            dbh.close();
            BindAdapter();
        }
        return v;
    }
    private  void  BindAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ListAdapter(students, getContext());
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }
}