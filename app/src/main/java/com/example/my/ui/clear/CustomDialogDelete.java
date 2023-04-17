package com.example.my.ui.clear;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.my.R;
import com.example.my.ui.BasaData.DBHelper_Sub;

import java.util.ArrayList;

public class CustomDialogDelete extends DialogFragment {
    DBHelper_Sub dbHelper;
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String de = getArguments().getString("del");
        String[] dee = de.split("//");
        dbHelper = new DBHelper_Sub(getContext());
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder
                .setTitle("Диалоговое окно")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Вы действительно хотите " + dee[0] + " ?");
        switch (dee[0])
    {

        case ("Удалить предметы"):{
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.CleanSub();
                        Toast.makeText(getContext(),  "Вы успешно удалили предметы", Toast.LENGTH_SHORT).show();
                    }
                });
        break;
        }
        case ("Удалить расписание"):{
            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dbHelper.CleanRas();
                    Toast.makeText(getContext(), "Вы успешно удалили расписание", Toast.LENGTH_SHORT).show();
                }
            });
            break;
        }
        case ("Удалить всё"):{
            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dbHelper.CleanAll();
                    Toast.makeText(getContext(), "Вы успешно удалили всё", Toast.LENGTH_SHORT).show();
                }
            });
            break;
        }
        case ("Удалить предмет"):{
            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dbHelper.CleanSubOne(getContext(), dee[1]);
                    Toast.makeText(getContext(), "Вы успешно удалили предмет: " + dee[1], Toast.LENGTH_SHORT).show();
                }
            });
            break;
        }

        case ("Удалить д/з"):{
            Toast.makeText(getContext(), dee[0], Toast.LENGTH_SHORT).show();
            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dbHelper.CleanHWOne(dee[1]);
                    Toast.makeText(getContext(), "Вы успешно удалили дз на " + dee[1], Toast.LENGTH_SHORT).show();
                }
            });
            break;
        }
        case ("Удалить всё д/з"):{
            Toast.makeText(getContext(), dee[0], Toast.LENGTH_SHORT).show();
            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dbHelper.CleanHW(getContext());
                    Toast.makeText(getContext(), "Вы успешно удалили всё дз", Toast.LENGTH_SHORT).show();
                }
            });
            break;
        }

    }

    builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        return builder.create();
    }

}