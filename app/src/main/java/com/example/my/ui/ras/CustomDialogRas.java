package com.example.my.ui.ras;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.my.R;
import com.example.my.ui.BasaData.DBHelper_Sub;

import java.util.ArrayList;

public class CustomDialogRas extends DialogFragment {
    DBHelper_Sub dbHelper;
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String[] rr = getArguments().getString("ras").split(" ");

        dbHelper = new DBHelper_Sub(getContext());
        ArrayList<String> n = dbHelper.GetByDayOnlyHomeW(getContext(), rr[0], rr[1]);
        ArrayList<String> nn = dbHelper.Get(getContext(), rr[1]);

        String[] array = n.toArray(new String[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        return builder
                    .setTitle(rr[1])
                    .setIcon(R.drawable.tt)
                    .setMessage("Урок: " + nn.get(0) + "\n" +
                            "Преподаватель: " + nn.get(0) + "\n"
                            + "Кабинет: " + nn.get(1) + "\n" +
                            "Домашнее задание: " + array[0].toString())
                    .setNeutralButton("закрыть", null)
                    .create();

    }
}
