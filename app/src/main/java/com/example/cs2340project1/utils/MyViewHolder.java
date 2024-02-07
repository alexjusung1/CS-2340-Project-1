package com.example.cs2340project1.utils;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.data.UpcomingData;
import com.example.cs2340project1.ui.classes.ClassesViewModel;
import com.example.cs2340project1.ui.upcoming.UpcomingViewModel;

public abstract class MyViewHolder extends RecyclerView.ViewHolder {
    protected final Context parentContext;
    protected final UpcomingViewModel upcomingViewModel;
    protected final ClassesViewModel classesViewModel;
    protected final RecyclerView recyclerView;
    protected final FragmentManager fragmentManager;

    public MyViewHolder(@NonNull View itemView, Context parentContext,
                        UpcomingViewModel upcomingViewModel,
                        ClassesViewModel classesViewModel,
                        RecyclerView recyclerView,
                        FragmentManager fragmentManager) {
        super(itemView);
        this.parentContext = parentContext;
        this.upcomingViewModel = upcomingViewModel;
        this.classesViewModel = classesViewModel;
        this.recyclerView = recyclerView;
        this.fragmentManager = fragmentManager;
    }

    public abstract void bind(UpcomingData data);
}