package com.example.cs2340project1.ui.to_do;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private List<Task> tasks;
    private Context context;
    private ToDoViewModel toDoViewModel;

    public ToDoAdapter(Context context, List<Task> tasks, ToDoViewModel toDoViewModel) {
        this.context = context;
        this.tasks = tasks;
        this.toDoViewModel = toDoViewModel;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_to_do, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.toDoTextView.setText(task.getTaskName());
        holder.toDoCheckBox.setOnCheckedChangeListener(null);
        holder.toDoCheckBox.setChecked(false);

        holder.toDoCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (task.isChecked()) {
                holder.toDoTextView.setTextColor(ContextCompat.getColor(context, R.color.completed_task_color));
            } else {
                holder.toDoTextView.setTextColor(ContextCompat.getColor(context, R.color.default_text_color));
            }
        });

        holder.itemView.setOnClickListener(v -> {
            toDoViewModel.deleteTask(task);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        TextView toDoTextView;
        CheckBox toDoCheckBox;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoTextView = itemView.findViewById(R.id.toDoTextView);
            toDoCheckBox = itemView.findViewById(R.id.toDoCheckBox);
        }
    }
}
