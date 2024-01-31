package com.example.cs2340project1.ui.upcoming;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cs2340project1.data.UpcomingData;

import java.util.List;

public class UpcomingViewModel extends ViewModel {
    private MutableLiveData<List<UpcomingData>> upcomingDataList;

    public UpcomingViewModel() {

    }
}