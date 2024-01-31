package com.example.cs2340project1.data;

import android.content.SharedPreferences;

import java.io.Serializable;

public class UpcomingData {
    protected String title;
    protected ClassData attachedClass;

    @Override
    public boolean equals(Object o) {
        if (o instanceof UpcomingData) {
            UpcomingData other = (UpcomingData) o;
            return title.equals(other.title)
                    && attachedClass == other.attachedClass;
        }
        return false;
    }
}
