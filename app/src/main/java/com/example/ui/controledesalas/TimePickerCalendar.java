package com.example.ui.controledesalas;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerCalendar extends DialogFragment {
    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Calendar horaTime = Calendar.getInstance();
        int hora = horaTime.get(Calendar.HOUR);
        int minuto = horaTime.get(Calendar.MINUTE);
        return  new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hora, minuto, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }
}
