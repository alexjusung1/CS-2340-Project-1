package com.example.cs2340project1.data;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.cs2340project1.utils.MyTimeUtils;
import com.example.cs2340project1.R;
import com.example.cs2340project1.ui.upcoming.UpcomingViewModel;
import com.example.cs2340project1.utils.MyViewHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UpcomingAssignmentData extends UpcomingData {
    private LocalDate dueDate = MyTimeUtils.defaultDate;
    private LocalTime dueTime = MyTimeUtils.defaultAssignmentDue;

    public UpcomingAssignmentData(String title, ClassObj attachedClass) {
        super(title, attachedClass);
    }

    public UpcomingAssignmentData(String title, ClassObj attachedClass,
                                  LocalDate dueDate, LocalTime dueTime) {
        this(title, attachedClass);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalTime getDueTime() {
        return dueTime;
    }

    public String getDueDateTimeString() {
        return String.format("due by %s, %s",
                dueDate.format(MyTimeUtils.dateFormat),
                dueTime.format(MyTimeUtils.timeFormat));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UpcomingAssignmentData) {
            UpcomingAssignmentData other = (UpcomingAssignmentData) o;

            return super.equals(o) &&
                    dueDate.equals(other.dueDate) &&
                    dueTime.equals(other.dueTime);
        }
        return false;
    }

    @Override
    public int getType() {
        return 0;
    }

    @NonNull
    @Override
    public LocalDateTime getRepresentativeDate() {
        return LocalDateTime.of(dueDate, dueTime);
    }

    public static class AssignmentHolder extends MyViewHolder {
        final TextView assignmentTitle;
        final TextView assignmentAttachedClass;
        final TextView assignmentDateTime;
        final Button editButton;
        final Button deleteButton;

        public AssignmentHolder(@NonNull View itemView, Context parentContext,
                                UpcomingViewModel upcomingViewModel) {
            super(itemView, parentContext, upcomingViewModel, null, null, null);
            assignmentTitle = itemView.findViewById(R.id.assignmentTitle);
            assignmentAttachedClass = itemView.findViewById(R.id.assignmentAttachedClass);
            assignmentDateTime = itemView.findViewById(R.id.assignmentDateTime);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }

        @Override
        public void bind(UpcomingData data) {
            UpcomingAssignmentData realData = (UpcomingAssignmentData) data;
            assignmentTitle.setText(realData.title);
            assignmentAttachedClass.setText(realData.getAttachedClass().getClassName());
            assignmentDateTime.setText(realData.getDueDateTimeString());
            deleteButton.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(parentContext);
                builder.setTitle("Delete Assignment");
                builder.setMessage("Are you sure you want to delete this assignment?");
                builder.setPositiveButton("Confirm", (dialog, which) -> {
                    upcomingViewModel.removeUpcomingData(data);
                });
                builder.setNegativeButton(android.R.string.cancel, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            });
            editButton.setOnClickListener(view -> {
                upcomingViewModel.replaceWithEdit(data);
            });
        }
    }
}
