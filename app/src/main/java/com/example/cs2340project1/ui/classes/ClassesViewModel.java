package com.example.cs2340project1.ui.classes;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.cs2340project1.data.ClassData;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClassesViewModel extends ViewModel {
    private final MutableLiveData<List<ClassData>> classList;

    public ClassesViewModel() {
        List<ClassData> testList = new ArrayList<>();
        testList.add(new ClassData.Builder().className("Class 1").build());
        testList.add(new ClassData.Builder().className("Class 2").build());
        testList.add(new ClassData.Builder().className("Class 3").build());
        testList.add(new ClassData.Builder().className("Class 4").build());

        classList = new MutableLiveData<>(testList);
    }

    public MutableLiveData<List<ClassData>> getClassList() {
        return classList;
    }

    public void attachClassListObserver(LifecycleOwner owner, Observer<List<ClassData>> observer) {
        classList.observe(owner, observer);
    }

    public void addClass(ClassData data) {
        List<ClassData> list = classList.getValue();
        list.add(data);
        classList.setValue(list);
    }

    public void removeClass(ClassData data) {
        List<ClassData> list = classList.getValue();
        list.remove(data);
        classList.setValue(list);
    }

    /**
     *  Overload that removes class by class name (not used - just added in case)
     * @param className name of class to remove
     * @return classData object that was removed
     */
    public ClassData removeClass(String className) {
        List<ClassData> list = classList.getValue();
        ClassData ret = null;
        for (ClassData data : list) {
            if (data.getClassName().equals(className)) {
                ret = data;
            }
        }
        if (ret == null) {
            throw new NoSuchElementException("No class with such class name in list");
        }
        list.remove(ret);
        classList.setValue(list);
        return ret;
    }

    // TODO: Implement adding to classList when implementing ClassesFragment
}