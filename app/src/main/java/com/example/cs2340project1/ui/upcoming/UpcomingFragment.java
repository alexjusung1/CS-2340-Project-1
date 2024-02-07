package com.example.cs2340project1.ui.upcoming;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.cs2340project1.R;
import com.example.cs2340project1.data.UpcomingData;
import com.example.cs2340project1.databinding.FragmentUpcomingBinding;
import com.example.cs2340project1.ui.classes.ClassesViewModel;
import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UpcomingFragment extends Fragment {
    private FragmentUpcomingBinding binding;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUpcomingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ClassesViewModel classesViewModel = new ViewModelProvider(requireActivity()).get(ClassesViewModel.class);
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new UpcomingViewModel(classesViewModel.getClassList());
            }
        };
        UpcomingViewModel upcomingViewModel = new ViewModelProvider(requireActivity(), factory)
                .get(UpcomingViewModel.class);

        MaterialAutoCompleteTextView sortTextView = (MaterialAutoCompleteTextView) binding.sortText;

        String[] sortOptions = getResources().getStringArray(R.array.upcoming_sort_by);

        sortTextView.setSaveEnabled(false);
        sortTextView.setText(sortOptions[upcomingViewModel.getSortType()], false);
        sortTextView.setSimpleItems(sortOptions);
        sortTextView.setOnItemClickListener((parent, view, position, id) -> {
            upcomingViewModel.updateSortType(position);
        });

        RecyclerView upcomingList = binding.upcomingClassList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        upcomingList.setLayoutManager(layoutManager);

        SimpleItemAnimator animator = new DefaultItemAnimator();
        animator.setSupportsChangeAnimations(false);
        upcomingList.setItemAnimator(animator);

        UpcomingClassAdapter classAdapter = new UpcomingClassAdapter(upcomingViewModel,
                upcomingList.getContext(), classesViewModel, getParentFragmentManager());
        MaterialDividerItemDecoration classDecoration = new MaterialDividerItemDecoration(upcomingList.getContext(),
                layoutManager.getOrientation());

        UpcomingAdapter dateAdapter = new UpcomingAdapter(upcomingList.getContext(), upcomingViewModel,
                classesViewModel, getParentFragmentManager(), upcomingList);
        MaterialDividerItemDecoration dateDecoration = new MaterialDividerItemDecoration(upcomingList.getContext(),
                layoutManager.getOrientation());
        dateDecoration.setDividerColor(Color.TRANSPARENT);
        dateDecoration.setDividerThickness(8);

        upcomingViewModel.attachToSortType(getViewLifecycleOwner(), sortType -> {
            while (upcomingList.getItemDecorationCount() > 0) {
                upcomingList.removeItemDecorationAt(0);
            }

            switch (sortType) {
                case UpcomingViewModel.CLASS_SORT:
                    upcomingList.setAdapter(classAdapter);
                    upcomingList.addItemDecoration(classDecoration);
                    classesViewModel.attachClassListObserver(getViewLifecycleOwner(), classAdapter::submitList);
                    binding.materialDividerClass.setVisibility(View.VISIBLE);
                    break;
                case UpcomingViewModel.DATE_SORT:
                    upcomingList.setAdapter(dateAdapter);
                    upcomingList.addItemDecoration(dateDecoration);
                    upcomingViewModel.attachUpcomingObserver(getViewLifecycleOwner(), list -> {
                        List<UpcomingData> sortedList = list.stream()
                                .sorted(Comparator.comparing(UpcomingData::getRepresentativeDate))
                                .collect(Collectors.toList());
                        dateAdapter.submitList(sortedList);
                    });
                    binding.materialDividerClass.setVisibility(View.GONE);
                    break;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}