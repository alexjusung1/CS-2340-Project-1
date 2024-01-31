package com.example.cs2340project1.ui.upcoming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.R;

public class UpcomingClassAdapter extends RecyclerView.Adapter<UpcomingClassAdapter.MyViewHolder> {
    private String[] upcomingClassNames;

    public UpcomingClassAdapter(String[] upcomingClassNames) {
        this.upcomingClassNames = upcomingClassNames;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View upcomingClassRowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_upcoming_class_row, parent, false);
        return new MyViewHolder(upcomingClassRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.upcomingClassNameView.setText(upcomingClassNames[position]);
    }

    @Override
    public int getItemCount() {
        return upcomingClassNames.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView upcomingClassNameView;

        public MyViewHolder(View view) {
            super(view);
            upcomingClassNameView = view.findViewById(R.id.upcomingClassName);
        }
    }
}