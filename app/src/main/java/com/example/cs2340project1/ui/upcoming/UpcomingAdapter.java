package com.example.cs2340project1.ui.upcoming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.R;
import com.example.cs2340project1.data.UpcomingAssignmentData;
import com.example.cs2340project1.data.UpcomingData;
import com.example.cs2340project1.data.UpcomingEditData;
import com.example.cs2340project1.data.UpcomingExamData;
import com.example.cs2340project1.ui.classes.ClassesViewModel;
import com.example.cs2340project1.utils.MyViewHolder;

public class UpcomingAdapter extends ListAdapter<UpcomingData, MyViewHolder> {
    private Context listContext;
    private UpcomingViewModel upcomingViewModel;
    private ClassesViewModel classesViewModel;
    private FragmentManager fragmentManager;
    private RecyclerView recyclerView;

    protected UpcomingAdapter(Context listContext, UpcomingViewModel upcomingViewModel,
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                return new UpcomingAssignmentData.AssignmentHolder(
                        inflater.inflate(R.layout.upcoming_assignment, parent, false),
                        listContext, upcomingViewModel);
            case 1:
                return new UpcomingExamData.ExamHolder(
                        inflater.inflate(R.layout.upcoming_exam, parent, false),
                        listContext, upcomingViewModel);
            case 2:
                return new UpcomingEditData.UpcomingEditHolder(
                        inflater.inflate(R.layout.upcoming_edit, parent, false),
                        listContext, upcomingViewModel, classesViewModel, recyclerView,
                        fragmentManager);
        }
        throw new IllegalArgumentException("ViewType is not supported");
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
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
