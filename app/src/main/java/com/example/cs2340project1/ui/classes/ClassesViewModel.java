package com.example.cs2340project1.ui.classes;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.cs2340project1.data.ClassData;

import java.util.ArrayList;
import java.util.List;

public class ClassesViewModel extends ViewModel {
    private final MutableLiveData<List<ClassData>> classList;

    public ClassesViewModel() {
        List<ClassData> testList = new ArrayList<>();
        testList.add(new ClassData.ClassDataBuilder().className("Class 1").build());
        testList.add(new ClassData.ClassDataBuilder().className("Class 2").build());
        testList.add(new ClassData.ClassDataBuilder().className("Class 3").build());
        testList.add(new ClassData.ClassDataBuilder().className("Class 4").build());

        classList = new MutableLiveData<>(testList);
    }

    public MutableLiveData<List<ClassData>> getClassList() {
        return classList;
    }

    public void attachClassListObserver(LifecycleOwner owner, Observer<List<ClassData>> observer) {
        classList.observe(owner, observer);
    }

    // TODO: Implement adding to classList when implementing ClassesFragment
}