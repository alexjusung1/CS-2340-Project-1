package com.example.cs2340project1.data;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cs2340project1.MyTimeUtils;
import com.example.cs2340project1.R;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpcomingExamData extends UpcomingData {
    private String location = "Sample Location";
    private LocalDate examDate = MyTimeUtils.defaultDate;
    private LocalTime beginTime = MyTimeUtils.defaultBegin;
    private LocalTime endTime = MyTimeUtils.defaultEnd;

    public UpcomingExamData(String title, ClassData attachedClass) {
        super(title, attachedClass);
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

    public static class ExamHolder extends UpcomingHolder {
        final TextView examTitle;
        final TextView examDateTime;
        final TextView examLocation;

        public ExamHolder(@NonNull View itemView) {
            super(itemView);
            examTitle = itemView.findViewById(R.id.examTitle);
            examDateTime = itemView.findViewById(R.id.examDateTime);
            examLocation = itemView.findViewById(R.id.examLocation);
        }

        @Override
        public void bind(UpcomingData data) {
            UpcomingExamData realData = (UpcomingExamData) data;
            examTitle.setText(realData.getTitle());
            examDateTime.setText(realData.getDateTimeString());
            examLocation.setText(realData.getLocation());
        }
    }
}
