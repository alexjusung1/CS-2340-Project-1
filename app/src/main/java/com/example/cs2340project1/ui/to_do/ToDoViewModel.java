package com.example.cs2340project1.ui.to_do;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ToDoViewModel extends ViewModel {
    private List<Task> tasks = new ArrayList<>();;

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void updateTask(Task task, int position) {
        tasks.set(position, task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }
}
