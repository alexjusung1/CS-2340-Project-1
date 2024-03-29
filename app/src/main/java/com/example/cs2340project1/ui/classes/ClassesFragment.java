package com.example.cs2340project1.ui.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340project1.data.ClassObj;
import com.example.cs2340project1.databinding.FragmentClassesBinding;

public class ClassesFragment extends Fragment {

    private FragmentClassesBinding binding;
    private RecyclerView classRecyclerView;
    private ClassesAdapter classesAdapter;

    private ClassesViewModel classesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        classesViewModel =
                new ViewModelProvider(requireActivity()).get(ClassesViewModel.class);

        binding = FragmentClassesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        classRecyclerView = binding.classList;
        classRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        classesAdapter = new ClassesAdapter(this);
        classRecyclerView.setAdapter(classesAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerClassTouchHelper(classesAdapter));
        itemTouchHelper.attachToRecyclerView(classRecyclerView);

        classesAdapter.setViewModel(classesViewModel);


//        final TextView textView = binding.textHome;
//        classesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void edit(Bundle bundle) {
        bundle.get("className");
        binding.classNameField.setText(bundle.get("className").toString());
        binding.classInstructorField.setText(bundle.get("instructorName").toString());
        binding.classDaysField.setText(bundle.get("days").toString());
        binding.classTimeField.setText(bundle.get("time").toString());
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classesViewModel.addClass(new ClassObj(binding.classNameField.getText().toString(),
                        binding.classInstructorField.getText().toString(),
                        binding.classDaysField.getText().toString(),
                        binding.classTimeField.getText().toString()));
                classesAdapter.notifyDataSetChanged();
                binding.classNameField.setText("");
                binding.classInstructorField.setText("");
                binding.classDaysField.setText("");
                binding.classTimeField.setText("");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}