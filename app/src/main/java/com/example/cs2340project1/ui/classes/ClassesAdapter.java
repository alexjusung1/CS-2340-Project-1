package com.example.cs2340project1.ui.classes;


import com.example.cs2340project1.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder>{
    private List<ClassObj> classList;
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
        ClassObj item = classList.get(position);
        holder.name.setText(item.getClassName());
        holder.instructor.setText(item.getInstructorName());
        holder.days.setText(item.getDays());
        holder.time.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public void setClasses(List<ClassObj> classes) {
        classList = classes;
        notifyDataSetChanged();
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