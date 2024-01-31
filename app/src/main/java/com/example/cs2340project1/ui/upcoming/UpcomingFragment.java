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

import com.example.cs2340project1.databinding.FragmentUpcomingBinding;
import com.google.android.material.divider.MaterialDividerItemDecoration;

public class UpcomingFragment extends Fragment {
    private static final String[] upcomingNames = { "MATH 3215", "CS 3511", "CS 2340", "LMC 3202", };
    private UpcomingViewModel toDoViewModel;
    private FragmentUpcomingBinding binding;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toDoViewModel = new ViewModelProvider(requireActivity()).get(UpcomingViewModel.class);

        binding = FragmentUpcomingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
//        toDoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        RecyclerView upcomingList = binding.upcomingClassList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        upcomingList.setLayoutManager(layoutManager);
        upcomingList.setAdapter(new UpcomingClassAdapter(upcomingNames));
        upcomingList.addItemDecoration(new MaterialDividerItemDecoration(upcomingList.getContext(),
                layoutManager.getOrientation()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}