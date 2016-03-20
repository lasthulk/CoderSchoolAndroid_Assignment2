package com.tam.nytimes.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.support.v4.app.DialogFragment;

import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by toan on 3/21/2016.
 */
public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener) getActivity();
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }
}
