package com.example.my.ui.subjects;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.my.ui.BasaData.DBHelper_Sub;
import com.example.my.R;

import java.util.ArrayList;

public class SubjectsFragment extends ListFragment {
    DBHelper_Sub dbHelper;
    Dialog dialog;
    ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);
        dbHelper = new DBHelper_Sub(getContext());

        ArrayList<String> NamesList = new ArrayList<>(dbHelper.ReadSub(getContext()));

        mAdapter = new ArrayAdapter<>(getContext(),
               android.R.layout.simple_list_item_1, NamesList);

        setListAdapter(mAdapter);

        dialog = new Dialog(getContext());
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        String selectedPhone = mAdapter.getItem(position);
        CustomDialog dialog = new CustomDialog();
        Bundle args = new Bundle();
        args.putString("see", selectedPhone);
        dialog.setArguments(args);

        dialog.show(getParentFragmentManager(), "custom");
    }
}