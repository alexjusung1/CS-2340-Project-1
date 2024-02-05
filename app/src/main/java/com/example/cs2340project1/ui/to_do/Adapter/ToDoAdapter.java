package com.example.cs2340project1.ui.to_do.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.R;
import com.example.cs2340project1.ui.to_do.AddNewTask;
import com.example.cs2340project1.ui.to_do.Model.ToDoModel;
import com.example.cs2340project1.ui.to_do.ToDoFragment;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoModel> toDoList;
    private ToDoFragment activity;

    public ToDoAdapter(ToDoFragment activity) {
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.to_do_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoModel item = toDoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
    }

    public int getItemCount() {
        return toDoList.size();
    }

    public void setTasks(List<ToDoModel> toDoList) {
        this.toDoList = toDoList;
        notifyDataSetChanged();
    }

    private boolean toBoolean(int number) {
        return number != 0;
    }

    public Context getContext() {
        return activity;
    }

    public void editItem(int position) {
        ToDoModel item = toDoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.toDoCheckBox);
        }
    }
}