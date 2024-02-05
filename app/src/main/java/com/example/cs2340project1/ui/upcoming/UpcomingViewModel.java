package com.example.cs2340project1.ui.upcoming;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.cs2340project1.data.ClassData;
import com.example.cs2340project1.data.UpcomingAssignmentData;
import com.example.cs2340project1.data.UpcomingData;
import com.example.cs2340project1.data.UpcomingExamData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UpcomingViewModel extends ViewModel {
    private final MutableLiveData<List<UpcomingData>> upcomingDataList;
    private Map<ClassData, Observer<List<UpcomingData>>> classToUpcomingObservers;

    public UpcomingViewModel() {
        List<UpcomingData> dataList = new ArrayList<>();
        upcomingDataList = new MutableLiveData<>(dataList);
        classToUpcomingObservers = new HashMap<>();
    }

    // Temporary; to test functionality
    public void updateClassDataList(List<ClassData> classDataList) {
        if (classToUpcomingObservers.size() > 0) {
            return;
        }

        List<UpcomingData> dataList = upcomingDataList.getValue();

        ClassData class1 = classDataList.get(0);
        dataList.add(new UpcomingAssignmentData("Assignment 1", class1));
        dataList.add(new UpcomingAssignmentData("Assignment 2", class1));
        dataList.add(new UpcomingAssignmentData("Assignment 3", class1));
        dataList.add(new UpcomingExamData("Exam 1", class1));

        ClassData class2 = classDataList.get(1);
        dataList.add(new UpcomingAssignmentData("Assignment 1", class2));
        dataList.add(new UpcomingAssignmentData("Quiz 1", class2));

        ClassData class3 = classDataList.get(2);
        dataList.add(new UpcomingAssignmentData("Final Exam", class3));

        upcomingDataList.setValue(dataList);
    }

    public void attachClassUpcomingObserver(ClassData classData, Observer<List<UpcomingData>> observer) {
        classToUpcomingObservers.put(classData, observer);
        notifyObserverWithClassData(classData);
    }

    private void notifyObserverWithClassData(ClassData classData) {
        Observer<List<UpcomingData>> targetObserver = classToUpcomingObservers.get(classData);
        targetObserver.onChanged(upcomingDataList.getValue().stream()
                .filter(upcomingData -> upcomingData.getAttachedClass() == classData)
                .collect(Collectors.toList()));
    }

    public MutableLiveData<List<UpcomingData>> getUpcomingDataList() {
        return upcomingDataList;
    }

    public void addUpcomingData(UpcomingData upcomingData) {
        List<UpcomingData> dataList = upcomingDataList.getValue();
        dataList.add(upcomingData);
        // TODO: send query to data layer
        upcomingDataList.setValue(dataList);
        notifyObserverWithClassData(upcomingData.getAttachedClass());
    }
}