package com.example.cs2340project1.ui.to_do;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.R;
import com.example.cs2340project1.ui.upcoming.UpcomingClassAdapter;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder> {
    private String[] toDoListNames;
    public ToDoListAdapter(String[] toDoListNames) {
        this.toDoListNames = toDoListNames;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toDoRowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_to_do_list_items, parent, false);
        return new ToDoListAdapter.MyViewHolder(toDoRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.getToDoRowItem().setText(toDoListNames[position]);
    }

    @Override
    public int getItemCount() {
        return toDoListNames.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView toDoListView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoListView = itemView.findViewById(R.id.toDoRowItem);
        }

        public TextView getToDoRowItem() {return toDoListView;}
    }
}
