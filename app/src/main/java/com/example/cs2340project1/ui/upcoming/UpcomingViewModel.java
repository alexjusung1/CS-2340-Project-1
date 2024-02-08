package com.example.cs2340project1.ui.upcoming;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.cs2340project1.data.ClassObj;
import com.example.cs2340project1.data.UpcomingAssignmentData;
import com.example.cs2340project1.data.UpcomingData;
import com.example.cs2340project1.data.UpcomingEditData;
import com.example.cs2340project1.data.UpcomingExamData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UpcomingViewModel extends ViewModel {
    private final MutableLiveData<List<UpcomingData>> upcomingDataList;
    private final Map<ClassObj, Observer<List<UpcomingData>>> classToUpcomingObservers;
    private final MutableLiveData<Integer> sortType;
    private ClassObj expandedClass = null;
    public static final int CLASS_SORT = 0;
    public static final int DATE_SORT = 1;

    public UpcomingViewModel(MutableLiveData<List<ClassObj>> mutableClassList) {
        List<ClassObj> classDataList = mutableClassList.getValue();
        assert classDataList.size() >= 3;

        List<UpcomingData> dataList = new ArrayList<>();
        ClassObj class1 = classDataList.get(0);
        dataList.add(new UpcomingAssignmentData("Assignment 1", class1));
        dataList.add(new UpcomingAssignmentData("Assignment 2", class1));
        dataList.add(new UpcomingAssignmentData("Assignment 3", class1));
        dataList.add(new UpcomingExamData("Exam 1", class1));

        ClassObj class2 = classDataList.get(1);
        dataList.add(new UpcomingAssignmentData("Assignment 1", class2));
        dataList.add(new UpcomingExamData("Quiz 1", class2));

        ClassObj class3 = classDataList.get(2);
        dataList.add(new UpcomingAssignmentData("Final Exam", class3));

        upcomingDataList = new MutableLiveData<>(dataList);
        classToUpcomingObservers = new HashMap<>();
        sortType = new MutableLiveData<>(CLASS_SORT);

        mutableClassList.observeForever(list -> {
            List<UpcomingData> classDataListOld = upcomingDataList.getValue();
            upcomingDataList.setValue(classDataListOld.stream()
                    .filter(upcomingData -> list.contains(upcomingData.getAttachedClass()))
                    .collect(Collectors.toList()));

            if (!list.contains(expandedClass)) {
                expandedClass = null;
            }
        });
    }

    public void attachUpcomingObserver(LifecycleOwner lifecycleOwner, Observer<List<UpcomingData>> observer) {
        upcomingDataList.observe(lifecycleOwner, observer);
    }

    public void attachClassUpcomingObserver(ClassObj classData, Observer<List<UpcomingData>> observer) {
        classToUpcomingObservers.put(classData, observer);
        notifyObserverWithClassData(classData);
    }

    public void updateSortType(int sortType) {
        this.sortType.setValue(sortType);
    }

    public int getSortType() {
        return sortType.getValue();
    }

    public void attachToSortType(LifecycleOwner lifecycleOwner, Observer<Integer> observer) {
        sortType.observe(lifecycleOwner, observer);
    }

    private void notifyObserverWithClassData(ClassObj classData) {
        Observer<List<UpcomingData>> targetObserver = classToUpcomingObservers.get(classData);
        if (targetObserver == null) return;
        targetObserver.onChanged(upcomingDataList.getValue().stream()
                .filter(upcomingData -> upcomingData.getAttachedClass() == classData)
                .collect(Collectors.toList()));
    }

    public MutableLiveData<List<UpcomingData>> getUpcomingDataList() {
        return upcomingDataList;
    }

    public ClassObj getExpandedClass() {
        return expandedClass;
    }

    public void setExpandedClass(ClassObj expandedClass) {
        this.expandedClass = expandedClass;
    }

    public void removeUpcomingData(UpcomingData upcomingData) {
        List<UpcomingData> dataList = upcomingDataList.getValue();
        dataList.remove(upcomingData);
        upcomingDataList.setValue(dataList);
        notifyObserverWithClassData(upcomingData.getAttachedClass());
    }

    public void addUpcomingData(UpcomingData upcomingData) {
        List<UpcomingData> dataList = upcomingDataList.getValue();
        dataList.add(upcomingData);
        // TODO: send query to data layer
        upcomingDataList.setValue(dataList);
        notifyObserverWithClassData(upcomingData.getAttachedClass());
    }

    public void replaceWithEdit(UpcomingData upcomingData) {
        List<UpcomingData> dataList = upcomingDataList.getValue();
        int position = dataList.indexOf(upcomingData);
        UpcomingEditData editData = new UpcomingEditData(upcomingData);
        dataList.set(position, editData);
        upcomingDataList.setValue(dataList);
        notifyObserverWithClassData(upcomingData.getAttachedClass());
    }

    public void cancelEdit(UpcomingEditData upcomingEditData) {
        List<UpcomingData> dataList = upcomingDataList.getValue();
        int position = dataList.indexOf(upcomingEditData);
        dataList.set(position, upcomingEditData.getPreviousData());
        upcomingDataList.setValue(dataList);
        notifyObserverWithClassData(upcomingEditData.getAttachedClass());
    }

    public void confirmEdit(UpcomingEditData upcomingEditData) {
        List<UpcomingData> dataList = upcomingDataList.getValue();
        int position = dataList.indexOf(upcomingEditData);
        UpcomingData newData = upcomingEditData.confirmEdit();
        dataList.set(position, newData);
        upcomingDataList.setValue(dataList);
        notifyObserverWithClassData(newData.getAttachedClass());
        if (upcomingEditData.getPreviousData().getAttachedClass() != newData.getAttachedClass()) {
            notifyObserverWithClassData(upcomingEditData.getPreviousData().getAttachedClass());
        }
    }
}