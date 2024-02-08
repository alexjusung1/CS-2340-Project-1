package com.example.cs2340project1.ui.upcoming;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.cs2340project1.R;
import com.example.cs2340project1.data.ClassObj;
import com.example.cs2340project1.data.UpcomingAssignmentData;
import com.example.cs2340project1.ui.classes.ClassesViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.divider.MaterialDividerItemDecoration;

public class UpcomingClassAdapter extends ListAdapter<ClassObj, UpcomingClassAdapter.ClassHolder> {
    private final Context listContext;
    private final UpcomingViewModel upcomingViewModel;
    private final ClassesViewModel classesViewModel;
    private final FragmentManager fragmentManager;
    private final RecyclerView recyclerView;

    public UpcomingClassAdapter(UpcomingViewModel upcomingViewModel, Context listContext,
                                ClassesViewModel classesViewModel, FragmentManager fragmentManager,
                                RecyclerView recyclerView) {
        super(DIFF_CALLBACK);
        this.listContext = listContext;
        this.upcomingViewModel = upcomingViewModel;
        this.classesViewModel = classesViewModel;
        this.fragmentManager = fragmentManager;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View upcomingClassView = LayoutInflater.from(listContext)
                .inflate(R.layout.upcoming_class, parent, false);

        return new ClassHolder(upcomingClassView, listContext,
                upcomingViewModel, classesViewModel, fragmentManager, recyclerView, this);
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
        final RecyclerView parentRecyclerView;
        final UpcomingClassAdapter upcomingClassAdapter;

        @SuppressLint("WrongViewCast")
        public ClassHolder(@NonNull View itemView, Context parentContext,
                           UpcomingViewModel upcomingViewModel, ClassesViewModel classesViewModel,
                           FragmentManager fragmentManager, RecyclerView recyclerView,
                           UpcomingClassAdapter upcomingClassAdapter) {
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

            adapter = new UpcomingAdapter(parentContext, upcomingViewModel, classesViewModel,
                    fragmentManager, upcomingList);
            upcomingList.setAdapter(adapter);

            parentRecyclerView = recyclerView;

            this.upcomingClassAdapter = upcomingClassAdapter;
        }

        public void bind(ClassObj classData) {
            className.setText(classData.getClassName());
            dropdownButton.setOnClickListener(view -> parentRecyclerView.post(() -> {
                ClassObj expandedClass = upcomingViewModel.getExpandedClass();
                if (expandedClass != null) {
                    upcomingViewModel.setExpandedClass(null);
                    int position = upcomingClassAdapter.getCurrentList().indexOf(expandedClass);
                    upcomingClassAdapter.notifyItemChanged(position);
                }
                if (expandedClass != classData) {
                    upcomingViewModel.setExpandedClass(classData);
                } else {
                    upcomingViewModel.setExpandedClass(null);
                }
                upcomingClassAdapter.notifyItemChanged(getAdapterPosition());
            }));

            if (upcomingViewModel.getExpandedClass() == classData) {
                upcomingList.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.VISIBLE);
                upcomingViewModel.attachClassUpcomingObserver(classData, adapter::submitList);
                addButton.setOnClickListener(view -> upcomingViewModel.addUpcomingData(
                        new UpcomingAssignmentData("New Assignment", classData)));
            } else {
                upcomingList.setVisibility(View.GONE);
                addButton.setVisibility(View.GONE);
            }
        }
    }

    public static final DiffUtil.ItemCallback<ClassObj> DIFF_CALLBACK = new DiffUtil.ItemCallback<ClassObj>() {
        @Override
        public boolean areItemsTheSame(@NonNull ClassObj oldItem, @NonNull ClassObj newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ClassObj oldItem, @NonNull ClassObj newItem) {
            return oldItem.equals(newItem);
        }
    };
}