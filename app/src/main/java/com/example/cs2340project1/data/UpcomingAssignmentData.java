package com.example.cs2340project1.data;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cs2340project1.MyTimeUtils;
import com.example.cs2340project1.R;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class UpcomingAssignmentData extends UpcomingData {
    private LocalDate dueDate = MyTimeUtils.defaultDate;
    private LocalTime dueTime = MyTimeUtils.defaultAssignmentDue;

    public UpcomingAssignmentData(String title, ClassData attachedClass) {
        super(title, attachedClass);
    }

    public String getDueDateTimeString() {
        return String.format("due by %s, %s",
                dueDate.format(MyTimeUtils.dateFormat),
                dueTime.format(MyTimeUtils.timeFormat));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UpcomingData) {
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

    public static class AssignmentHolder extends UpcomingHolder {
        final TextView assignmentTitle;
        final TextView assignmentDateTime;

        public AssignmentHolder(@NonNull View itemView) {
            super(itemView);
            assignmentTitle = itemView.findViewById(R.id.assignmentTitle);
            assignmentDateTime = itemView.findViewById(R.id.assignmentDateTime);
        }

        @Override
        public void bind(UpcomingData data) {
            UpcomingAssignmentData realData = (UpcomingAssignmentData) data;
            assignmentTitle.setText(realData.title);
            assignmentDateTime.setText(realData.getDueDateTimeString());
        }
    }
}
