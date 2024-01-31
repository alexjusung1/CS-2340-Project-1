package com.example.cs2340project1.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.datastore.core.Serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.stream.Collectors;

import kotlin.Unit;
import kotlin.coroutines.Continuation;

public class ClassData {
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");

    private String className;
    private String instructorName;
    private EnumSet<ClassData.DayOfWeek> classDays;
    private LocalTime beginTime;
    private LocalTime endTime;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    @NonNull
    public String getClassDaysString() {
        return classDays.stream().map(Enum::name).collect(Collectors.joining(", "));
    }

    public void toggleClassDays(ClassData.DayOfWeek day) {
        if (classDays.contains(day)) {
            classDays.remove(day);
        } else {
            classDays.add(day);
        }
    }

    @NonNull
    public String getTimeString() {
        return String.format("%s - %s", beginTime.format(timeFormat), endTime.format(timeFormat));
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public enum DayOfWeek {
        M, T, W, R, F
    }

    // TODO: Implement Serializer for DataStore (https://developer.android.com/topic/libraries/architecture/datastore#proto-create)
    public static class MySerializer implements Serializer<ClassData> {
        @Override
        public ClassData getDefaultValue() {
            return null;
        }

        @Nullable
        @Override
        public Object readFrom(@NonNull InputStream inputStream, @NonNull Continuation<? super ClassData> continuation) {
            return null;
        }

        @Nullable
        @Override
        public Object writeTo(ClassData classData, @NonNull OutputStream outputStream, @NonNull Continuation<? super Unit> continuation) {
            return null;
        }
    }
}
