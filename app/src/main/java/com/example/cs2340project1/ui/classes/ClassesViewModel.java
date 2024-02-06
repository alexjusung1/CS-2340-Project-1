package com.example.cs2340project1.ui.classes;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.cs2340project1.data.ClassObj;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClassesViewModel extends ViewModel {
    private final MutableLiveData<List<ClassObj>> classList;

    public ClassesViewModel() {
        List<ClassObj> testList = new ArrayList<>();
        testList.add(new ClassObj("CS 2340", "Pedroguillermo Feijoogarcia",
                "T Th", "2:00 - 3:15"));
        testList.add(new ClassObj("CS 1332", "Frederic Faulkner",
                "M W F", "2:00 - 2:50"));
        testList.add(new ClassObj("CS 2110", "Dan Forsyth",
                "M T W Th", "5:00 - 6:15"));

        classList = new MutableLiveData<>(testList);
    }

    public MutableLiveData<List<ClassObj>> getClassList() {
        return classList;
    }

    public List<ClassObj> getData() {
        return classList.getValue();
    }

    public void attachClassListObserver(LifecycleOwner owner, Observer<List<ClassObj>> observer) {
        classList.observe(owner, observer);
    }

    public void setClassList(List<ClassObj> cl) {
        classList.setValue(cl);
    }

    public void addClass(ClassObj data) {
        List<ClassObj> list = classList.getValue();
        list.add(data);
        classList.setValue(list);
    }

    public void removeClass(ClassObj data) {
        List<ClassObj> list = classList.getValue();
        list.remove(data);
        classList.setValue(list);
    }

    public void removeIndex(int pos) {
        List<ClassObj> list = classList.getValue();
        list.remove(pos);
        classList.setValue(list);
    }

    /**
     *  Overload that removes class by class name (not used - just added in case)
     * @param className name of class to remove
     * @return classData object that was removed
     */
    public ClassObj removeClass(String className) {
        List<ClassObj> list = classList.getValue();
        ClassObj ret = null;
        for (ClassObj data : list) {
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