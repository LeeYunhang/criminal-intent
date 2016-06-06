package com.mrcode.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by mrcode on 16-6-5.
 */
public class DatePickerFragment extends DialogFragment{
    @Override public Dialog onCreateDialog(Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.app_name)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
