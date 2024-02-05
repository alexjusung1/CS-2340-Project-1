package com.example.cs2340project1.ui.upcoming;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.cs2340project1.R;
import com.example.cs2340project1.data.ClassData;
import com.example.cs2340project1.data.UpcomingAssignmentData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.divider.MaterialDividerItemDecoration;

public class UpcomingClassAdapter extends ListAdapter<ClassData, UpcomingClassAdapter.ClassHolder> {
    private ClassHolder currentExpanded;
    private UpcomingViewModel upcomingViewModel;

    public UpcomingClassAdapter(UpcomingViewModel upcomingViewModel) {
        super(DIFF_CALLBACK);
        this.upcomingViewModel = upcomingViewModel;
    }

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View upcomingClassView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upcoming_class, parent, false);

        ClassHolder holder = new ClassHolder(upcomingClassView, parent.getContext(), upcomingViewModel);

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
        final UpcomingViewModel upcomingViewModel;
        final TextView className;
        final MaterialButton dropdownButton;
        final MaterialButton addButton;
        final RecyclerView upcomingList;
        final UpcomingAdapter adapter;
        boolean expanded = false;

        @SuppressLint("WrongViewCast")
        public ClassHolder(@NonNull View itemView, Context parentContext, UpcomingViewModel upcomingViewModel) {
            super(itemView);

            this.upcomingViewModel = upcomingViewModel;

            className = itemView.findViewById(R.id.upcomingClassName);
            dropdownButton = itemView.findViewById(R.id.dropdownButton);
            addButton = itemView.findViewById(R.id.addButton);
            upcomingList = itemView.findViewById(R.id.upcomingList);

            LinearLayoutManager layoutManager = new LinearLayoutManager(parentContext);
            upcomingList.setLayoutManager(layoutManager);

            MaterialDividerItemDecoration decoration = new MaterialDividerItemDecoration(upcomingList.getContext(),
                    layoutManager.getOrientation());
            decoration.setDividerColor(Color.TRANSPARENT);
            decoration.setDividerThickness(8);
            upcomingList.addItemDecoration(decoration);

            SimpleItemAnimator animator = new DefaultItemAnimator();
            animator.setSupportsChangeAnimations(false);
            upcomingList.setItemAnimator(animator);

            adapter = new UpcomingAdapter(upcomingViewModel);
            upcomingList.setAdapter(adapter);
        }

        public void bind(ClassData classData) {
            className.setText(classData.getClassName());
            if (expanded) {
                upcomingList.setVisibility(View.VISIBLE);
                upcomingViewModel.attachClassUpcomingObserver(classData, adapter::submitList);
                addButton.setVisibility(View.VISIBLE);
                addButton.setOnClickListener(view -> {
                    upcomingViewModel.addUpcomingData(new UpcomingAssignmentData("New Assignment",
                            classData));
                });
            } else {
                upcomingList.setVisibility(View.GONE);
                addButton.setVisibility(View.GONE);
            }
        }
    }

    public static final DiffUtil.ItemCallback<ClassData> DIFF_CALLBACK = new DiffUtil.ItemCallback<ClassData>() {
        @Override
        public boolean areItemsTheSame(@NonNull ClassData oldItem, @NonNull ClassData newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ClassData oldItem, @NonNull ClassData newItem) {
            return oldItem.equals(newItem);
        }
    };
}