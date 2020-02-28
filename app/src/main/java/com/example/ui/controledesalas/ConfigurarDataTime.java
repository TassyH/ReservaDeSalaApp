package com.example.ui.controledesalas;

import android.content.SharedPreferences;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class ConfigurarDataTime extends Fragment {
    DialogFragment datePicker = new DatePickerCalendar();
    DialogFragment timePicker = new TimePickerCalendar();
    SharedPreferences configHora;

}
