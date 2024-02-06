package com.example.cs2340project1.data;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.ui.upcoming.UpcomingViewModel;

public abstract class UpcomingData {
    protected String title;
    protected ClassData attachedClass;

    public UpcomingData(String title, ClassData attachedClass) {
        this.title = title;
        this.attachedClass = attachedClass;
    }

    public String getTitle() { return title; }

    public ClassData getAttachedClass() { return attachedClass; }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UpcomingData) {
            UpcomingData other = (UpcomingData) o;
            return title.equals(other.title)
                    && attachedClass == other.attachedClass;
        }
        return false;
    }

    public abstract int getType();
}
