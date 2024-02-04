package com.example.cs2340project1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyTimeUtils {
    public final static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
    public final static LocalTime defaultAssignmentDue = LocalTime.of(23, 59);
    public final static LocalTime defaultExamStart = LocalTime.of(12, 0);
    public final static LocalTime defaultExamEnd = LocalTime.of(15, 0);
}
