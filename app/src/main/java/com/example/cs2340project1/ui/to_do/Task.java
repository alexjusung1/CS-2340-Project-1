package com.example.cs2340project1.ui.to_do;

public class Task {
    private String taskName;
    private boolean isChecked;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isChecked = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
