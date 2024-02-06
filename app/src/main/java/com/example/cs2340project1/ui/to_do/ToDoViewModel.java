package com.example.cs2340project1.ui.to_do;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ToDoViewModel extends ViewModel {

    private final MutableLiveData<List<String>> toDoItems;

    public ToDoViewModel() {
        toDoItems = new MutableLiveData<>(new ArrayList<>());
    }
    public void addItem(String newItem) {
        List<String> itemsList = toDoItems.getValue();
        itemsList.add(newItem);
        toDoItems.setValue(itemsList);
    }

    public MutableLiveData<List<String>> getItems() {
        return toDoItems;
    }
}