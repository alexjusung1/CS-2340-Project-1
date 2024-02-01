package com.example.cs2340project1.data;

import java.time.LocalDateTime;

public class UpcomingAssignmentData extends UpcomingData {
    private LocalDateTime dueDateTime;

    public UpcomingAssignmentData(String title, ClassData attachedClass) {
        super(title, attachedClass);
        dueDateTime = null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UpcomingData) {
            UpcomingAssignmentData other = (UpcomingAssignmentData) o;

            return super.equals(o);
//                    && dueDateTime.equals(other.dueDateTime);
        }
        return false;
    }
}
