package com.example.cs2340project1.ui.classes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.datastore.core.Serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.stream.Collectors;


public class ClassObj {
    private String className;
    private String instructorName;
    private String classDays;
    private String time;

    public ClassObj(String className, String instructorName, String classDays, String time) {
        this.className = className;
        this.instructorName = instructorName;
        this.classDays = classDays;
        this.time = time;
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

    public String getDays() {
        return classDays;
    }

    public void setDays(String classDays) {
        this.classDays = classDays;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof com.example.cs2340project1.ui.classes.ClassObj) {
            com.example.cs2340project1.ui.classes.ClassObj otherClass = (com.example.cs2340project1.ui.classes.ClassObj) o;
            return className.equals(otherClass.getClassName())
                    && instructorName.equals(otherClass.getInstructorName())
                    && classDays.equals(otherClass.getDays())
                    && time.equals(otherClass.getTime());
        }
        return false;
    }
}
