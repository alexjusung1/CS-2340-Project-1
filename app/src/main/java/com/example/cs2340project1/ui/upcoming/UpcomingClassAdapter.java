package com.example.cs2340project1.ui.upcoming;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.R;
import com.example.cs2340project1.data.ClassData;
import com.google.android.material.button.MaterialButton;

public class UpcomingClassAdapter extends ListAdapter<ClassData, UpcomingClassAdapter.ClassHolder> {
    private ClassHolder currentExpanded;

    protected UpcomingClassAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View upcomingClassView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_upcoming_class, parent, false);


        ClassHolder holder = new ClassHolder(upcomingClassView);
        holder.dropdownButton.setOnClickListener(v -> {
            if (currentExpanded != null) {
                currentExpanded.expanded = false;
                notifyItemChanged(currentExpanded.getAdapterPosition());
            }
            if (currentExpanded != holder) {
                currentExpanded = holder;
                holder.expanded = true;
                notifyItemChanged(holder.getAdapterPosition());
            } else {
                currentExpanded = null;
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ClassHolder extends RecyclerView.ViewHolder {
        final TextView className;
        final MaterialButton dropdownButton;
        final RecyclerView upcomingList;
        boolean expanded = false;

        @SuppressLint("WrongViewCast")
        public ClassHolder(@NonNull View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.upcomingClassName);
            dropdownButton = itemView.findViewById(R.id.dropdownButton);
            upcomingList = itemView.findViewById(R.id.upcomingList);
        }

        public void bind(ClassData classData) {
            className.setText(classData.getClassName());
            upcomingList.setVisibility(expanded ? View.VISIBLE : View.GONE);
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