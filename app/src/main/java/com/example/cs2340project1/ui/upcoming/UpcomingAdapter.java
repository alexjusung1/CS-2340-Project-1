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
import com.example.cs2340project1.data.UpcomingExamData;

import java.util.HashMap;
import java.util.Map;

public class UpcomingAdapter extends ListAdapter<UpcomingData, UpcomingData.UpcomingHolder> {
    private UpcomingViewModel upcomingViewModel;

    protected UpcomingAdapter(UpcomingViewModel upcomingViewModel) {
        super(DIFF_CALLBACK);
        this.upcomingViewModel = upcomingViewModel;
    }

    @NonNull
    @Override
    public UpcomingData.UpcomingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                return new UpcomingAssignmentData.AssignmentHolder(
                        inflater.inflate(R.layout.upcoming_assignment, parent, false));
            case 1:
                return new UpcomingExamData.ExamHolder(
                        inflater.inflate(R.layout.upcoming_exam, parent, false));
            case 2:

        }
        throw new IllegalArgumentException("ViewType is not supported");
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingData.UpcomingHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
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
