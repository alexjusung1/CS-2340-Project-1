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
import com.example.cs2340project1.data.UpcomingAssignmentData;
import com.example.cs2340project1.data.UpcomingData;

public class UpcomingAdapter extends ListAdapter<UpcomingData, UpcomingAdapter.ClassHolder> {
    private UpcomingViewModel upcomingViewModel;

    protected UpcomingAdapter(UpcomingViewModel upcomingViewModel) {
        super(DIFF_CALLBACK);
        this.upcomingViewModel = upcomingViewModel;
    }

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View upcomingAssignmentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_upcoming_assignment, parent, false);

        return new ClassHolder(upcomingAssignmentView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ClassHolder extends RecyclerView.ViewHolder {
        final TextView upcomingTitle;

        public ClassHolder(@NonNull View itemView) {
            super(itemView);
            upcomingTitle = itemView.findViewById(R.id.upcomingTitle);
        }

        public void bind(UpcomingData data) {
            upcomingTitle.setText(data.getTitle());
        }
    }

    public static final DiffUtil.ItemCallback<UpcomingData> DIFF_CALLBACK = new DiffUtil.ItemCallback<UpcomingData>() {
        @Override
        public boolean areItemsTheSame(@NonNull UpcomingData oldItem, @NonNull UpcomingData newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UpcomingData oldItem, @NonNull UpcomingData newItem) {
            return oldItem.equals(newItem);
        }
    };
}
