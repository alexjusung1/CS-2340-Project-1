package com.example.cs2340project1.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class MyTimeUtils {
    public final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d");
    public final static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
    public final static DateTimeFormatter dateFormatForInput = DateTimeFormatter.ofPattern("MM/dd/uuuu");
    public final static DateTimeFormatter timeFormatForInput = DateTimeFormatter.ofPattern("HH:mm");

    public final static LocalTime defaultAssignmentDue = LocalTime.of(23, 59);
    public final static LocalTime defaultBegin = LocalTime.of(12, 0);
    public final static LocalTime defaultEnd = LocalTime.of(15, 0);
    public final static LocalDate defaultDate = LocalDate.of(2024, Month.JANUARY, 1);
    public final static LocalDateTime defaultExam = LocalDateTime.of(defaultDate, defaultBegin);
}
