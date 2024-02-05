package com.example.cs2340project1.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.datastore.core.Serializer;

import com.example.cs2340project1.MyTimeUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.stream.Collectors;

import kotlin.Unit;
import kotlin.coroutines.Continuation;

public class ClassData {
    private String className;
    private String instructorName;
    private final EnumSet<ClassData.DayOfWeek> classDays;
    private LocalTime beginTime;
    private LocalTime endTime;

    public ClassData(Builder builder) {
        className = builder.className;
        instructorName = builder.instructorName;
        classDays = builder.classDays;
        beginTime = builder.beginTime;
        endTime = builder.endTime;
    }

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
        if (beginTime == null || endTime == null) {
            return "";
        }

        return String.format("%s - %s",
                beginTime.format(MyTimeUtils.timeFormat),
                endTime.format(MyTimeUtils.timeFormat));
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

    public static class Builder {
        private String className = "Sample Class";
        private String instructorName = "Sample Instructor";
        private EnumSet<ClassData.DayOfWeek> classDays = EnumSet.noneOf(ClassData.DayOfWeek.class);
        private LocalTime beginTime = MyTimeUtils.defaultBegin;
        private LocalTime endTime = MyTimeUtils.defaultEnd;

        // TODO: Implement remaining Builder design pattern (https://www.baeldung.com/java-builder-pattern-inheritance)

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        public ClassData build() {
            return new ClassData(this);
        }
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof ClassData) {
            ClassData otherClass = (ClassData) o;
            return className.equals(otherClass.className)
                    && instructorName.equals(otherClass.instructorName)
                    && classDays.equals(otherClass.classDays)
                    && beginTime.equals(otherClass.beginTime)
                    && endTime.equals(otherClass.endTime);
        }
        return false;
    }
}
