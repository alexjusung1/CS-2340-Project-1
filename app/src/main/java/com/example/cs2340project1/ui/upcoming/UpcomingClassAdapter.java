package com.example.cs2340project1.ui.upcoming;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.R;
import com.example.cs2340project1.data.ClassData;
import com.example.cs2340project1.ui.to_do.ToDoListAdapter;

public class UpcomingClassAdapter extends ListAdapter<ClassData, UpcomingClassAdapter.ClassHolder> {

    protected UpcomingClassAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View upcomingClassView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_upcoming_class, parent, false);
        return new ClassHolder(upcomingClassView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ClassHolder extends RecyclerView.ViewHolder {
        TextView className;
        RecyclerView upcomingList;

        public ClassHolder(@NonNull View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.upcomingClassName);
            upcomingList = itemView.findViewById(R.id.upcomingList);
        }

        public void bind(ClassData classData) {
            className.setText(classData.getClassName());
        }
    }

    public static final DiffUtil.ItemCallback<ClassData> DIFF_CALLBACK = new DiffUtil.ItemCallback<ClassData>() {
        @Override
        public boolean areItemsTheSame(@NonNull ClassData oldItem, @NonNull ClassData newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ClassData oldItem, @NonNull ClassData newItem) {
            return oldItem.equals(newItem);
        }
    };
}