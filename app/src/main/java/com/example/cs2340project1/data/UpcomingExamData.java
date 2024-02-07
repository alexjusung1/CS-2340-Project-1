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
import java.time.LocalTime;

public class UpcomingExamData extends UpcomingData {
    private String location = "Sample Location";
    private LocalDate examDate = MyTimeUtils.defaultDate;
    private LocalTime beginTime = MyTimeUtils.defaultBegin;
    private LocalTime endTime = MyTimeUtils.defaultEnd;

    public UpcomingExamData(String title, ClassObj attachedClass) {
        super(title, attachedClass);
    }

    public UpcomingExamData(String title, ClassObj attachedClass, String location,
                            LocalDate examDate, LocalTime beginTime, LocalTime endTime) {
        this(title, attachedClass);
        this.location = location;
        this.examDate = examDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public String getDateTimeString() {
        return String.format("%s, %s - %s",
                examDate.format(MyTimeUtils.dateFormat),
                beginTime.format(MyTimeUtils.timeFormat),
                endTime.format(MyTimeUtils.timeFormat));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UpcomingExamData) {
            UpcomingExamData other = (UpcomingExamData) o;
            return super.equals(o) &&
                    location.equals(other.location) &&
                    examDate.equals(other.examDate) &&
                    beginTime.equals(other.beginTime) &&
                    endTime.equals(other.endTime);
        }
        return false;
    }

    @Override
    public int getType() {
        return 1;
    }

    public static class ExamHolder extends MyViewHolder {
        final TextView examTitle;
        final TextView examAttachedClass;
        final TextView examDateTime;
        final TextView examLocation;
        final Button editButton;
        final Button deleteButton;

        public ExamHolder(@NonNull View itemView, Context parentContext,
                          UpcomingViewModel upcomingViewModel) {
            super(itemView, parentContext, upcomingViewModel, null, null, null);
            examTitle = itemView.findViewById(R.id.examTitle);
            examAttachedClass = itemView.findViewById(R.id.examAttachedClass);
            examDateTime = itemView.findViewById(R.id.examDateTime);
            examLocation = itemView.findViewById(R.id.examLocation);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);
        }

        @Override
        public void bind(UpcomingData data) {
            UpcomingExamData realData = (UpcomingExamData) data;
            examTitle.setText(realData.getTitle());
            examAttachedClass.setText(realData.attachedClass.getClassName());
            examDateTime.setText(realData.getDateTimeString());
            examLocation.setText(realData.getLocation());
            deleteButton.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(parentContext);
                builder.setTitle("Delete Exam/Quiz");
                builder.setMessage("Are you sure you want to delete this exam/quiz?");
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
