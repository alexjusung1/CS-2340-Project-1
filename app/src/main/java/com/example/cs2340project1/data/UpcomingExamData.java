package com.example.cs2340project1.data;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpcomingExamData extends UpcomingData {
    private LocalDate examDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public UpcomingExamData(String title, ClassData attachedClass) {
        super(title, attachedClass);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o instanceof )
//    }

    @Override
    public int getType() {
        return 1;
    }
}
