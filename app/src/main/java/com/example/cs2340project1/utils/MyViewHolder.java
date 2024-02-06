package com.example.cs2340project1.utils;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.data.UpcomingData;
import com.example.cs2340project1.ui.upcoming.UpcomingViewModel;

public abstract class MyViewHolder extends RecyclerView.ViewHolder {
    protected final Context parentContext;
    protected final UpcomingViewModel upcomingViewModel;
    protected final ListAdapter<UpcomingData, MyViewHolder> adapter;
    protected final FragmentManager fragmentManager;

    public MyViewHolder(@NonNull View itemView, Context parentContext,
                        UpcomingViewModel upcomingViewModel,
                        ListAdapter<UpcomingData, MyViewHolder> adapter,
                        FragmentManager fragmentManager) {
        super(itemView);
        this.parentContext = parentContext;
        this.upcomingViewModel = upcomingViewModel;
        this.adapter = adapter;
        this.fragmentManager = fragmentManager;
    }

    public abstract void bind(UpcomingData data);
}