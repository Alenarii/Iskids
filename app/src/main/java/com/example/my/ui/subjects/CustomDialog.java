package com.example.my.ui.subjects;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.my.R;
import com.example.my.ui.BasaData.DBHelper_Sub;

import java.util.ArrayList;

public class CustomDialog extends DialogFragment {
    DBHelper_Sub dbHelper;
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String phone = getArguments().getString("see");
        dbHelper = new DBHelper_Sub(getContext());
        ArrayList<String> n = dbHelper.Get(getContext(), phone);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        if (n.isEmpty()) {
            return builder
                    .setTitle("Диалоговое окно")
                    .setIcon(R.drawable.tt)
                    .setMessage("Название предмета:" + phone + "\n" + "Преподаватель: "  + "\n" + "Кабинет: " + "\n" + "Дополнительно: ")
                    .setNeutralButton("закрыть", null)
                    .create();
        }
        else {
            return builder
                    .setTitle("Диалоговое окно")
                    .setIcon(R.drawable.tt)
                    .setMessage("Название предмета: " + phone + "\n" + "Преподаватель: " + n.get(0) + "\n" + "Кабинет: " + n.get(1) + "\n" + "Дополнительно: " + n.get(2))
                    .setNeutralButton("закрыть", null)
                    .create();
        }
    }
}