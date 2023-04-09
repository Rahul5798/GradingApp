package com.example.rahulbhaiassignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Student> studentList;

    public ListAdapter(ArrayList<Student> studentList, Context context){
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Student student = studentList.get(position);
        ((ViewHolder) holder).sID.setText("Student ID : " + String.valueOf(student.getStudentID()));
        ((ViewHolder) holder).sName.setText("Name : " + String.valueOf(student.getStudentName()));
        ((ViewHolder) holder).sProgram.setText("Program : " + String.valueOf(student.getProgram()));
        ((ViewHolder) holder).sMarks1.setText("Course1 Marks : " + String.valueOf(student.getCourse1Marks()));
        ((ViewHolder) holder).sMarks2.setText("Course2 Marks : " + String.valueOf(student.getCourse2Marks()));
        ((ViewHolder) holder).sMarks3.setText("Course3 Marks : " + String.valueOf(student.getCourse3Marks()));
        ((ViewHolder) holder).sMarks4.setText("Course4 Marks : " + String.valueOf(student.getCourse4Marks()));
        ((ViewHolder) holder).sTotalMarks.setText("Total Marks : " + String.valueOf(student.getTotalMarks()));

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView sID,sName, sProgram, sMarks1, sMarks2, sMarks3, sMarks4, sTotalMarks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sID = (TextView) itemView.findViewById(R.id.txtSID);
            sName = (TextView) itemView.findViewById(R.id.txtSName);
            sProgram = (TextView) itemView.findViewById(R.id.txtSProgram);
            sMarks1 = (TextView) itemView.findViewById(R.id.txtSCourse1);
            sMarks2 = (TextView) itemView.findViewById(R.id.txtSCourse2);
            sMarks3 = (TextView) itemView.findViewById(R.id.txtSCourse3);
            sMarks4 = (TextView) itemView.findViewById(R.id.txtSCourse4);
            sTotalMarks = (TextView) itemView.findViewById(R.id.txtSTotalMarks);


        }
    }
}
