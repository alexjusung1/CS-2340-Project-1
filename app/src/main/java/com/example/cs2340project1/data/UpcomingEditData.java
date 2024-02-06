package com.example.cs2340project1.data;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ListAdapter;

import com.example.cs2340project1.R;
import com.example.cs2340project1.ui.upcoming.UpcomingViewModel;
import com.example.cs2340project1.utils.MyTimeUtils;
import com.example.cs2340project1.utils.MyViewHolder;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class UpcomingEditData extends UpcomingData {

    final UpcomingData previousData;

    private boolean isAssignmentState;
    private LocalDate assignmentDueDate = MyTimeUtils.defaultDate;
    private String assignmentDueTime = MyTimeUtils.defaultAssignmentDue.format(MyTimeUtils.timeFormat);

    private String examLocation = "Sample Location";
    private LocalDate examDate = MyTimeUtils.defaultDate;
    private LocalTime examBeginTime = MyTimeUtils.defaultBegin;
    private LocalTime examEndTime = MyTimeUtils.defaultEnd;

    public UpcomingEditData(UpcomingData previousData) {
        super(previousData.title, previousData.attachedClass);
        this.previousData = previousData;

        if (previousData instanceof UpcomingAssignmentData) {
            UpcomingAssignmentData realData = (UpcomingAssignmentData) previousData;
            assignmentDueDate = realData.getDueDate();
            assignmentDueTime = realData.getDueTime().format(MyTimeUtils.timeFormat);
            isAssignmentState = true;
        } else if (previousData instanceof UpcomingExamData) {
            UpcomingExamData realData = (UpcomingExamData) previousData;
            examLocation = realData.getLocation();
            examDate = realData.getExamDate();
            examBeginTime = realData.getBeginTime();
            examEndTime = realData.getEndTime();
            isAssignmentState = false;
        } else {
            throw new IllegalArgumentException("Bad UpcomingData type");
        }
    }

    @Override
    public int getType() {
        return 2;
    }

    public UpcomingData getPreviousData() {
        return previousData;
    }

    public static class UpcomingEditHolder extends MyViewHolder {
        final MaterialButtonToggleGroup toggleButtonGroup;
        final Button cancelButton;
        final TextInputEditText title;

        final TextInputLayout assignmentDueDateContainer;
        final TextInputLayout assignmentDueTimeContainer;

        final TextInputLayout examDateContainer;
        final TextInputEditText examDate;
        final LinearLayout examTimesGroup;
        final TextInputLayout examBeginTimeContainer;
        final TextInputEditText examBeginTime;
        final TextInputLayout examEndTimeContainer;
        final TextInputEditText examEndTime;

        public UpcomingEditHolder(@NonNull View itemView, Context parentContext,
                                  UpcomingViewModel upcomingViewModel,
                                  ListAdapter<UpcomingData, MyViewHolder> adapter,
                                  FragmentManager fragmentManager){
            super(itemView, parentContext, upcomingViewModel, adapter, fragmentManager);
            toggleButtonGroup = itemView.findViewById(R.id.toggleButtonGroup);
            cancelButton = itemView.findViewById(R.id.cancelButton);

            title = itemView.findViewById(R.id.upcomingTitle);

            assignmentDueDateContainer = itemView.findViewById(R.id.assignmentDueDateContainer);
            assignmentDueTimeContainer = itemView.findViewById(R.id.assignmentDueTimeContainer);

            examDateContainer = itemView.findViewById(R.id.examDateContainer);
            examDate = examDateContainer.findViewById(R.id.examDate);

            examTimesGroup = itemView.findViewById(R.id.examTimes);
            examBeginTimeContainer = examTimesGroup.findViewById(R.id.examBeginTimeContainer);
            examBeginTime = examBeginTimeContainer.findViewById(R.id.examBeginTime);
            examEndTimeContainer = examTimesGroup.findViewById(R.id.examEndTimeContainer);
            examEndTime = examEndTimeContainer.findViewById(R.id.examEndTime);
        }

        @Override
        public void bind(UpcomingData data) {
            UpcomingEditData realData = (UpcomingEditData) data;

            toggleButtonGroup.check(realData.isAssignmentState ? R.id.assignmentToggle : R.id.examToggle);
            toggleButtonGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
                // TODO: Fix this! crashes... (https://stackoverflow.com/questions/43221847/cannot-call-this-method-while-recyclerview-is-computing-a-layout-or-scrolling-wh)
                if (checkedId == R.id.assignmentToggle) {
                    realData.isAssignmentState = isChecked;
                    adapter.notifyItemChanged(getAdapterPosition());
                }
            });

            cancelButton.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(parentContext);
                builder.setTitle("Discard Changes");
                builder.setMessage("Are you sure you want to discard changes?");
                builder.setPositiveButton("Confirm", (dialog, which) -> {
                    upcomingViewModel.cancelEdit(realData);
                });
                builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                    // Do nothing
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            });

            title.setText(realData.getTitle());
            title.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    data.title = s.toString();
                }
            });

            if (realData.isAssignmentState) {
                assignmentDueDateContainer.setVisibility(View.VISIBLE);
                assignmentDueTimeContainer.setVisibility(View.VISIBLE);
                examDateContainer.setVisibility(View.GONE);
                examTimesGroup.setVisibility(View.GONE);
            } else {
                assignmentDueDateContainer.setVisibility(View.GONE);
                assignmentDueTimeContainer.setVisibility(View.GONE);
                examDateContainer.setVisibility(View.VISIBLE);
                examDate.setText(realData.examDate.format(MyTimeUtils.dateFormatForInput));
                examTimesGroup.setVisibility(View.VISIBLE);

                examDateContainer.setEndIconOnClickListener(view -> {
                    MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                    builder.setTitleText("Select Exam Date");
                    builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
                    MaterialDatePicker<Long> datePicker = builder.build();
                    datePicker.addOnPositiveButtonClickListener(milliseconds -> {
                        realData.examDate = Instant.ofEpochMilli(milliseconds)
                                .atZone(ZoneId.of("UTC"))
                                .toLocalDate();
                        examDate.setText(realData.examDate.format(MyTimeUtils.dateFormatForInput));
                    });

                    datePicker.show(fragmentManager, "Exam/Quiz date");
                });

                examBeginTime.setText(realData.examBeginTime.format(MyTimeUtils.timeFormatForInput));
                examBeginTimeContainer.setEndIconOnClickListener(view -> {
                    MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder();
                    builder.setHour(realData.examBeginTime.getHour());
                    builder.setMinute(realData.examBeginTime.getMinute());
                    builder.setTitleText("Select Begin Time");
                    MaterialTimePicker timePicker = builder.build();
                    timePicker.addOnPositiveButtonClickListener(view1 -> {
                        realData.examBeginTime = LocalTime.of(timePicker.getHour(),
                                timePicker.getMinute());
                        examBeginTime.setText(realData.examBeginTime.format(MyTimeUtils.timeFormatForInput));
                    });
                    timePicker.show(fragmentManager, "Exam/Quiz begin time");
                });

                examEndTime.setText(realData.examEndTime.format(MyTimeUtils.timeFormatForInput));
                examEndTimeContainer.setEndIconOnClickListener(view -> {
                    MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder();
                    builder.setHour(realData.examEndTime.getHour());
                    builder.setMinute(realData.examEndTime.getMinute());
                    builder.setTitleText("Select End Time");
                    MaterialTimePicker timePicker = builder.build();
                    timePicker.addOnPositiveButtonClickListener(view1 -> {
                        realData.examEndTime = LocalTime.of(timePicker.getHour(),
                                timePicker.getMinute());
                        examEndTime.setText(realData.examEndTime.format(MyTimeUtils.timeFormatForInput));
                    });
                    timePicker.show(fragmentManager, "Exam/Quiz end time");
                });
            }
        }
    }
}
