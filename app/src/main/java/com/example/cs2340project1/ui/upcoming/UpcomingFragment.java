package com.example.cs2340project1.ui.upcoming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.UpcomingClass;
import com.example.cs2340project1.databinding.FragmentUpcomingBinding;
import com.example.cs2340project1.ui.classes.ClassesViewModel;
import com.google.android.material.divider.MaterialDividerItemDecoration;

public class UpcomingFragment extends Fragment {
    private static final String[] upcomingNames = { "MATH 3215", "CS 3511", "CS 2340", "LMC 3202", };
    private FragmentUpcomingBinding binding;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProvider activityVMProvider = new ViewModelProvider(requireActivity());

        ClassesViewModel classesViewModel = activityVMProvider.get(ClassesViewModel.class);
        UpcomingViewModel upcomingViewModel = activityVMProvider.get(UpcomingViewModel.class);

        binding = FragmentUpcomingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView upcomingList = binding.upcomingClassList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        upcomingList.setLayoutManager(layoutManager);

        UpcomingClassAdapter adapter = new UpcomingClassAdapter();
        upcomingList.setAdapter(adapter);
        upcomingList.addItemDecoration(new MaterialDividerItemDecoration(upcomingList.getContext(),
                layoutManager.getOrientation()));

        classesViewModel.getClassList().observe(getViewLifecycleOwner(), adapter::submitList);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}