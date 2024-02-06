package com.example.cs2340project1.ui.classes;


import com.example.cs2340project1.R;
import com.example.cs2340project1.data.ClassObj;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder>{
    private ClassesViewModel classList;
    private ClassesFragment classesFragment;

    public ClassesAdapter(ClassesFragment frag) {
        classesFragment = frag;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_card, parent, false);
        return new ViewHolder(itemView);
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassObj item = classList.getData().get(position);
        holder.name.setText(item.getClassName());
        holder.instructor.setText(item.getInstructorName());
        holder.days.setText(item.getDays());
        holder.time.setText(item.getTime());
    }

    public void setViewModel(ClassesViewModel c) {
        classList = c;
    }

    @Override
    public int getItemCount() {
        return classList.getData().size();
    }

    public Context getContext() {
        return classesFragment.getContext();
    }

    public void setClasses(List<ClassObj> classes) {
        classList.setClassList(classes);
        notifyDataSetChanged();
    }


    public void deleteItem(int position) {
        ClassObj item = classList.getData().get(position);
        classList.removeIndex(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ClassObj item = classList.getData().get(position);
        Bundle bundle = new Bundle();
        bundle.putString("className", item.getClassName());
        bundle.putString("instructorName", item.getInstructorName());
        bundle.putString("days", item.getDays());
        bundle.putString("time", item.getTime());
        deleteItem(position);
        classesFragment.edit(bundle);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView instructor;
        TextView days;
        TextView time;
        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.className);
            instructor = view.findViewById(R.id.classInstructor);
            days = view.findViewById(R.id.classDays);
            time = view.findViewById(R.id.classTime);
        }
    }
}